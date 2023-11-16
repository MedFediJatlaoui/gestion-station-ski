FROM openjdk:11-jdk
EXPOSE 8089
ADD target/sofiene-mazlout-5sae4-g2-gestion-station-ski.jar
ENTRYPOINT ["java","-jar","/sofiene-mazlout-5sae4-g2-gestion-station-ski.jar"]