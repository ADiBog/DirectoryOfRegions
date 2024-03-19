package com.example.directoryofregions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegionExistsException.class)
    public ResponseEntity<String> handleRegionExistsException(RegionExistsException ex) {
        // Возвращаем ответ клиенту с сообщением об ошибке и статусом конфликта
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        // Возвращаем ответ клиенту с сообщением об ошибке и статусом внутренней ошибки сервера
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
