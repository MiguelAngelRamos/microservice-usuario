package com.kibernumacademy.user.repository;

import com.kibernumacademy.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {
}
