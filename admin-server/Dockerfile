FROM openjdk:8-jdk-alpine
MAINTAINER bmj
ARG JAR_FILE=jar/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8093
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.config.location=/config/application.yml"]