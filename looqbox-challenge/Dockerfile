FROM adoptopenjdk/openjdk11:latest

ARG JAR_FILE=build/libs/pokeapi-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]