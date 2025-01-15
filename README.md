# MathChallenger

*MathChallenger* es una API REST desarrollada con Spring Boot y Java 21, diseñada para realizar cálculos matemáticos con porcentajes dinámicos, utilizando una arquitectura eficiente que incluye caché distribuido, reintentos automáticos y almacenamiento en PostgreSQL.

La API está orientada a ofrecer servicios matemáticos de alto rendimiento y precisión, adecuados para proyectos que requieren cálculos rápidos y robustos. Además, cuenta con una interfaz gráfica para la exploración y prueba de endpoints, accesible en:  
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

---

## Requisitos previos

Para garantizar el correcto funcionamiento del proyecto, es necesario contar con las siguientes herramientas instaladas:
- *Java 21:* Requerido para ejecutar la aplicación.
- *Maven 3.8 o superior:* Para gestionar las dependencias y realizar la compilación.
- *Docker y Docker Compose (opcional):* Para gestionar los servicios de PostgreSQL y Redis mediante contenedores.

---

## Instalación y ejecución

1. *Clonar el repositorio:*  
   Ejecute los siguientes comandos para obtener el código fuente:  
   ```bash  
      git clone https://github.com/CristianCamargo04/mathchallenger-app.git  
      cd mathchallenger
   ```

2. *Levantamiento de servicios:*  
   Los contenedores necesarios para la ejecución del proyecto se definen en el archivo docker-compose.yml. La imagen de la aplicación que utiliza el docker-compose se encuentra disponible en Docker Hub en el siguiente enlace:  
   [https://hub.docker.com/r/cristiancamargo04/mathchallenger-spring-app](https://hub.docker.com/r/cristiancamargo04/mathchallenger-spring-app)

   Para iniciar los servicios, ejecute:  
   ```bash  
      docker-compose up -d
   ```

   *Nota:* Si desea construir y ejecutar la imagen de Docker localmente:
   
   En lugar de utilizar la imagen cristiancamargo04/mathchallenger-spring-app:latest:
   ```
      image: cristiancamargo04/mathchallenger-spring-app:latest
   ```

   Modifique su archivo docker-compose.yml para reemplazarla por:
   ```
      build:
         context: .
         dockerfile: Dockerfile
   ```

3. *Acceso a Swagger:*  
   Una vez que la aplicación esté en ejecución, acceda a la interfaz Swagger en:  
   [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)


4. *Pruebas con Postman:*
   - En la raíz del proyecto se encuentra la colección de Postman denominada ApiRestMathchallenger.postman_collection.json.
   - Importe esta colección en Postman siguiendo estos pasos:
      1. Abra Postman y haga clic en "Importar".
      2. Seleccione "Archivo" y cargue el archivo ApiRestMathchallenger.postman_collection.json.
      3. Confirme la importación para disponer de los endpoints listos para prueba.

---

## Tecnologías utilizadas

El proyecto está construido con las siguientes tecnologías:
- *Spring Boot:* Framework para el desarrollo de aplicaciones web con Java.
- *Maven:* Herramienta de gestión de dependencias.
- *PostgreSQL:* Base de datos relacional para el almacenamiento persistente.
- *Redis:* Sistema de caché en memoria para mejorar el rendimiento.

---  
