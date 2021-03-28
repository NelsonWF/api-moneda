FROM gradle:6.6-jdk14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/Application.yml application-dev.yml
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 4300