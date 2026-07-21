# SmartPods API

Este proyecto corresponde a la API backend de SmartPods, desarrollada con Spring Boot 4 y Java 17. Actualmente se encuentra en una etapa inicial de construcción, con la base del modelo de usuarios, la capa de servicio, la configuración de seguridad y los endpoints básicos ya implementados.

## Objetivo del proyecto

Crear una API REST para gestionar usuarios y preparar la base para futuras funcionalidades del sistema SmartPods.

## Tecnologías utilizadas

- Java 17
- Spring Boot 4.1.0
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven

## Estado actual del proyecto

Hasta el momento se tiene:

- Estructura base del proyecto Spring Boot
- Configuración de seguridad básica para permitir acceso temporal a los endpoints
- Entidad Usuario con campos principales
- Repositorio para persistencia de usuarios
- Servicio con lógica de registro y listado
- Controlador REST para operaciones de usuarios
- Conexión configurada a una base de datos PostgreSQL

## Estructura del proyecto

```text
smartpods-api/
├── src/
│   ├── main/
│   │   ├── java/com/smartpods/api/
│   │   │   ├── config/
│   │   │   │   ├── PasswordConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   └── UsuarioController.java
│   │   │   ├── entity/
│   │   │   │   └── Usuario.java
│   │   │   ├── repository/
│   │   │   │   └── UsuarioRepository.java
│   │   │   ├── service/
│   │   │   │   └── UsuarioService.java
│   │   │   └── SmartpodsApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/smartpods/api/SmartpodsApiApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Componentes principales

### 1. Controlador
El controlador `UsuarioController` expone endpoints para:
- registrar un usuario
- listar usuarios

### 2. Servicio
El servicio `UsuarioService` gestiona:
- encriptación de contraseñas
- guardado de usuarios
- consulta de usuarios

### 3. Entidad
La entidad `Usuario` representa el modelo principal del sistema y contiene información como:
- nombre
- apellido
- correo
- password
- rol

### 4. Seguridad
La configuración actual permite que los endpoints sean accesibles de forma temporal mientras se define la seguridad final del proyecto.

## Configuración de base de datos

La aplicación está conectada a PostgreSQL mediante el archivo de propiedades.

## Próximos pasos sugeridos

- Mejorar la seguridad con autenticación real
- Implementar validaciones más completas para los datos
- Crear más endpoints para gestión de usuarios
- Añadir pruebas unitarias y de integración
- Definir el modelo de negocio completo de SmartPods

## Cómo ejecutar el proyecto

Desde la raíz del proyecto, ejecutar:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

## Nota

Este README refleja el estado actual del proyecto hasta este momento y puede actualizarse conforme avance el desarrollo.
