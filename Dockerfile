FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

# Copy pom.xml first for better Docker layer caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src
COPY tst ./tst

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim

WORKDIR /app

# Add labels
LABEL maintainer="minjzhao@amazon.com"
LABEL description="Enhanced Java Calculator Application with Dependencies"
LABEL build.timestamp="2025-01-09T14:16:00PST"
LABEL build.trigger="dummy-change-for-ecr-update"

# Copy the built JAR from build stage
COPY --from=build /app/target/java-calculator-1.0.0.jar app.jar

# Run tests first, then start the calculator
CMD echo "Enhanced Java Calculator with Dependencies" && \
    echo "Available options:" && \
    echo "  java -jar app.jar --help" && \
    echo "  java -jar app.jar --json" && \
    echo "  java -jar app.jar --precision 4" && \
    echo "" && \
    echo "Starting calculator in interactive mode..." && \
    java -jar app.jar
