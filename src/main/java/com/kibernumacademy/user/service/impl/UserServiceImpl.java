package com.kibernumacademy.user.service.impl;

import com.kibernumacademy.user.entities.Hotel;
import com.kibernumacademy.user.entities.Rating;
import com.kibernumacademy.user.entities.User;
import com.kibernumacademy.user.exceptions.ResourceNotFoundException;
import com.kibernumacademy.user.external.service.IHotelService;
import com.kibernumacademy.user.repository.IUserRepository;
import com.kibernumacademy.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

  private Logger logger = LoggerFactory.getLogger(IUserService.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IHotelService hotelService;
  @Override
  public User saveUser(User user) {
    String randomUserId = UUID.randomUUID().toString(); // GENERAMOS UN ID PERSONALIZADO
    user.setUserId(randomUserId);
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }


  /* Implementación RestTemplate Feign*/
  @Override
  public User getUser(String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    // Comenzamos hacer el llamado con RestTemplate
    Rating[] ratingsUser = restTemplate.getForObject("http://CALIFICACION-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

    logger.info("{}", ratingsUser[0]);

    List<Rating> ratings = Arrays.stream(ratingsUser).collect(Collectors.toList());

    List<Rating> listRatings = ratings.stream().map(rating -> {

      // Aquí, hacemos una petición usando IHotelService.
      // Feign, un cliente declarativo, permite realizar llamadas a servicios web mediante interfaces.
      // Esto es una manifestación del patrón de inversión de dependencias, donde definimos cómo queremos llamar a un servicio, pero no su implementación.
      Hotel hotel = hotelService.getHotel(rating.getHotelId());

      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());

    user.setRatings(listRatings);
    return user;
  }

  /* Implementación Rest Template
  @Override
  public User getUser(String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    // Comenzamos hacer el llamado con RestTemplate
    Rating[] ratingsUser = restTemplate.getForObject("http://CALIFICACION-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);

    logger.info("{}", ratingsUser[0]);

    List<Rating> ratings = Arrays.stream(ratingsUser).collect(Collectors.toList());

    List<Rating> listRatings = ratings.stream().map(rating -> {
      // haciendo uso del rest-template
      ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);
      Hotel hotel = forEntity.getBody();
      logger.info("Respuesta con código de estado: {}", forEntity.getStatusCode());
      // una vez que obtenemos la información del hotel, vamos uso del atributo de rating
      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());

    user.setRatings(listRatings);
    return user;
  }

   */
}
