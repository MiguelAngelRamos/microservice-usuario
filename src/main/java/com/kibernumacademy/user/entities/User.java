package com.kibernumacademy.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

  @Transient // indicamos que no se va persistir en la base de datos.
  private List<Rating> ratings = new ArrayList<>();

}
