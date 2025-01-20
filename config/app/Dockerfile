FROM gradle:jdk21-corretto AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src/infrastructure

RUN gradle build --no-daemon

FROM amazoncorretto:21-alpine3.20-jdk

COPY --from=build /home/gradle/src/infrastructure/build/libs/*-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]