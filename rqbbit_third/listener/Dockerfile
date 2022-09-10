FROM openjdk:17-alpine

RUN mkdir /app

COPY build/libs/jms-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]