FROM openjdk:17-jdk-alpine
COPY ./target/project-0.0.1-SNAPSHOT.jar server.jar
ENTRYPOINT [ "java", "-jar", "/server.jar" ]