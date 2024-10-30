FROM openjdk:17
EXPOSE 8089
ADD target/gestion-station-ski-1.0-SNAPSHOT.jar station_ski.jar
ENTRYPOINT ["java","-jar","station_ski.jar"]

