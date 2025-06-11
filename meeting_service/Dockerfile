# Use OpenJDK 21 as base image
FROM openjdk:21

# Set working directory
WORKDIR /meeting_service

# Copy the built jar file into the image
ARG JAR_FILE=target/meeting_service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]