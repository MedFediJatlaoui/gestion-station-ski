FROM amazoncorretto:17
EXPOSE 8089
ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
CMD ["java", "-jar", "gestion-station-ski-1.0.jar"]
