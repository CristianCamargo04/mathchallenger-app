# MathChallenger

MathChallenger es una API REST desarrollada en Spring Boot con Java 21, diseñada para realizar cálculos matemáticos con porcentajes dinámicos y manejar datos mediante caché distribuido, reintentos automáticos y almacenamiento en PostgreSQL. Esta API está orientada a proporcionar servicios matemáticos para proyectos que requieran cálculos rápidos y eficientes.

## Comenzando 🚀
Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

Mira Deployment para conocer cómo desplegar el proyecto.

### Pre-requisitos 📋
Asegúrate de tener instalados los siguientes programas antes de comenzar:

- **Java 21:** Necesario para ejecutar el proyecto Spring Boot. [Descargar Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- **Maven 3.8+:** Para gestionar dependencias y compilar el proyecto. [Descargar Maven](https://maven.apache.org/download.cgi)
- **Docker y Docker Compose (opcional):** Para levantar los servicios de PostgreSQL y Redis. [Descargar Docker](https://www.docker.com/products/docker-desktop/)

### Instalación 🔧
Sigue estos pasos para tener un entorno de desarrollo en funcionamiento:

1. Clona el repositorio del proyecto:
    ```bash
    git clone https://github.com/CristianCamargo04/mathchallenger-app.git
    cd mathchallenger
    ```

2. Si tienes Docker y Docker Compose instalados, puedes iniciar PostgreSQL y Redis con:
    ```bash
    docker-compose up -d cache db
    ```
   Esto levantará los contenedores de PostgreSQL en el puerto 5433 y Redis en el puerto 6379.

3. Para compilar y generar el JAR del proyecto, usa Maven:
    ```bash
    mvn clean install
    ```

4. **Iniciar solo el contenedor de la aplicación Spring Boot con `docker-compose`**:
    ```bash
    docker-compose up -d spring-app
    ```

5. **Verificar que todos los contenedores están en funcionamiento**:
    ```bash
    docker ps
    ```
   Deberías ver los contenedores `redis-container`, `postgres-container` y `spring-app-container` en funcionamiento.

6. **Obtener la colección de Postman:**
   La colección de Postman para probar los endpoints de la API se encuentra en la raíz del proyecto con el nombre `ApiRestMathchallenger.postman_collection.json`.

7. **Importar la colección de Postman:**
   Abre Postman y sigue estos pasos:
   - Haz clic en el botón "Importar" en la esquina superior izquierda.
   - Selecciona la opción "File" y elige el archivo `ApiRestMathchallenger.postman_collection.json` ubicado en la raíz del proyecto.
   - Haz clic en "Importar" para agregar la colección a Postman.

### Ejecutando las pruebas ⚙️
Para ejecutar las pruebas automatizadas del proyecto, sigue estos pasos:

- **Analiza las pruebas end-to-end 🔩**
  Estas pruebas verifican el funcionamiento correcto de los endpoints, asegurándose de que los cálculos matemáticos y los retornos de datos de porcentaje funcionen según lo esperado.
    ```bash
    mvn test
    ```

### Construido con 🛠️
- **Spring Boot** - Framework de Java para el desarrollo de aplicaciones web.
- **Maven** - Manejador de dependencias.
- **PostgreSQL** - Sistema de gestión de bases de datos relacional.
- **Redis** - Almacenamiento de datos en memoria para caché.

### Autores ✒️
Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios:

- **Cristian Camargo** - Trabajo Inicial - [CristianCamargo04](https://github.com/CristianCamargo04)
- **Cristian Camargo** - Documentación - [CristianCamargo04](https://github.com/CristianCamargo04)

### Expresiones de Gratitud 🎁
- Comenta sobre este proyecto 📢.
- Invita una cerveza 🍺 o un café ☕ a alguien del equipo.
- Da las gracias públicamente 🤓.

⌨️ con ❤️ por **Cristian Camargo** 😊
