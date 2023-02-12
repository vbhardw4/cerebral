FROM openjdk:17-oracle
EXPOSE 8080
ENV POSTGRES_HOST=localhost
ADD target/cerebral-0.0.1-SNAPSHOT.jar cerebral-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","cerebral-0.0.1-SNAPSHOT.jar"]