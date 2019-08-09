#!/bin/sh
set -exu

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

ROOT=${SCRIPT_DIR}/..

pushd ${ROOT}

mvn clean
mvn compile
mvn -DskipTests package

mv ./target/web*.jar ./docker/app.jar

pushd ./docker

docker build -t app:latest .

popd
popd
