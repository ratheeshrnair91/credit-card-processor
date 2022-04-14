FROM openjdk:17
ADD target/card-processor.jar card-processor.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","card-processor.jar"]