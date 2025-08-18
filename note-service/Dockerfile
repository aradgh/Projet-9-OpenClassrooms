FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY note-service ./note-service
COPY gateway-service ./gateway-service
COPY patient-service ./patient-service
COPY diabetes-report-service ./diabetes-report-service

WORKDIR /app/note-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/note-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
