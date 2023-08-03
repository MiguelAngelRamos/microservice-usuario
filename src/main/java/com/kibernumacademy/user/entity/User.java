package com.kibernumacademy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
  @Id
  @Column(name="id")
  private String userId;
  @Column(name="name", length = 20)
  private String name;
  @Column(name="email")
  private String email;
  @Column(name="information")
  private String information;

}
