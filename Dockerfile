FROM openjdk:11
EXPOSE 8089
ADD target/5SAE4_G2_GestionStationSki.jar 5SAE4_G2_GestionStationSki.jar
ENTRYPOINT ["java","-jar","/5SAE4_G2_GestionStationSki.jar"]
