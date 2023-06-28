
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
FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/roopya-money-utility-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/roopya.money.utilities-0.0.1-SNAPSHOT.jar /opt/app/roopya.money.utilities-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} roopya.money.utilities-0.0.1-SNAPSHOT.jar
COPY target/roopya-money-utility-0.0.1-SNAPSHOT.jar roopya-money-utility-0.0.1-SNAPSHOT.jarr

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","/roopya-money-utility-0.0.1-SNAPSHOT.jar"]






#FROM eclipse-temurin:10-jdk-alpine
#RUN apk add --no-cache wget tar curl
#RUN mkdir /myapp
#WORKDIR /myapp

#RUN wget https://cdn.azul.com/zulu/bin/zulu15.28.13-ca-jre15.0.1-linux_musl_x64.tar.gz
#RUN tar -xvf zulu15.28.13-ca-jre15.0.1-linux_musl_x64.tar.gz

#COPY --from=builder /application/build/libs/app.jar .
#CMD ["/myapp/zulu15.28.13-ca-jre15.0.1-linux_musl_x64/bin/java","-jar","/myapp/app.jar"]
