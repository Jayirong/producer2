FROM openjdk:21-ea-24-oracle

WORKDIR /app

COPY target/producer2-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

CMD [ "java", "-jar", "app.jar" ]