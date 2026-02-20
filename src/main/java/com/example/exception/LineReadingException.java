package com.example.exception;

public class LineReadingException extends RuntimeException {
    
    public LineReadingException(String message) {
        super(message);
    }
}
