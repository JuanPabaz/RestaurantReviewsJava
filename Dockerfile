FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/restaurant-0.0.1-SNAPSHOT.jar restaurant-0.0.1-SNAPSHOT.jar
EXPOSE 8080

CMD ["java","-jar","restaurant-0.0.1-SNAPSHOT.jar"]