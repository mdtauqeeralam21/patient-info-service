FROM openjdk:17

EXPOSE 9005

ADD /target/patient-info-service.jar patient-info-service.jar

ENTRYPOINT [ "java","-jar","/patient-info-service.jar"]