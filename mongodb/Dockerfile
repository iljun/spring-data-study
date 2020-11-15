FROM adoptopenjdk/openjdk11

LABEL maintainer="sumusb34@gmail.com"

ADD ./build/libs/mongodb-0.0.1-SNAPSHOT.jar /app/mongodb-example.jar

EXPOSE 8080
CMD ["java", "-jar", "/app/mongodb-example.jar"]
