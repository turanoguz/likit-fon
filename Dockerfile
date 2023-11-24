FROM maven:3.9.3-eclipse-temurin-20-alpine
WORKDIR /kogus
COPY ./winvest-application ./winvest-application
COPY ./winvest-auth-v1 ./winvest-auth-v1
COPY ./winvest-fund-v1 ./winvest-fund-v1
COPY ./winvest-core ./winvest-core
COPY ./pom.xml ./pom.xml
RUN mvn clean install

WORKDIR /kogus/winvest-application/target
CMD java --enable-preview -jar winvest-application.jar