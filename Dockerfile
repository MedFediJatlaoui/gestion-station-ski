FROM amazoncorretto:17
EXPOSE 8089
ADD target/5SAE4-G2-gestion-station-ski-1.0.jar 5SAE4-G2-gestion-station-ski-1.0.jar
CMD ["java", "-jar", "5SAE4-G2-gestion-station-ski-1.0.jar"]
