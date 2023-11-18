FROM amazoncorretto:17
ARG JAR_URL
EXPOSE 8089
ADD $JAR_URL 5SAE4-G2-gestion-station-ski-1.0.jar
CMD ["java", "-jar", "5SAE4-G2-gestion-station-ski-1.0.jar"]
