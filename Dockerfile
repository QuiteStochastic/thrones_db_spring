FROM ubuntu:16.04
RUN mkdir /code
WORKDIR /code

RUN apt-get -y update && \
    apt-get -y upgrade

RUN apt-get -y update && \
    apt-get -y install openjdk-8-jre

ADD ./target/thrones_db_spring-1.0-SNAPSHOT.jar /code

EXPOSE 80
CMD java -jar ./thrones_db_spring-1.0-SNAPSHOT.jar thrones_db_spring.Application --server.port=80
