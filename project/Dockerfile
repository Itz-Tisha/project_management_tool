# Use official Java runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file (built in target folder)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
