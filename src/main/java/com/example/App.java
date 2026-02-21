package com.example;

import java.io.IOException;
import java.nio.file.Path;

import com.example.domain.DataType;
import com.example.exception.CommandLineArgsException;
import com.example.exception.LineReadingException;
import com.example.exception.LineWritingException;
import com.example.io.InputService;
import com.example.io.OutputService;
import com.example.util.ArgsParser;
import com.example.util.StatsSingletone;
import com.example.util.ArgsParser.Config;

public class App {

    private Config config;
    private InputService inputService;
    private OutputService outputService;
    
    public App(String[] args) {
        try {
            this.config = ArgsParser.parse(args);
            this.outputService = new OutputService(config.outputPath(), config.prefix(), config.addMode());
            this.inputService = new InputService(outputService);
        } catch (CommandLineArgsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void run() {
        for (int i = 0; i < config.inputPaths().size(); i++) {
            Path path = config.inputPaths().get(i);
            try {
                inputService.process(path);
            } catch (IOException e) {
                System.err.println("Unexpected IO exception occured while opening file " + path);
                System.err.println(e.getMessage());
            } catch (LineReadingException e) {
                System.err.println("Error occured while reading lines from file " + path);
                System.err.println(e.getMessage());
            } catch (LineWritingException e) {
                System.err.println("Error occured while writing lines into file");
                System.err.println(e.getMessage());
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }
        outputService.closeWriters();
        for (DataType type : DataType.values()) {
            System.out.println("Stats for " + type);
            StatsSingletone.getInstance().getStats(type).get(config.fullStats())
            .entrySet()
            .stream()
            .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
            System.out.println();
        }
    }
}
