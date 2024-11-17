FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/api-fastlog-0.0.1-SNAPSHOT.jar /app/api-fastlog-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/api-fastlog-0.0.1-SNAPSHOT.jar"]