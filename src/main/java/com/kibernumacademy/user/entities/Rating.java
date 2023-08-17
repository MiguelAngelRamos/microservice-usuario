package com.kibernumacademy.user.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Rating {
  private String ratingId;

  private String userId;
  private String hotelId;
  private int rating;
  private String comments;

  // Necesitamos tener un atributo del tipo Hotel
  private Hotel hotel;
}
