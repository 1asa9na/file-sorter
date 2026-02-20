package com.example.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;

import com.example.domain.DataType;
import com.example.exception.LineWritingException;
import com.example.util.StatsSingletone;

public class OutputService {

    private final Path outputPath;
    private final String prefix;
    private final boolean addMode;
    private final Map<DataType, BufferedWriter> writers = new EnumMap<>(DataType.class);
    
    public OutputService(Path outputPath, String prefix, boolean addMode) {
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.addMode = addMode;
    }

    public void write(String line, DataType type) {
        try {
            BufferedWriter writer = writers.computeIfAbsent(type, this::createWriter);
            writer.write(line);
            writer.newLine();
            StatsSingletone.getInstance().getStats(type).add(line);
        } catch (IOException e) {
            throw new LineWritingException("Cannot write line for " + type);
        }
    }

    private BufferedWriter createWriter(DataType type) throws LineWritingException {
        try {
            Files.createDirectories(outputPath);

            Path file = outputPath.resolve(prefix + type.getFilename());

            return Files.newBufferedWriter(
                    file,
                    StandardOpenOption.CREATE,
                    addMode ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file for " + type);
        }
    }

    public void closeWriters() {
        for (BufferedWriter w : writers.values()) {
            try {
                w.close();
            } catch (IOException e) {
                throw new RuntimeException("Error occured while closing file");
            }
        }
    }
}
