FROM openjdk:21
COPY build/libs/*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]