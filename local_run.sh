#!/usr/bin/env bash

git pull

##build the jar
mvn clean package


##if we just wanted to run locally without docker the following is what we'd run
#java -jar ./target/thrones_db_spring-1.0-SNAPSHOT.jar thrones_db_spring.Application


##build the docker image
docker build -t thrones-server .


##this is just the exit code of previous command
exit_code=$?


if [[ ${exit_code} == 0 ]]
then
    echo "running thrones server"
    docker run -p 8080:8080 thrones-server
fi