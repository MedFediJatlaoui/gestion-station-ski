FROM openjdk:11-jdk
ARG JAR_UR
EXPOSE 8089
ADD $JAR_URL sofiene-mazlout-5sae4-g2-gestion-station-ski.jar
ENTRYPOINT ["java","-jar","/sofiene-mazlout-5sae4-g2-gestion-station-ski.jar"]