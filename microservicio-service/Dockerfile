FROM store/oracle/serverjre:8
COPY microservicio-web/target/microservicio-web-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]