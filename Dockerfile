FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY ncba/pom.xml .
COPY ncba/src ./src
COPY ncba/.mvn ./.mvn
COPY ncba/mvnw .
COPY ncba/mvnw.cmd .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]