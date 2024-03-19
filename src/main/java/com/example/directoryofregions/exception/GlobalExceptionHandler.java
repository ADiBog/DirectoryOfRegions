package com.example.directoryofregions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Произошла непредвиденная ошибка:: " + ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RegionExistsException.class)
    public ResponseEntity<ApiError> handleRegionExistsException(RegionExistsException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Регион уже существует: " + ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ApiError> handleRegionExistsException(RecordNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Запись отсутствует: " + ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Неверный аргумент: " + ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
