package com.kibernumacademy.user.service.impl;

import com.kibernumacademy.user.entities.User;
import com.kibernumacademy.user.exceptions.ResourceNotFoundException;
import com.kibernumacademy.user.repository.IUserRepository;
import com.kibernumacademy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

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
    return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
  }
}
