# Usar la imagen oficial de OpenJDK
FROM openjdk:21-jdk-slim

# Crear un directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR generado por Maven al contenedor
COPY target/mathchallenger-0.0.1-SNAPSHOT.jar mathchallenge.jar

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "mathchallenge.jar"]
