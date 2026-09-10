# Fact_App - Sistema de Facturación

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21.0.6-007396?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.46-red?style=for-the-badge)](https://projectlombok.org/)

Base del sistema de facturación minorista de escritorio desarrollado en JavaFX con arquitectura modular y Maven.

## Requisitos

- Java Development Kit (JDK) 21
- Apache Maven 3.8+ (o el wrapper `./mvnw` incluido)

## Estructura del Proyecto

```text
Fact_App/
├── pom.xml
├── README.md
└── src/main/
    ├── java/
    │   ├── module-info.java
    │   └── com/example/fact_app/
    │       ├── Launcher.java
    │       ├── application/
    │       │   ├── CargoApplication.java
    │       │   └── FacturacionApplication.java
    │       ├── controller/
    │       │   ├── CargoController.java
    │       │   ├── MenuPrincipalController.java
    │       │   └── ProductoController.java
    │       ├── model/
    │       │   ├── Cargo.java
    │       │   ├── Categoria.java
    │       │   ├── Empleado.java
    │       │   └── Producto.java
    │       └── util/
    │           └── SceneManager.java
    └── resources/com/example/fact_app/
        ├── fxml/
        │   ├── cargo-view.fxml
        │   ├── menu-principal.fxml
        │   └── producto-view.fxml
        ├── icons/
        │   ├── agregar.png
        │   └── cerrar.png
        └── images/
            └── logo.png
```

## Modelos del Dominio

- **Cargo**: Identificador, nombre y descripción del puesto laboral.
- **Categoria**: Categorías clasificatorias de los productos del inventario.
- **Empleado**: Información personal, cargo asignado y estado laboral.
- **Producto**: Catálogo de artículos, código de barras, categoría, precio de venta, existencia y estado.

## Compilación y Ejecución

Compilar el proyecto:

```bash
./mvnw clean compile
```

Ejecutar la aplicación:

```bash
./mvnw javafx:run
```
