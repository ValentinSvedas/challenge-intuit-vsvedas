# API de Evaluación Intuit

Este proyecto es una **API RESTful** desarrollada con arquitectura **hexagonal**, diseñada para la gestión de clientes.

## Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3
- **Arquitectura**: Hexagonal
- **Persistencia**:
    - **PostgreSQL 16** (con Docker)
    - **JPA (Hibernate)**
    - **Liquibase** (para migraciones de base de datos)
- **Testing**:
    - **JUnit 5**
    - **Mockito**

## Frontend (No terminado por falta de tiempo)

- **React** + **React Admin** (para panel CRUD básico)

## Infraestructura

- **Docker** + **Docker Compose** (para gestionar contenedores)
- **Maven** (gestión de dependencias)

## Cómo Ejecutar el Proyecto

1. **Levantar la base de datos**:
   Ejecuta el siguiente comando en tu terminal para levantar la base de datos con Docker Compose:
   ```bash
   docker compose up -d

2. Ejecutar la API: Ejecuta la clase EvaluacionIntuitApplication.class desde tu IDE o línea de comandos para iniciar la API.


3. Levantar el Frontend: Dirígete a la carpeta ~/front-end/front-init y ejecuta los siguientes comandos:
     ```bash
        npm install
        npm run dev
