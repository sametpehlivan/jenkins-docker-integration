FROM amazoncorretto:17
EXPOSE=8080
ARG app_version
ENV APP_VERSION=${app_version}

ADD target/my-app-${APP_VERSION}.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]