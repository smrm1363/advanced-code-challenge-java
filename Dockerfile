# Use an OpenJDK base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at the working directory
COPY code-challenge/target/code-refactoring-1.0-SNAPSHOT.jar /app/code-refactoring-1.0-SNAPSHOT.jar

# Expose the port that your application runs on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "code-refactoring-1.0-SNAPSHOT.jar"]
