#!/bin/sh
set -eux

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

ROOT=${SCRIPT_DIR}/..

pushd ${ROOT}

./mvnw clean
./mvnw compile
./mvnw -DskipTests package

mv ./target/web*.jar ./docker/app.jar

pushd ./docker

docker build -t app:latest .

docker-compose up -d

popd
popd
