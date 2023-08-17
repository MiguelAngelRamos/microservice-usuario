package com.kibernumacademy.user.service.impl;

import com.kibernumacademy.user.entities.Hotel;
import com.kibernumacademy.user.entities.Rating;
import com.kibernumacademy.user.entities.User;
import com.kibernumacademy.user.exceptions.ResourceNotFoundException;
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

  @Override
  public User getUser(String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

    // Comenzamos hacer el llamado con RestTemplate
    Rating[] ratingsUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), Rating[].class);

    logger.info("{}", ratingsUser[0]);

    List<Rating> ratings = Arrays.stream(ratingsUser).collect(Collectors.toList());

    List<Rating> listRatings = ratings.stream().map(rating -> {
      // haciendo uso del rest-template
      ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+ rating.getHotelId(), Hotel.class);
      Hotel hotel = forEntity.getBody();
      logger.info("Respuesta con código de estado: {}", forEntity.getStatusCode());
      // una vez que obtenemos la información del hotel, vamos uso del atributo de rating
      rating.setHotel(hotel);
      return rating;
    }).collect(Collectors.toList());

    user.setRatings(listRatings);
    return user;
  }
}
