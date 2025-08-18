# Étape de build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copier tout le projet pour que Maven trouve le parent POM
COPY pom.xml .
COPY gateway-service ./gateway-service
COPY patient-service ./patient-service
COPY note-service ./note-service
COPY diabetes-report-service ./diabetes-report-service

WORKDIR /app/gateway-service
RUN mvn clean package -DskipTests

# Étape runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/gateway-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
