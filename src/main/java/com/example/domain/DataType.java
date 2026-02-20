package com.example.domain;

public enum DataType {
    INTEGER("integers.txt"),
    FLOAT("floats.txt"),
    STRING("string.txt");

    private final String filename;

    private DataType(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
