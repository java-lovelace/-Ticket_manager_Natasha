# Sistema de Gestión de Tickets

Sistema de gestión de tickets de soporte técnico desarrollado en Java con interfaz gráfica mediante JOptionPane y conexión a base de datos MySQL. Implementa roles de usuario (Reporter y Assignee) con funcionalidades específicas para cada uno.

## 📋 Descripción

Aplicación de escritorio para administrar tickets de soporte técnico que permite:
- Registro e inicio de sesión de usuarios
- Creación de tickets por usuarios Reporter
- Asignación automática de tickets a usuarios Assignee
- Consulta de tickets por estado y categoría
- Visualización de estadísticas (Top 3 categorías)
- Gestión basada en roles

## 🛠️ Tecnologías Utilizadas

- **Java** (JDK 8 o superior)
- **MySQL** (Base de datos)
- **JDBC** (Conector MySQL)
- **Swing** (JOptionPane para interfaz gráfica)
- **Arquitectura en capas** (Controller, Service, DAO, Domain)

## 📂 Estructura del Proyecto

    src/
    ├── Main.java
    ├── Controller/
    │   ├── LogOut.java
    │   └── TicketController.java
    ├── config/
    │   └── ConfigDB.java
    ├── dao/
    │   ├── CRUD.java
    │   ├── CategoriaDao.java
    │   ├── ComentarioDao.java
    │   ├── EstadoDao.java
    │   ├── TicketDao.java
    │   ├── UsuarioDao.java
    │   └── impl/
    │       ├── EstadoDaoImpl.java
    │       ├── TicketDaoImpl.java
    │       └── UsuarioDaoImpl.java
    ├── domain/
    │   ├── Categoria.java
    │   ├── Comentario.java
    │   ├── Estado.java
    │   ├── Rol.java
    │   ├── Ticket.java
    │   └── Usuario.java
    ├── service/
    │   ├── TicketService.java
    │   ├── TicketServiceImpl.java
    │   ├── UsuarioService.java
    │   └── UsuarioServiceImpl.java
    └── view/
        ├── LoginRegister.java
        ├── MenuAssignee.java
        └── MenuReporter.java

## 🗂️ Arquitectura

El proyecto implementa una arquitectura en capas:

- **Domain**: Entidades del sistema (Usuario, Ticket, Rol, Estado, Categoria, Comentario)
- **DAO (Data Access Object)**: Interfaces y implementaciones para acceso a datos
- **Service**: Lógica de negocio y validaciones
- **Controller**: Coordinación entre vista y servicios
- **View**: Interfaces de usuario con JOptionPane
- **Config**: Configuración de conexión a base de datos

## 📊 Modelo de Datos

### Tabla: Usuarios

| Campo      | Tipo        | Descripción                |
|------------|-------------|----------------------------|
| id_usuario | INT         | Identificador único (PK)   |
| nombre     | VARCHAR     | Nombre del usuario         |
| correo     | VARCHAR     | Correo electrónico (único) |
| pass       | VARCHAR     | Contraseña                 |
| id_rol     | INT         | Referencia al rol (FK)     |

### Tabla: Tickets

| Campo         | Tipo        | Descripción                     |
|---------------|-------------|---------------------------------|
| id_ticket     | INT         | Identificador único (PK)        |
| titulo        | VARCHAR     | Título del ticket               |
| descripcion   | TEXT        | Descripción detallada           |
| reporter_id   | INT         | Usuario que reporta (FK)        |
| assignee_id   | INT         | Usuario asignado (FK)           |
| id_categoria  | INT         | Categoría del ticket (FK)       |
| id_estado     | INT         | Estado actual (FK)              |
| created_at    | TIMESTAMP   | Fecha de creación               |
| updated_at    | TIMESTAMP   | Última actualización            |

### Tabla: Roles

| Campo   | Tipo    | Descripción              |
|---------|---------|--------------------------|
| id_rol  | INT     | Identificador único (PK) |
| nombre  | VARCHAR | Nombre del rol           |

### Tabla: Estados

| Campo     | Tipo    | Descripción              |
|-----------|---------|--------------------------|
| id_estado | INT     | Identificador único (PK) |
| nombre    | VARCHAR | Nombre del estado        |

### Tabla: Categorias

| Campo        | Tipo    | Descripción              |
|--------------|---------|--------------------------|
| id_categoria | INT     | Identificador único (PK) |
| nombre       | VARCHAR | Nombre de la categoría   |

### Tabla: Comentarios

| Campo          | Tipo      | Descripción              |
|----------------|-----------|--------------------------|
| id_comentario  | INT       | Identificador único (PK) |
| id_ticket      | INT       | Ticket asociado (FK)     |
| id_usuario     | INT       | Usuario que comenta (FK) |
| text           | TEXT      | Contenido del comentario |
| created_at     | TIMESTAMP | Fecha de creación        |

## ⚙️ Configuración

### Requisitos Previos

1. Java JDK 8 o superior instalado
2. MySQL Server instalado y en ejecución
3. Driver JDBC de MySQL (mysql-connector-java)

### Configuración de Base de Datos

Edita el archivo `src/config/ConfigDB.java` con tus credenciales:

    String url = "jdbc:mysql://tu-servidor:puerto/ticket_manager";
    String user = "tu-usuario";
    String password = "tu-contraseña";

### Script de Base de Datos

Ejecuta el siguiente script SQL para crear las tablas necesarias:

    CREATE DATABASE IF NOT EXISTS ticket_manager;
    USE ticket_manager;

    CREATE TABLE Roles (
        id_rol INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(50) NOT NULL UNIQUE
    );

    CREATE TABLE Usuarios (
        id_usuario INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
        correo VARCHAR(100) NOT NULL UNIQUE,
        pass VARCHAR(255) NOT NULL,
        id_rol INT NOT NULL,
        FOREIGN KEY (id_rol) REFERENCES Roles(id_rol)
    );

    CREATE TABLE Estados (
        id_estado INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(50) NOT NULL UNIQUE
    );

    CREATE TABLE Categorias (
        id_categoria INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL UNIQUE
    );

    CREATE TABLE Tickets (
        id_ticket INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(200) NOT NULL,
        descripcion TEXT NOT NULL,
        reporter_id INT NOT NULL,
        assignee_id INT,
        id_categoria INT NOT NULL,
        id_estado INT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        FOREIGN KEY (reporter_id) REFERENCES Usuarios(id_usuario),
        FOREIGN KEY (assignee_id) REFERENCES Usuarios(id_usuario),
        FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria),
        FOREIGN KEY (id_estado) REFERENCES Estados(id_estado)
    );

    CREATE TABLE Comentarios (
        id_comentario INT AUTO_INCREMENT PRIMARY KEY,
        id_ticket INT NOT NULL,
        id_usuario INT NOT NULL,
        text TEXT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (id_ticket) REFERENCES Tickets(id_ticket) ON DELETE CASCADE,
        FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
    );

    INSERT INTO Roles (nombre) VALUES ('Reporter'), ('Assignee');
    INSERT INTO Estados (nombre) VALUES ('Abierto'), ('EN_PROCESO'), ('CERRADO');
    INSERT INTO Categorias (nombre) VALUES ('Infraestructura'), ('Aplicación'), ('Cuenta');

## 🚀 Instalación y Ejecución

### 1. Clonar o descargar el proyecto

    git clone <url-del-repositorio>
    cd ticket-manager

### 2. Agregar el Driver JDBC de MySQL

Descarga el driver desde MySQL Connector/J y agrégalo al classpath del proyecto.

### 3. Configurar la base de datos

Ejecuta el script SQL proporcionado en tu servidor MySQL y actualiza las credenciales en `ConfigDB.java`.

### 4. Compilar el proyecto

    javac -d bin src/**/*.java

### 5. Ejecutar la aplicación

    java -cp bin Main

O desde tu IDE favorito (IntelliJ IDEA, Eclipse, NetBeans):
- Abre el proyecto
- Configura el driver JDBC en las dependencias
- Ejecuta la clase `Main.java`

## 💻 Uso del Sistema

### Sistema de Autenticación

Al iniciar la aplicación, se muestra el menú de login:

    1. Iniciar Sesión
    2. Registrarse
    3. Salir

### Registro de Usuario

Los nuevos usuarios se registran automáticamente con el rol de **Reporter**.

### Roles y Funcionalidades

#### 👤 Reporter (Rol 1)
- Crear tickets de soporte
- Los tickets se asignan automáticamente a un Assignee disponible
- Seleccionar categoría (Infraestructura, Aplicación, Cuenta)
- Los tickets inician con estado "Abierto"

Menú Reporter:

    1. Crear Ticket
    2. Salir

#### 👨‍💻 Assignee (Rol 2)
- Buscar tickets por estado y categoría
- Listar tickets asignados
- Ver estadísticas del top 3 de categorías más reportadas

Menú Assignee:

    1. Buscar estado + Categoria
    2. Listar por asignado
    3. Top 3 categorias
    4. Salir

## 🔧 Funcionalidades Principales

### TicketController
- `crecion()`: Crea un nuevo ticket y lo asigna aleatoriamente a un Assignee
- `EncontrarCategoriaEstado()`: Busca tickets por estado y categoría específicos
- `mostrarlistado()`: Lista todos los tickets con información de asignación
- `mostrarTop()`: Muestra las 3 categorías con más tickets

### TicketService
- `crear(Ticket)`: Valida y crea un nuevo ticket
- `asignarTicket(int, int)`: Asigna un ticket a un usuario específico
- `cambiarEstado(int)`: Cambia el estado del ticket (Abierto → En Proceso → Cerrado)
- `EncontrarTickets(String, String)`: Busca tickets por estado y categoría
- `obtenerTop()`: Obtiene estadísticas de categorías

### UsuarioService
- `login(String, String)`: Autentica usuarios por correo y contraseña
- `registrar(Usuario)`: Registra nuevos usuarios con validaciones
- `listaDeUsuariosAssinee()`: Obtiene usuarios con rol de Assignee

## 📈 Características Especiales

### Asignación Automática
Los tickets se asignan automáticamente de forma aleatoria entre los usuarios con rol Assignee disponibles en el sistema.

### Estados del Ticket
- **Abierto**: Estado inicial al crear el ticket
- **EN_PROCESO**: Ticket siendo trabajado
- **CERRADO**: Ticket resuelto

### Categorías Disponibles
- Infraestructura
- Aplicación
- Cuenta

## 🔐 Seguridad

**⚠️ IMPORTANTE**: Este proyecto almacena credenciales en texto plano. Para uso en producción:

1. Implementar encriptación de contraseñas (BCrypt, SHA-256)
2. Utilizar variables de entorno para credenciales de BD
3. Implementar validación de entrada para prevenir SQL Injection
4. Agregar sistema de tokens o sesiones
5. Implementar auditoría de acciones


## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la Licencia MIT.



---

**Desarrollado con ☕ y Java | Sistema de Tickets v1.0**
