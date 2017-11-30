#!/usr/bin/env bash

#!/bin/sh

build() {
    mvn install -Dmaven.test.skip=true
    docker build -t afayes/payment-api .
}

start() {
    docker-compose up -d
    docker-compose logs -f
}
stop() {
    docker-compose down
}

run() {
    build
    start
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
    *)
        echo "USAGE: $0 {build|start|stop|run}"
        echo "build: build the maven project and build the docker image"
        echo "start: start the docker containers"
        echo "stop: stop the docker containers"
        echo "run: build and start"
esac
