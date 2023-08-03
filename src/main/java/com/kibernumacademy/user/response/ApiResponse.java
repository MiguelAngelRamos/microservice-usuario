package com.kibernumacademy.user.response;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
  // Mensaje de la respuesta, como un error o confirmación
  private String message;
  // Indica si la operación fue exitosa o no
  private boolean success;
  // Estado Http de la respuesta
  private HttpStatus status;
}