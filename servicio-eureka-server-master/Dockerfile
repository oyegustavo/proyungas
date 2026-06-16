FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8761
ADD ./target/eureka-service-0.0.1-SNAPSHOT.jar eureka-service.jar
ENTRYPOINT ["java","-jar","/eureka-service.jar"]
