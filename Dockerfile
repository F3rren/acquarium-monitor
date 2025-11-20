FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080

# FROM openjdk:21-jdk-slim: Usa immagine lightweight di Java 21
# WORKDIR /app: Cartella di lavoro nel container
# COPY target/*.jar app.jar: Copia il jar compilato
# EXPOSE 8080: Espone la porta per comunicazione esterna
