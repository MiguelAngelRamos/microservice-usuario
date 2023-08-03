package com.kibernumacademy.user.exceptions;
// Clase de excepción para manejar situaciones donde un recurso no se encuentra
public class ResourceNotFoundException extends RuntimeException {
  // Constructor sin mensaje, con un mensaje predeterminado
  public ResourceNotFoundException() {
    super("Recurso no encontrado en el servidor");
  }

  // Constructor con mensaje personalizado
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
