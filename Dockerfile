
#FROM eclipse-temurin:10-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]


#FROM eclipse-temurin:8-jdk-alpine
#FROM openjdk:10-jdk
#VOLUME /tmp
#COPY /target/roopya.money.utilities-0.0.1-SNAPSHOT.jar roopya.money.utilities-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","/roopya.money.utilities-0.0.1-SNAPSHOT.jar"]

# For Java 8, try this
#FROM openjdk:8-jdk-alpine
#FROM maven:3.8.4-openjdk-11-slim

# For Java 11, try this
#FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
#ARG JAR_FILE=/target/roopya-money-utility-0.0.1-SNAPSHOT.jar

# cd /opt/app
#WORKDIR /app

# Copy the Maven project files to the container
#COPY pom.xml .
#COPY src ./src

# Build the Spring Boot application
#RUN mvn package

# cp target/roopya.money.utilities-0.0.1-SNAPSHOT.jar /opt/app/roopya.money.utilities-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} roopya.money.utilities-0.0.1-SNAPSHOT.jar
#COPY /target/roopya-money-utility-0.0.1-SNAPSHOT.jar roopya-money-utility-0.0.1-SNAPSHOT.jar

# java -jar /opt/app/app.jar
#ENTRYPOINT ["java","-jar","/app/roopya-money-utility-0.0.1-SNAPSHOT.jar"]






#FROM eclipse-temurin:10-jdk-alpine
#RUN apk add --no-cache wget tar curl
#RUN mkdir /myapp
#WORKDIR /myapp

#RUN wget https://cdn.azul.com/zulu/bin/zulu15.28.13-ca-jre15.0.1-linux_musl_x64.tar.gz
#RUN tar -xvf zulu15.28.13-ca-jre15.0.1-linux_musl_x64.tar.gz

#COPY --from=builder /application/build/libs/app.jar .
#CMD ["/myapp/zulu15.28.13-ca-jre15.0.1-linux_musl_x64/bin/java","-jar","/myapp/app.jar"]





# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-11-slim
# Set the working directory inside the container
#WORKDIR /app
# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src
# Download the dependencies
RUN mvn dependency:go-offline -B
# Copy the application source code 
RUN mvn clean install
# End of Docker file
# Build the Spring Boot application
RUN cd target
COPY target/roopya-money-utility-0.0.1-SNAPSHOT.jar /app.jar
# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "/app.jar"]
