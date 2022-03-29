FROM adoptopenjdk/openjdk11:alpine

WORKDIR /workspace

VOLUME /tmp

COPY target/*.jar app.jar

EXPOSE 8082/tcp

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar
