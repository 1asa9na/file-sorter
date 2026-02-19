package com.example.exception;

public class CommandLineArgsException extends RuntimeException {
    
    public CommandLineArgsException(String message) {
        super(message);
    }
}
