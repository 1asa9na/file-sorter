package com.example.util;

import com.example.domain.DataType;

public class TypeDetector {

    public static DataType detect(String line) {
        if (line == null || line.isBlank()) {
            return DataType.STRING;
        }

        try {
            Long.parseLong(line);
            return DataType.INTEGER;
        } catch (NumberFormatException ignored) {
        }

        try {
            Double.parseDouble(line);
            return DataType.FLOAT;
        } catch (NumberFormatException ignored) {
        }

        return DataType.STRING;
    }
}