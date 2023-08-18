package com.kibernumacademy.user.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}

// Registramos un bean de RestTemplate para que pueda ser gestionado por el contenedor de Spring y ser inyectado donde lo necesitemos.

// El RestTemplate es una clase Spring para hacer llamadas Rest hacia otros micro-servicios