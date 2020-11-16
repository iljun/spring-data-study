FROM adoptopenjdk/openjdk11

LABEL maintainer="sumusb34@gmail.com"

ADD ./build/libs/redis-0.0.1-SNAPSHOT.jar /app/redis-example.jar

EXPOSE 8080
CMD ["java", "-jar", "/app/redis-example.jar"]
