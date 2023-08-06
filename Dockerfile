FROM maven:3.8.3-openjdk-17 AS build
# Set the working directory inside the container
WORKDIR /app
# Copy source files and pom.xml 
COPY src ./src
COPY pom.xml .
# Running the build with maven
RUN mvn clean package

# Use the official OpenJDK 8 image as the base image
FROM openjdk:17
# Set the working directory inside the container
WORKDIR /app
# Set the user to runner. Don't allow root privileges.
USER 10000:10000
# Copy the JAR file into the container
COPY --from=build /app/target/random-number-generator*.jar app.jar
# Expose the port on which the Spring Boot application will listen
EXPOSE 8080
# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
