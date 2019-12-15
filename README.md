## Kantega User Directory API Demo App

A simple user directory, exposing a few REST endpoints, using Quarkus. 

This application was made to test various cloud solutions among the main cloud providers.

Dockerfiles for creating images (jvm and native) can be found at `src/main/docker`.

### Usage
**Run quarkus dev for local development:**
```shell script
mvn compile quarkus:dev
```

**Create a native docker image:**
```shell script
mvn package -Pnative -Dquarkus.native.container-build=true
docker build -f src/main/docker/Dockerfile.native -t quarkus/user-directory-api .
```

**Create a Java image:**
```shell script
mvn package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/user-directory-api-jvm .
```
