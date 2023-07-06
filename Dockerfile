FROM openjdk:11-jdk-slim
COPY /target/dinseiworld-0.0.1-SNAPSHOT.jar dinseiworld.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","dinseiworld.jar"]