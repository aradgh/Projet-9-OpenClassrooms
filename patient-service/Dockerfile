FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY patient-service ./patient-service
COPY gateway-service ./gateway-service
COPY note-service ./note-service
COPY diabetes-report-service ./diabetes-report-service

WORKDIR /app/patient-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/patient-service/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
