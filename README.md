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
The PDF document of the original design with REST API and class diagram is available [here](design.pdf)

## Tests
You can run all unit and integration tests by executing
```
mvn clean verify
```
A Maven plugin has been used to start the Couchbase Docker container before the integration tests are run and also for stopping the container after the tests have finished, have a look at [pom.xml](pom.xml). 

## Some Implementation Details
When the Spring Context is starting, Spring Data Couchbase requires that Couchbase is running and ready and has a bucket pre configured for connection. For the purposes of this project, I created a custom image
for Couchbase Docker. I modified an existing Dockerfile by bootstrapping a _payment_ bucket. See [here](resources/couchbase-server-docker) for more details. I also created a 
(service)[src/main/java/com/form3/payment/service/CouchbaseHealthCheckService.java] that uses REST calls to check that the Couchbase bucket is ready for connection before Spring connects to it.

## Useful Commands
A [run.sh](run.sh) Bash script has been written to provide useful commands for the project. It is executed in the following way:

``` ./run.sh {build|start|stop|run}```

- build: build the maven project and build the docker image"

- start: start the docker containers

- stop: stop and remove the docker containers

- run: build and start

- logs: shows log output from docker containers

