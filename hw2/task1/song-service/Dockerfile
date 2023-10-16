FROM amazoncorretto:17
ENV SPRING_PROFILES_ACTIVE=docker-run
COPY /target/song-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
CMD ["java","-jar","app.jar"]
