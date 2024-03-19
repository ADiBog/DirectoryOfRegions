package com.example.directoryofregions.exception;

public class RegionExistsException extends RuntimeException {
    public RegionExistsException(String message) {
        super(message);
    }
}

