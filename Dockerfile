FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar server.jar
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/server.jar" ]