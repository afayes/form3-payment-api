FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Abul Fayes (afayes@hotmail.co.uk)
LABEL name="payment-api"
LABEL description="Payment API"
LABEL version="0.10"

VOLUME /tmp
COPY target/api-*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xmx2500m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
