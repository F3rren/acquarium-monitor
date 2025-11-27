FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080

# FROM eclipse-temurin:21-jdk-alpine: Usa immagine lightweight di Java 21
# WORKDIR /app: Cartella di lavoro nel container
# COPY target/*.jar app.jar: Copia il jar compilato in /app/app.jar
# ENTRYPOINT: Avvia il jar (percorso relativo a WORKDIR)