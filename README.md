# SmartPods API

API backend de SmartPods desarrollada con Spring Boot 4 y Java 17.

## Objetivo del proyecto

Construir una API REST para gestionar la operativa de SmartPods, incluyendo usuarios, pedidos, lockers y autenticación.

## Tecnologías utilizadas

- Java 17
- Spring Boot 4.1.0
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- PostgreSQL
- Maven
- ZXing (QR code generation)
- Lombok

## Estado actual del proyecto

Hasta ahora el proyecto incluye:

- Estructura base de Spring Boot
- Configuración de seguridad y contraseña encriptada con BCrypt
- Entidad `Usuario` y su repositorio
- Servicio `UsuarioService` con registro, login y listado
- Controladores REST para usuarios, autenticación, administración, pedidos y simulador
- Entidades de dominio para pedidos y lockers
- Repositorios y servicios básicos para pedidos y lockers
- Utilidad de generación de QR (`QrCodeUtil`)
- Seed inicial de datos (`DataSeeder`)
- Conexión a base de datos PostgreSQL configurada en `application.properties`
- Compilación Maven exitosa (`./mvnw.cmd compile`)

## Estructura actual del proyecto

```text
smartpods-api/
├── src/
│   ├── main/
│   │   ├── java/com/smartpods/api/
│   │   │   ├── config/
│   │   │   │   ├── DataSeeder.java
│   │   │   │   ├── PasswordConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AdminController.java
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── PedidoController.java
│   │   │   │   ├── SimuladorController.java
│   │   │   │   └── UsuarioController.java
│   │   │   ├── dto/
│   │   │   │   ├── DashboardDTO.java
│   │   │   │   ├── EscaneoRequestDTO.java
│   │   │   │   ├── EscaneoResponseDTO.java
│   │   │   │   ├── LoginDTO.java
│   │   │   │   ├── LoginResponseDTO.java
│   │   │   │   ├── PedidoCrearDTO.java
│   │   │   │   ├── PedidoResponseDTO.java
│   │   │   │   └── UsuarioRegistroDTO.java
│   │   │   ├── entity/
│   │   │   │   ├── EstadoLocker.java
│   │   │   │   ├── EstadoPedido.java
│   │   │   │   ├── Locker.java
│   │   │   │   ├── Pedido.java
│   │   │   │   ├── Rol.java
│   │   │   │   ├── TamanoLocker.java
│   │   │   │   └── Usuario.java
│   │   │   ├── repository/
│   │   │   │   ├── LockerRepository.java
│   │   │   │   ├── PedidoRepository.java
│   │   │   │   └── UsuarioRepository.java
│   │   │   ├── service/
│   │   │   │   ├── PedidoService.java
│   │   │   │   └── UsuarioService.java
│   │   │   ├── security/
│   │   │   │   └── (paquete para futuros componentes de seguridad)
│   │   │   ├── util/
│   │   │   │   └── QrCodeUtil.java
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

### Controladores
- `UsuarioController`
- `AuthController`
- `AdminController`
- `PedidoController`
- `SimuladorController`

### Servicios
- `UsuarioService`
- `PedidoService`

### Repositorios
- `UsuarioRepository`
- `PedidoRepository`
- `LockerRepository`

### Entidades
- `Usuario`
- `Pedido`
- `Locker`
- `Rol`
- `EstadoLocker`
- `EstadoPedido`
- `TamanoLocker`

### Utilidades
- `QrCodeUtil`
- `DataSeeder`

## Dependencias principales

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Validation
- PostgreSQL driver
- Lombok
- ZXing core/javase

## Configuración de base de datos

La conexión a PostgreSQL está definida en `src/main/resources/application.properties`.

> Nota: Para producción se recomienda mover las credenciales a variables de entorno o un vault, en lugar de dejar `spring.datasource.password` en el archivo de propiedades.

## Cómo ejecutar el proyecto

Desde la raíz del proyecto:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

Para compilar sin ejecutar:

```bash
./mvnw.cmd compile
```

## Próximos pasos sugeridos

- Completar la seguridad basada en roles y JWT
- Añadir validación de payloads y manejo de errores
- Implementar tests unitarios e integración
- Documentar los endpoints con OpenAPI / Swagger
- Añadir soporte para gestión completa de pedidos y lockers
- Mover la configuración sensible fuera del repositorio
