FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7

MAINTAINER bryce.deyto@gmail.com

USER root

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8000

HEALTHCHECK CMD curl --fail http://localhost:8000/actuator/health || exit 1

CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "./app.jar"]
