#!/usr/bin/env bash

#!/bin/sh

build() {
    mvn install -Dmaven.test.skip=true
    docker build -t afayes/payment-api .
}

start() {
    docker-compose up
}
stop() {
    docker-compose down
}

run() {
    build
    start
}

logs() {
    docker-compose logs -f
}

case "$1" in
    build)
        build
        ;;
    start)
        start
        ;;
    stop)
        stop
        ;;
    run)
        run
        ;;
    logs)
        logs
        ;;
    *)
        echo "USAGE: $0 {build|start|stop|run}"
        echo "build: build the maven project and build the docker image"
        echo "start: start the docker containers"
        echo "stop: stop the docker containers"
        echo "run: build and start"
        echo "logs: shows output from docker containers"
esac
