FROM openjdk:8-jdk-alpine
MAINTAINER bmj
ARG JAR_FILE=jar/*.jar
COPY ${JAR_FILE} app.jar
RUN apk add --update font-adobe-100dpi ttf-dejavu fontconfig
EXPOSE 8093
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.config.location=/config/application.yml"]