# Use a base image with Java pre-installed
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged application JAR file into the container
COPY target/api-gateway-0.0.1.jar app.jar

# Expose the application port
EXPOSE 8765

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
