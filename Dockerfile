# Use the official OpenJDK 8 image as the base image
FROM openjdk:8-jdk-alpine
# Set the working directory inside the container
WORKDIR /app
# Copy the JAR file into the container
COPY /tmp/random-number-generator-0.0.1-SNAPSHOT.jar /app/app.jar
# Expose the port on which the Spring Boot application will listen
EXPOSE 8080
# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
