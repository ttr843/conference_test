# Conference 

This simple project for waveaccess test task.

* [Getting Started](#getting-started)
* [Development environment](#development-environment)
* [Running the tests](#running-the-tests)
* [Used technologies](#used-technologies)

## Getting Started

You must to build spring-boot-application using the following command :

```bash
./mvn clean package 
```

## Development environment

To launch development environment for this application you should install [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/install/).

We then put this executable JAR into the Docker image by running the docker build command from the root of the project containing the Dockerfile:


To build docker image of this example application, use the following command:

```bash
 docker build .-t conference:v1.
```
To run the docker containers, change directory to the `dev-env` and run:

```bash
docker-compose up
```

Or you can just use the following command:

```bash
./mvnw spring-boot:build-image
```

## Running the tests

To run tests, use the following commands:

```bash
./mvn test
```

## Used technologies

##### For back-end:

* [Spring Boot](https://spring.io/projects/spring-boot) 
* [Java](https://www.oracle.com/ru/java/technologies/)
* [Maven](https://maven.apache.org/)

##### For front-end:

* [Freemarker](https://freemarker.apache.org)


##### For database connectivity:

* [H2](https://www.h2database.com/html/main.htmlc)
* [Liquibase](https://www.liquibase.org)


##### For Tests:

* [JUnit](https://junit.org/junit4/)
* [Mockito](https://site.mockito.org)


