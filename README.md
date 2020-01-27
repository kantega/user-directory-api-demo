## Kantega User Directory API Demo App

A simple user directory, exposing a few REST endpoints, using Quarkus. 

This application was made to test various cloud solutions among the main cloud providers.

Dockerfiles for creating images (jvm and native) can be found at `src/main/docker`.

### Usage
#### Run quarkus dev for local development:
```shell script
mvn compile quarkus:dev
```

This will use H2 as DB for local development.

#### Run quarkus locally with `docker-compose`:
Requires `docker` and `docker-compose`. 
```shell script
docker-compose up --build
```

#### Run quarkus dev for local development with PostgreSQL:
Start postgres with docker-compose:
```shell script
docker-compose up database
```

Run `mvn compile quarkus:dev` with the following environment variables:
```shell script
QUARKUS_DATASOURCE_DRIVER=org.postgresql.Driver
QUARKUS_DATASOURCE_URL=jdbc:postgresql://localhost:5432/user-api
QUARKUS_DATASOURCE_USERNAME=postgres
QUARKUS_DATASOURCE_PASSWORD=localpass
```

#### Create a native docker image:
```shell script
mvn package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t quarkus/user-directory-api .
```

#### Create a Java docker image:
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/user-directory-api-jvm .
```

This expects Postgres database `user-api` to be available at host `postgres`, with username `postgres` and password `localpass`.
These values can be configured using environment variables:

```shell script
POSTGRES_HOST=postgres
POSTGRES_DB=user-api
POSTGRES_USER=postgres
POSTGRES_PASSWORD=localpass
POSTGRES_PORT=5432
```

#### Production:

For production the variables mentioned in the note on the Java docker image should be configured. 
