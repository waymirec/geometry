FROM openjdk:8-jdk-alpine
MAINTAINER waymire.net
COPY target/GeometryInspector-1.0.jar GeometryInspector-1.0.0.jar
ENTRYPOINT ["java","-jar","/GeometryInspector-1.0.0.jar"]
