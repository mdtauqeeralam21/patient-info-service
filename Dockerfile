FROM openjdk:17

EXPOSE 9005

ADD /target/g2-patient-info-service.jar g2-patient-info-service.jar

ENTRYPOINT [ "java","-jar","/g2-patient-info-service.jar"]