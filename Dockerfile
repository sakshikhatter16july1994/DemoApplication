# FROM openjdk:17-jdk-slim
# WORKDIR /app
# COPY target/spring-boot-docker.jar spring-boot-docker.jar
# EXPOSE 8081
# ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]

 FROM maven:3-openjdk-17-slim AS build-base
 WORKDIR /app
 RUN apt-get update && apt-get install -y git
 COPY pom.xml .
 RUN mvn dependency:go-offline -B

 FROM build-base AS build
 COPY src ./src
 RUN mvn clean package -DskipTests

 FROM openjdk:17-jdk-slim AS publish
 WORKDIR /app
 COPY --from=build /app/target/spring-boot-docker.jar spring-boot-docker.jar

 FROM openjdk:17-jdk-slim AS final
 WORKDIR /app

 COPY --from=publish /app/spring-boot-docker.jar spring-boot-docker.jar
 EXPOSE 8081
 ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]