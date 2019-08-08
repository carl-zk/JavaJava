#!/bin/sh

mvn clean
mvn -DskipTests package
mvn failsafe:integration-test
