FROM gradle:7.6-jdk17 AS BUILD
WORKDIR /app
COPY . .
RUN gradle --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=BUILD /app/build/libs/*.jar /app/usuario.jar

EXPOSE 8080
CMD ["java", "-jar", "/app/usuario.jar"]