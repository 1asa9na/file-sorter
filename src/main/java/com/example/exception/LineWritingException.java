package com.example.exception;

public class LineWritingException extends RuntimeException {
    
    public LineWritingException(String message) {
        super(message);
    }
}
