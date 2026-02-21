# Demo Project

## Prerequisites

- **Java**: 17
- **Build System**: Maven 3.6.0 or higher

## Building the Project

```bash
mvn clean package
```

This command compiles the source code and creates an executable JAR file.

## Running the Application

```bash
java -jar target/demo-1.0-SNAPSHOT.jar [options] <file1> [file2] ...
```

### Command Line Arguments

**Input files** (required):
- One or more input files containing mixed integers, floating-point numbers, and strings (one value per line)

**Options** (optional):
- `-o <path>` - Output directory (default: current directory)
- `-p <prefix>` - Prefix for output filenames (default: empty)
- `-a` - Append mode (default: overwrite existing files)
- `-s` - Brief statistics (count only)
- `-f` - Full statistics (count, min, max, sum, average for numbers; count, min/max length for strings)

### Output Files

By default, creates:
- `integers.txt` - integer values
- `floats.txt` - floating-point numbers
- `strings.txt` - string data

With `-p result_` and `-o /some/path`, creates:
- `/some/path/result_integers.txt`
- `/some/path/result_floats.txt`
- `/some/path/result_strings.txt`

Output files are created only if the corresponding data type is found in input files.

### Example Usage

```bash
java -jar target/demo-1.0-SNAPSHOT.jar -o ./output -p data_ -f input1.txt input2.txt
```

## Project Structure

- `com.example.Main` - Application entry point
- `com.example.App` - Core application logic
- `domain/` - Data type definitions
- `exception/` - Custom exceptions
- `io/` - Input and output services
- `stats/` - Statistical calculation classes
- `util/` - Utility classes including argument parsing and type detection

## Dependencies

This project has no external dependencies. It uses only the Java Standard Library.

## Configuration

The project is configured to compile with Java 17 as source and target version (see `pom.xml`).