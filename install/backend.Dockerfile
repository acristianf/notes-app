FROM openjdk:17

COPY ./target/notes-0.0.1-SNAPSHOT.jar /notes.jar
EXPOSE 9001