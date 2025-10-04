# Enhanced Java Calculator

A feature-rich Java calculator application with external dependencies, automated testing, and deployment to AWS ECR using GitHub Actions.

## What it does

This project contains:
- An enhanced command-line calculator with advanced operations (+, -, *, /, ^, sqrt)
- Professional logging with SLF4J and Logback
- JSON output support using Jackson
- Modern CLI interface with Picocli
- Comprehensive testing with JUnit 5 and AssertJ
- Maven-based dependency management
- Docker containerization and CI/CD pipeline

## Project Structure

```
├── .github/workflows/build-and-deploy.yml  # GitHub Actions CI/CD workflow
├── src/
│   ├── java/Calculator.java               # Enhanced calculator application
│   └── resources/logback.xml              # Logging configuration
├── tst/
│   └── java/CalculatorTest.java           # JUnit 5 tests
├── pom.xml                                # Maven dependencies
├── Dockerfile                             # Multi-stage container build
└── README.md
```

## Dependencies

- **SLF4J + Logback**: Professional logging framework
- **Jackson**: JSON processing and output formatting
- **Picocli**: Modern command-line interface framework
- **Apache Commons Math**: Mathematical utilities and precision handling
- **JUnit 5**: Modern testing framework
- **AssertJ**: Fluent assertion library for tests

## Usage

### Run locally with Maven
```bash
# Compile and run tests
mvn clean test

# Build the application
mvn clean package

# Run the calculator
java -jar target/java-calculator-1.0.0.jar

# Run with options
java -jar target/java-calculator-1.0.0.jar --json --precision 4
java -jar target/java-calculator-1.0.0.jar --help
```

### Docker
```bash
# Build image (multi-stage build)
docker build -t enhanced-calculator .

# Run with default settings
docker run -it enhanced-calculator

# Run with custom options
docker run -it enhanced-calculator java -jar app.jar --json
```

### Trigger CI/CD
- Push to `main` branch (automatic)
- Manual trigger via GitHub Actions tab

## Calculator Features

- **Basic Operations**: `+`, `-`, `*`, `/`
- **Advanced Operations**: `^` (power), `sqrt` (square root)
- **JSON Output**: `--json` flag for structured output
- **Precision Control**: `--precision N` for decimal places
- **Professional Logging**: Structured logs to console and file
- **Error Handling**: Comprehensive validation and error messages
- **Interactive CLI**: Modern command-line interface

## Example Usage

```bash
# Basic calculation
5 + 3 = 8.00

# Power operation
2 ^ 3 = 8.00

# Square root
sqrt(16) = 4.00

# JSON output
{"operation":"+","operand1":5.0,"operand2":3.0,"result":8.0,"precision":2}
```

## Docker Registry

Images are automatically built and pushed to:
- **Registry**: `085494094526.dkr.ecr.us-west-2.amazonaws.com/gh/repo`
- **Tags**: `latest` and commit SHA

## Testing

The application includes comprehensive tests using JUnit 5 and AssertJ:
- Unit tests for all mathematical operations
- Error condition testing (division by zero, invalid operations)
- Fluent assertions for better test readability
- Automated test execution in CI/CD pipeline

Run tests with: `mvn test`
