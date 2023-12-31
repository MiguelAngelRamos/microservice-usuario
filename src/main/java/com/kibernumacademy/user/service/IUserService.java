package com.kibernumacademy.user.service;

import com.kibernumacademy.user.entities.User;

import java.util.List;

public interface IUserService {
  User saveUser(User user);
  List<User> getAllUsers();
  User getUser(String userId);
}
