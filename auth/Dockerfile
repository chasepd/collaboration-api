# Stage 1: Build the application
FROM maven:3.8.4-openjdk-11-slim as builder

# Copy the pom.xml and source code
COPY ./pom.xml /usr/src/myapp/pom.xml
COPY ./src /usr/src/myapp/src

# Set the working directory
WORKDIR /usr/src/myapp

# Package the application
RUN mvn clean package

# Stage 2: Create the final image
FROM openjdk:11-jre-slim

# Copy the built artifact from the builder stage
COPY --from=builder /usr/src/myapp/target/auth-1.0-SNAPSHOT.jar /usr/app/myapp.jar

# Set the working directory
WORKDIR /usr/app

# Run the application
CMD ["java", "-jar", "myapp.jar"]
