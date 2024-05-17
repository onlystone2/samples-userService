FROM eclipse-temurin:17-jdk-alpine
COPY target/user-service-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]