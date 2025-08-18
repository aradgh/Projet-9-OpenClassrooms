FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY diabetes-report-service ./diabetes-report-service
COPY gateway-service ./gateway-service
COPY patient-service ./patient-service
COPY note-service ./note-service

WORKDIR /app/diabetes-report-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/diabetes-report-service/target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","app.jar"]
