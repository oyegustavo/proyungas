FROM eclipse-temurin:8-jdk
VOLUME /tmp
EXPOSE 8090
ADD ./target/zuul-server-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java","-Dcom.sun.xml.bind.v2.runtime.reflect.opt.OptimizedAccessorFactory.noOptimization=true","-jar","/zuul-server.jar"]
