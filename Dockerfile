FROM amazoncorretto:17

ARG app_version
EXPOSE 8080
ENV APP_VERSION=${app_version}

ADD target/my-app-${APP_VERSION}.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]