FROM openjdk:14
CMD java -Dfile.encoding=UTF-8 -Djava.awt.headless=true -Xmx6G -server -Xms1G  -XX:NewSize=128m -XX:MaxNewSize=256m -XX:SurvivorRatio=5 -XX:TargetSurvivorRatio=30 -XX:PermSize=1G -XX:MaxPermSize=2G -Xincgc -XX:+CMSIncrementalMode -XX:+CMSIncrementalPacing -XX:+CMSParallelRemarkEnabled -XX:+UseParNewGC -XX:+UseTLAB -XX:+CMSPermGenSweepingEnabled -XX:+CMSClassUnloadingEnabled -Dsun.jnu.encoding=ISO-8859-15
VOLUME /tmp
EXPOSE 9100
ADD ./target/servicio-oauth-0.0.1-SNAPSHOT.jar servicio-oauth.jar
ENTRYPOINT ["java","-jar","/servicio-oauth.jar"]
