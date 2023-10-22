FROM amazoncorretto:17
#ENV SPRING_PROFILES_ACTIVE=docker-run
ENV SPRING_PROFILES_ACTIVE=docker-run-with-eureka
COPY /target/resource-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]