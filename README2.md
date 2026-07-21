# SmartPods API - Visión general

Este documento describe la estructura actual del backend de SmartPods, con los módulos principales:
- `Admin`
- `Cliente`
- `Simulador`

## Módulos principales

### 1. Admin
El módulo `Admin` expone funcionalidades de supervisión y dashboard.

Controlador:
- `AdminController` (`/api/admin`)

Endpoints:
- `GET /api/admin/dashboard`
  - Devuelve datos de dashboard sobre pedidos y estado del sistema.

Servicios asociados:
- `PedidoService`
  - Método `obtenerDashboard()` para construir la información de administración.

DTO:
- `DashboardDTO`
  - Devuelve métricas o datos agregados para el panel de administración.

### 2. Cliente
El módulo `Cliente` representa la parte de usuarios y autenticación.

Controladores:
- `UsuarioController` (`/api/usuarios`)
  - `POST /api/usuarios` registra un nuevo usuario.
  - `GET /api/usuarios` lista los usuarios existentes.

- `AuthController` (`/api/auth`)
  - `POST /api/auth/login` autentica un usuario.

Servicios asociados:
- `UsuarioService`
  - `registrar(Usuario usuario)` encripta el password y guarda el usuario.
  - `listar()` devuelve todos los usuarios.
  - `login(String correo, String password)` valida credenciales.

Entidades y DTOs clave:
- `Usuario`
  - Representa al usuario con campos como nombre, correo, password y rol.
- `LoginDTO`
  - Datos de entrada para login.
- `LoginResponseDTO`
  - Respuesta de autenticación con token demo.
- `UsuarioRegistroDTO`
  - DTO de registro de usuario.

### 3. Simulador
El módulo `Simulador` permite procesar escaneos y simular la interacción con pedidos.

Controlador:
- `SimuladorController` (`/api/simulador`)
  - `POST /api/simulador/escanear`

Servicio asociado:
- `PedidoService`
  - `procesarEscaneo(String qrData)` procesa la información del QR y devuelve el resultado.

DTOs:
- `EscaneoRequestDTO`
  - Datos de entrada para el escaneo.
- `EscaneoResponseDTO`
  - Resultado del escaneo.

## Estructura de carpetas relevante

```text
src/main/java/com/smartpods/api/
├── config/
│   ├── DataSeeder.java
│   ├── PasswordConfig.java
│   └── SecurityConfig.java
├── controller/
│   ├── AdminController.java
│   ├── AuthController.java
│   ├── PedidoController.java
│   ├── SimuladorController.java
│   └── UsuarioController.java
├── dto/
│   ├── DashboardDTO.java
│   ├── EscaneoRequestDTO.java
│   ├── EscaneoResponseDTO.java
│   ├── LoginDTO.java
│   ├── LoginResponseDTO.java
│   ├── PedidoCrearDTO.java
│   ├── PedidoResponseDTO.java
│   └── UsuarioRegistroDTO.java
├── entity/
│   ├── EstadoLocker.java
│   ├── EstadoPedido.java
│   ├── Locker.java
│   ├── Pedido.java
│   ├── Rol.java
│   ├── TamanoLocker.java
│   └── Usuario.java
├── repository/
│   ├── LockerRepository.java
│   ├── PedidoRepository.java
│   └── UsuarioRepository.java
├── service/
│   ├── PedidoService.java
│   └── UsuarioService.java
├── security/
│   └── (paquete para futuros componentes de seguridad)
├── util/
│   └── QrCodeUtil.java
└── SmartpodsApiApplication.java
```

## Estado actual

- El backend ya tiene implementados los módulos de administración, gestión de usuarios y simulador.
- La autenticación es funcional con `AuthController`, pero actualmente devuelve un token de demostración.
- La seguridad de contraseña usa `BCryptPasswordEncoder`.
- La base de datos PostgreSQL está configurada en `application.properties`.

## Observaciones

- No existe un módulo separado `cliente` de frontend dentro de este proyecto; el módulo cliente está representado por los endpoints de usuario y autenticación.
- El módulo `Admin` es ligero y actualmente solo incluye el dashboard.
- El módulo `Simulador` permite procesar QR y simular la lógica de pedidos.

## Próximo paso sugerido

- Separar claramente los módulos con paquetes y servicios dedicados.
- Agregar autenticación real (JWT o sesión) en el módulo cliente.
- Extender el dashboard admin con más métricas.
- Documentar los endpoints con OpenAPI/Swagger.
