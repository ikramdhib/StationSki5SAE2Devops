FROM openjdk:17
EXPOSE 8087
ADD target/StationSki5SAE2Devops-5.1.1.jar StationSki5SAE2Devops-5.1.1.jar
ENTRYPOINT ["java","-jar","/StationSki5SAE2Devops-5.1.1.jar"]

