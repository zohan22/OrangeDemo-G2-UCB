# OrangeDemo

Proyecto de automatizacion web con Selenium WebDriver, TestNG y Page Object
Model para OrangeHRM Demo.

## Tecnologias

- Java 21
- Maven
- Selenium WebDriver
- TestNG
- Gson
- Log4j2
- Chrome y Firefox

## Flujo automatizado

El caso principal crea y verifica empleados en OrangeHRM:

1. Ingresa a OrangeHRM Demo.
2. Inicia sesion con el usuario de demostracion.
3. Accede al modulo PIM.
4. Abre el formulario Add Employee.
5. Completa nombre, segundo nombre, apellido y datos de usuario.
6. Activa la creacion de datos de login y guarda el empleado.
7. Obtiene el ID generado por OrangeHRM.
8. Busca el empleado por su ID.
9. Verifica que aparece en la grilla de resultados.

La prueba se ejecuta para dos empleados distintos. El nombre, apellido y
usuario reciben un sufijo unico generado durante la ejecucion para evitar
colisiones entre corridas.

## Datos de prueba

Los datos no estan escritos dentro del test. Se cargan mediante Gson y
`JsonTestDataHelper`:

```text
src/test/resources/testdata/
├── employee/
│   └── employeeData.json
└── login/
    └── loginInvalidData.json
```

Los modelos de datos se encuentran en:

```text
src/main/java/org/ecommerce/models/Employee.java
src/main/java/org/ecommerce/helpers/JsonTestDataHelper.java
```

Para agregar otro empleado, se agrega una nueva fila en
`employeeData.json`, sin modificar el test.

## Page Object Model

Los locators y las acciones de Selenium estan encapsulados en las paginas.
El test contiene solamente el flujo de negocio y las aserciones.

`PIMPage` concentra las operaciones del modulo PIM, incluyendo la navegacion
a la lista, la busqueda y la verificacion del empleado. No existe una pagina
separada para la lista de empleados.

## Requisitos

- JDK 21 o superior
- Maven
- Google Chrome y/o Mozilla Firefox
- Conexion a internet
- WebDriver disponible en el PATH o gestionado por Selenium Manager

## Instalacion y compilacion

```bash
mvn clean compile
```

Para validar la compilacion sin ejecutar los navegadores:

```bash
mvn test -DskipTests
```

## Ejecucion

La suite `regression.xml` ejecuta los paquetes `employees` y `login` en Chrome
y Firefox:

```bash
mvn test -DsuiteXmlFiles=regression.xml
```

La suite tambien puede abrirse desde TestNG o IntelliJ. El navegador se envia
como parametro y `BaseTest` crea el driver correspondiente.

## Estructura principal

```text
src/
├── main/
│   ├── java/
│   │   ├── org/ecommerce/helpers/JsonTestDataHelper.java
│   │   ├── org/ecommerce/models/Employee.java
│   │   ├── pages/
│   │   │   ├── BasePage.java
│   │   │   ├── EmployeePage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── PIMPage.java
│   │   │   └── SideMenuPage.java
│   │   └── reports/ReportManager.java
│   └── resources/log4j2.properties
└── test/
    ├── java/
    │   ├── base/BaseTest.java
    │   ├── employees/EmployeeTest.java
    │   └── login/LoginTest.java
    └── resources/testdata/
```

La suite se encuentra en la raiz del proyecto:

```text
regression.xml
```

Los logs se escriben en `target/logs/automation.log`.

## Credenciales

El proyecto utiliza las credenciales de demostracion de OrangeHRM:

```text
Usuario: Admin
Contraseña: admin123
```

Sitio utilizado:

https://opensource-demo.orangehrmlive.com/
