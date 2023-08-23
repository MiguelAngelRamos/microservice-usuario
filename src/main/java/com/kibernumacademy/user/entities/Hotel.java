package com.kibernumacademy.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

  private String id;
  private String name;
  private String information;
  private String location;

  @Override
  public String toString() {
    return "Hotel{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", information='" + information + '\'' +
            ", location='" + location + '\'' +
            '}';
  }
}
