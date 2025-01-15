# Usar una imagen base con Maven y OpenJDK
FROM maven:3.9.4-eclipse-temurin-21

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el código fuente al contenedor
COPY . .

RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# Ejecutar Maven para construir el JAR
RUN mvn clean package

# Usar una imagen más ligera para ejecutar el JAR
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=0 /app/target/mathchallenger-0.0.1.jar mathchallenge.jar

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "mathchallenge.jar"]
