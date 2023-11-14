FROM openjdk:11-jdk
EXPOSE 8089
ADD target/gestion-station-ski-1.0.jar  ahmed-kaabar-5sae4-g2-gestion-station-ski.jar
ENTRYPOINT ["java","-jar","/ahmed-kaabar-5sae4-g2-gestion-station-ski.jar"]