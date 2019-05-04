#!/bin/bash

set -exu

mvn clean && mvn install

echo """
open three terminal and execute:
java -jar -Dserver.port=1111 eureka/target/eureka-0.0.1-SNAPSHOT.jar
java -jar -Dserver.port=8081 hello-service-core/target/hello-service-core-0.0.1-SNAPSHOT.jar
java -jar -Dserver.port=9000 hello-service-consumer/target/hello-service-consumer-0.0.1-SNAPSHOT.jar
"""


