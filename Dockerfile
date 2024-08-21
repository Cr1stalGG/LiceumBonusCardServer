FROM maven:3.9.6-sapmachine-17 as build

LABEL Maintaner = "Aliaksandr Savitski"

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests #delete skipping tests in runtime

FROM openjdk:20

COPY --from=build target/LiceumBonusCardServer-0.0.1-SNAPSHOT.jar ./backend.jar

#CMD "docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management"
ENTRYPOINT ["java", "-jar", "backend.jar"]