# MathChallenger

MathChallenger es una API REST desarrollada en Spring Boot con Java 21, diseñada para realizar cálculos matemáticos con porcentajes dinámicos y manejar datos mediante caché distribuido, reintentos automáticos y almacenamiento en PostgreSQL. Esta API está orientada a proporcionar servicios matemáticos para proyectos que requieran cálculos rápidos y eficientes.

Además, incluye una interfaz gráfica para explorar y probar los endpoints expuesta en Swagger, disponible en:  
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## Comenzando 🚀
Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

Mira Deployment para conocer cómo desplegar el proyecto.

### Pre-requisitos 📋
- **Java 21:** Necesario para ejecutar el proyecto Spring Boot.
- **Maven 3.8+:** Para gestionar dependencias y compilar el proyecto.
- **Docker y Docker Compose (opcional):** Para levantar los servicios de PostgreSQL y Redis.

### Instalación 🔧
1. Clona el repositorio del proyecto:
    ```bash
    git clone https://github.com/CristianCamargo04/mathchallenger-app.git
    cd mathchallenger
    ```

2. Levanta los contenedores de PostgreSQL en el puerto 5433, Redis en el puerto 6379 y la aplicación Spring Boot en el puerto 8080:
    ```bash
    docker-compose up -d
    ```

3. **Obtener la colección de Postman:**
   La colección de Postman para probar los endpoints de la API se encuentra en la raíz del proyecto con el nombre `ApiRestMathchallenger.postman_collection.json`.


4. **Importar la colección de Postman:**
   Abre Postman y sigue estos pasos:
   - Haz clic en el botón "Importar" en la esquina superior izquierda.
   - Selecciona la opción "File" y elige el archivo `ApiRestMathchallenger.postman_collection.json` ubicado en la raíz del proyecto.
   - Haz clic en "Importar" para agregar la colección a Postman.

### Construido con 🛠️
- **Spring Boot** - Framework de Java para el desarrollo de aplicaciones web.
- **Maven** - Manejador de dependencias.
- **PostgreSQL** - Sistema de gestión de bases de datos relacional.
- **Redis** - Almacenamiento de datos en memoria para caché.
