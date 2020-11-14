FROM adoptopenjdk/openjdk11

LABEL maintainer="sumusb34@gmail.com"

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

ADD ./build/libs/mongodb-0.0.1-SNAPSHOT.jar /app/mongodb-example.jar

EXPOSE 8080
CMD ["java", "-jar", "/app/mongodb-example.jar"]
