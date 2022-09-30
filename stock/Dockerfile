#For Java 8 try this
FROM openjdk:8-jdk-alpine

#Refer to Maven build -> fileName
ARG JAR_FILE=target/stock-0.0.1-SNAPSHOT.jar

#cd /opt/app
WORKDIR /opt/app

#cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

ADD src/main/resources/application.properties /opt/app/application.properties

#java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
