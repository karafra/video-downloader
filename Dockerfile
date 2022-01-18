FROM maven:3.6.0-jdk-11-slim AS build  

COPY src /usr/src/app/src  

COPY pom.xml /usr/src/app  

COPY checkstyle.xml /usr/src/app  

RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11-jre-slim 

COPY --from=build /usr/src/app/target/bitchute-dl-0.0.1-SNAPSHOT.war /usr/app/bitchute-dl-0.0.1-SNAPSHOT.war  

EXPOSE 8080 

CMD ["java","-jar","/usr/app/demo-0.0.1-SNAPSHOT.jar"] 