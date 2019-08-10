#!/bin/sh

./mvnw clean
./mvnw -DskipTests package
./mvnw failsafe:integration-test
