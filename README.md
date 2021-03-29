# README

Api de cotización de divisas.

Aplicación realizada usando arquitectura limpia con spring boot y base de datos postgresql. Adicional a eso se configura un proxy reverse llamado traefik.

**Nota:** Con el api-key del api fixer, solo se puede consultar no se puede consultar algunos paises.

## pre-requisitos

Se debe tener instalado los siguientes programas

1. Gradle
2. Java jdk-11
3. Docker

## Tecnologias usadas

1. Spring
2. Traefik
3. Postgres

## 1. Compilar

Se debe ejecutar este para compilar la aplicación.

```bash
    gradle build
```

### Variables

- API_fixer-key: Api key del api [fixer.io](https://fixer.io)

## 2. Despliegue

Para montar la aplicación en docker se ejecuta lo siguiente:

```bash
    docker-compose up -d --build
```

para consumir la aplicación se debe usar el dominio moneda.docker.localhost.

- Endpoints expuestos: http://moneda.docker.localhost/api/swagger-ui.html

## 3. Ejecución de pruebas unitarias

```bash
    gradle test
```

## 4. Ejecución de pruebas de integracion

```bash
    gradle integracion
```
