FROM java:openjdk-8-jre

MAINTAINER Swapnil Dahiphale <swapnil2233@yahoo.com>
WORKDIR /opt
COPY target/grpc-client-1.0-SNAPSHOT.jar .
CMD java -jar grpc-client-1.0-SNAPSHOT.jar