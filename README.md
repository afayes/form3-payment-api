# Form3 Payment API

## Overview
This Form3 Payment API is built using Java 8, Spring Boot, HATEOAS, Couchbase, Docker and SWAGGER. A TDD approach was followed using integration tests to drive the implementation.

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
[service](src/main/java/com/form3/payment/service/CouchbaseHealthCheckService.java) that uses REST calls to check that the Couchbase bucket is ready for connection before Spring connects to it.

## Useful Commands
A [run.sh](run.sh) Bash script has been written to provide useful commands for the project. It is executed in the following way:

``` ./run.sh {build|start|stop|run}```

- build: build the maven project and build the docker image"

- start: start the docker containers

- stop: stop and remove the docker containers

- run: build and start

- logs: shows log output from docker containers

## TODO
- For integration tests, bootstrap the Couchbase docker container possible using
[TestExecutionListener](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/context/TestExecutionListener.html). This allows you to easily execute integration tests from 
within your IDE and not just Maven. I tried this approach initially using a Java Docker client but took too long to try and get it working and so resorted to the simpler Maven approach.
- Bootstrap Couchbase bucket from within the application code, that way the docker container is decoupled from the application's data requirements
- Add more unit tests/integration tests for classes other than the PaymentController
- Based on the original design, implement the root controller which returns the root HATEOAS links. Would need more time to implement this.

