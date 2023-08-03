package com.kibernumacademy.user.exceptions;

import com.kibernumacademy.user.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Clase que maneja excepciones a nivel global en la aplicación
@RestControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
    String message = resourceNotFoundException.getMessage();

    // Construcción de la respuesta API
    ApiResponse response = new ApiResponse().builder()
            .message(message)
            .success(false)
            .status(HttpStatus.NOT_FOUND)
            .build();
    // Devolver una respuesta HTTP con estado 404 - No encontrado
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
