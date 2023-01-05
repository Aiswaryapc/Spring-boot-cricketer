FROM openjdk:8
ADD /target/springbootdemo-0.1.jar cricketapp.jar
EXPOSE 8081
ENTRYPOINT [ "java","-jar","cricketapp.jar" ]