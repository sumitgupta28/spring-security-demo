FROM maven:3.5.2-jdk-9 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:9
COPY --from=build /usr/src/app/target/demo-security*.jar /usr/app/spring-boot-security-demo.jar 
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/spring-boot-security-demo.jar"]