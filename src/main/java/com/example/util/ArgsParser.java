package com.example.util;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.exception.CommandLineArgsException;

public class ArgsParser {

    public record Config (
        List<Path> inputPaths,
        Path outputPath,
        String prefix,
        Boolean fullStats,
        Boolean addMode
    ) { }
    
    public static Config parse(String[] args) throws CommandLineArgsException {

        if (args.length == 0) {
            throw new CommandLineArgsException("No command line arguments.");
        }

        Boolean fullStats = null;
        Boolean addMode = false;
        List<Path> inputPaths = new ArrayList<>();
        String prefix = "";
        Path outputPath = Paths.get("");

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {

                case "-o":
                    if (args.length <= i + 1 || args[i + 1].charAt(0) == '-') {
                        throw new CommandLineArgsException("Missing output path after \"-o\"");
                    }
                    try {
                        outputPath = Paths.get(args[++i]);
                    } catch (InvalidPathException e) {
                        throw new CommandLineArgsException("Invalid Path");
                    }
                    break;

                case "-p":
                    if (args.length <= i + 1 || args[i + 1].charAt(0) == '-') {
                        throw new CommandLineArgsException("Missing prefix after \"-p\"");
                    }
                    prefix = args[++i];
                    break;

                case "-a":
                    addMode = true;
                    break;

                case "-s":
                    if (fullStats != null) {
                        throw new CommandLineArgsException("Cannot use multiple statistic modifiers together");
                    }
                    fullStats = false;
                    break;

                case "-f":
                    if (fullStats != null) {
                        throw new CommandLineArgsException("Cannot use multiple statistic modifiers together");
                    }
                    fullStats = true;
                    break;
                
                default:
                    Path file = Paths.get(args[i]);

                    inputPaths.add(file);
                    break;
            }
        }
        if (fullStats == null) {
            fullStats = false;
        }
        if (inputPaths.size() == 0) {
            throw new CommandLineArgsException("Missing input files");
        }
        return new Config(inputPaths, outputPath, prefix, fullStats, addMode);
    }
}
