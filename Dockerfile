FROM openjdk:11
EXPOSE 8089
ADD target/5SAE4-G1-stationski.jar 5SAE4-G1-stationski.jar
ENTRYPOINT ["java","-jar","/5SAE4-G1-stationski.jar"]
