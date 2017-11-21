FROM maven:3.2-jdk-8-onbuild
HEALTHCHECK --timeout=10s --interval=1000s --retries=3 CMD curl -s --fail http://localhost:4040/tours/1/ratings || exit 1
EXPOSE 4040
RUN mvn install
ENTRYPOINT ["java","-jar","target/explorecali-0.0.1-SNAPSHOT.jar"]
