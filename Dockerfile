FROM openjdk:17
EXPOSE 8089
ADD target/gestion-station-ski-1.0-SNAPSHOT.jar stationski.jar
ENTRYPOINT ["java","-jar","stationski.jar"]