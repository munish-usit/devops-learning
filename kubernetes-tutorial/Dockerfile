FROM openjdk:17

MAINTAINER devops

EXPOSE 9090

#WORKDIR /devops-learning

COPY target/kube-status-1.0.0.1.jar /usr/src/kube-status-1.0.0.1.jar

ENTRYPOINT ["java","-jar","/usr/src/kube-status-1.0.0.1.jar"]
