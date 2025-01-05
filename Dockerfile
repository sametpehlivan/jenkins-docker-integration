FROM amazoncorretto:17
ADD target/jenkins-docker-integration-1.0.0-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]