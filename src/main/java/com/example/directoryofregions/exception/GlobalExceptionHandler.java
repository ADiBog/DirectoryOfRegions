package com.example.directoryofregions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegionExistsException.class)
    public ResponseEntity<String> handleRegionExistsException(RegionExistsException ex) {
        // ���������� ����� ������� � ���������� �� ������ � �������� ���������
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}