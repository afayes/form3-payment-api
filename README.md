# Form3 Payment API

## Overview
This Form3 Payment API is built using Java 8, Spring Boot, HATEOAS, Couchbase, and Docker

## Requirements
- Java 8+
- Maven (tested on v 3.5)
- Docker (tested on 17.09.0-ce)

## Quick start
Clone the project from github
```
git clone git@github.com:afayes/form3-payment-api.git
```
To build and run the project execute
```
./run.sh run
```

navigate to ```http://localhost:8080/swagger-ui.html``` on your browser to use the API through Swagger

## Design
The PDF document of the original design with REST API and class diagram is available [here](Form3 Payment system design.pdf)

## Tests
You can run all unit and integration tests by executing
```
mvn clean verify
```
A Maven plugin has been used to start the Couchbase Docker container before the integration tests are run and also for stopping the container after the tests have finished, have a look at [pom.xml](pom.xml). I have customised an
existing Couchbase Docker container for the purposes of the project. It extends the base container by finding an open port and bootstrapping a _payment_ bucket with the port. See [here](resources/couchbase-server) for more details.

## Useful Commands
A [run.sh](run.sh) Bash script has been written to provide useful commands for the project. It is executed in the following way:

``` ./run.sh {build|start|stop|run}```

- build: build the maven project and build the docker image"

- start: start the docker containers

- stop: stop and remove the docker containers

- run: build and start

- logs: shows log output from docker containers

