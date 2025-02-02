# Build stage
FROM maven:3.8.6-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Runtime stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/building-control.jar

# Optional: Copy logging configuration
# COPY src/main/resources/logging.properties /app/logging.properties

ENTRYPOINT ["java", "-jar", "building-control.jar"]