package com.example.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.domain.DataType;
import com.example.exception.LineReadingException;
import com.example.util.TypeDetector;

public class InputService {

    private final OutputService outputService;

    public InputService(OutputService outputService) {
        this.outputService = outputService;
    }
    
    public void process(Path path) throws IOException, LineReadingException {

        if (!Files.exists(path)) {
            throw new RuntimeException("File is not found: " + path);
        }

        if (!Files.isReadable(path)) {
            throw new RuntimeException("File is not readable: " + path);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                handleLine(line);
            }
        } catch (IOException e) {
            throw new LineReadingException("Error occured while reading file " + path.toString());
        }
    }

    private void handleLine(String line) {
        DataType type = TypeDetector.detect(line);
        outputService.write(line, type);
    }
}
