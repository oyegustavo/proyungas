FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8090
ADD ./target/zuul-server-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java","-jar","/zuul-server.jar"]
