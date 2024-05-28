## Versiones
Java 21

Springboot 3.3.0

## Ejecutar manualmente

### Levantar RabbitMQ

```
docker compose up -d rabbitmq
```

### Opcion 1. Correr el app desde el jar
```
mvn clean package -DskipTests
java -jar target/challenge-0.0.1-SNAPSHOT.jar
```

### Opcion 1.  Correr el app desde spring
```
mvn spring-boot:run
```

## Ejecutar con docker compose

```
docker compose up
```

## Ejecutar los test

```
mvn clean test
```

La Base de datos es H2 in memory

La api corre por defecto en el puerto 8080

## Openapi
http://localhost:8080/v3/api-docs

## Swagger

http://localhost:8080/swagger-ui.html

## Seguridad
* Tiene autenticacion de tipo **Basic**
* Un rol de permisos de escritura y lectura ROLE_ADMIN
* Un rol de permisos de lectura ROLE_USER
* Usuarios en memoria
  * username: **user** password: **user** role: **ROLE_USER**
  * username: **admin** password: **admin** role: **ROLE_ADMIN**

## Versionamiento de la bd (Flyway)
El versionamiento se ejecuta de manera automatica al arrancar la aplicacion.

Los script se colocan en la carpeta **resources/migracion/db/**

## Integraciones del demo
* Openapi
* Swagger
* Seguridad
* Aspectos (Para log de parametro negativo)
* Tests
* Test de integracion
* Versionamiento de la base de datos (Flyway)
* Cache de peticiones
* RabbitMQ (Solo para enviar y recibir mensaje de las peticiones en proceso)
