package com.kibernumacademy.user.external.service;

import com.kibernumacademy.user.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface IHotelService {

  @GetMapping("/hotels/{hotelId}")
  Hotel getHotel(@PathVariable String hotel);
}
