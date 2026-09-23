# OrangeDemo

Proyecto de automatizacion web con Selenium WebDriver para OrangeHRM Demo.

## Tecnologias

- Java 21
- Maven
- Selenium WebDriver 4.48.0
- Google Chrome

## Flujo automatizado

El programa realiza las siguientes acciones:

1. Ingresa a OrangeHRM Demo.
2. Inicia sesion con el usuario de demostracion.
3. Accede al modulo PIM.
4. Registra un nuevo empleado.
5. Busca al empleado por su ID.

## Requisitos

- JDK 21 o superior
- Maven
- Google Chrome
- Conexion a internet

## Instalacion

```bash
mvn clean compile
```

## Ejecucion

Ejecutar la clase principal:

```text
src/main/java/orange/OrangeRunner.java
```

Clase principal:

```text
orange.OrangeRunner
```

Tambien puede ejecutarse desde el IDE como una aplicacion Java.

## Estructura del proyecto

```text
src/main/java/
├── orange/
│   └── OrangeRunner.java
└── pages/
    ├── BasePage.java
    ├── EmployeePage.java
    ├── LoginPage.java
    ├── PIMPage.java
    └── SideMenuPage.java
```

## Credenciales

El proyecto utiliza las credenciales de demostracion de OrangeHRM:

```text
Usuario: Admin
Contraseña: admin123
```

Sitio utilizado:

https://opensource-demo.orangehrmlive.com/
