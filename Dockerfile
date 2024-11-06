FROM openjdk:17
EXPOSE 8087
ADD target/StationSki5SAE2Devops-5.0.13.jar StationSki5SAE2Devops-5.0.13.jar
ENTRYPOINT ["java","-jar","/StationSki5SAE2Devops-5.0.13.jar"]