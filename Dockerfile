# --- ETAPA 1: Construcción con Maven y Java 21 ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar archivos de configuración y código fuente
COPY pom.xml .
COPY src ./src

# Empaquetar omitiendo completamente las pruebas y su compilación
RUN mvn clean package -Dmaven.test.skip=true

# --- ETAPA 2: Ejecución con JRE de Java 21 ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiar el archivo .jar generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]