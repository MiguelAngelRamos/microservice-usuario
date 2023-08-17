package com.kibernumacademy.user.controllers;

import com.kibernumacademy.user.entities.User;
import com.kibernumacademy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Restful controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserService userService;

  // localhost:8081/users "POST"
  @PostMapping
  public ResponseEntity<User> saveUser(@RequestBody User userRequest) {
    User user = userService.saveUser(userRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }
  // localhost:8081/users/{userId}
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUser(@PathVariable String userId) {
    User user = userService.getUser(userId);
    return ResponseEntity.ok(user);
  }

  // localhost:8081/users "GET"
  @GetMapping
  public ResponseEntity<List<User>> listUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }
}
