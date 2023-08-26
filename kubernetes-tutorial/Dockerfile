FROM openjdk:17

MAINTAINER devops

EXPOSE 9090

WORKDIR /devops-learning

#COPY src/main/resources/application.properties /devops-learning/application.properties

COPY target/kube-status-1.0.0.1.jar /devops-learning/kube-status-1.0.0.1.jar

ENTRYPOINT ["java","-jar","/devops-learning/kube-status-1.0.0.1.jar"]
