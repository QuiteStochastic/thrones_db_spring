FROM ubuntu:16.04
RUN mkdir /code
WORKDIR /code

RUN apt-get -y update && \
    apt-get -y upgrade && \
    apt-get -y install apt-utils

RUN apt-get -y update && \
    apt-get -y install openjdk-8-jre && \
    apt-get -y install sudo
RUN apt-get -y install postgresql
RUN update-rc.d postgresql defaults
ADD ./src/main/resources/db/migration/ /code/data
RUN service postgresql start && \
	sudo -u postgres psql -c "CREATE USER thrones_db_user PASSWORD 'kingsguard';" && \
	sudo -u postgres psql -c "CREATE DATABASE thronesdb_db WITH OWNER = thrones_db_user CONNECTION LIMIT = -1;" && \
	sudo -u postgres psql -d thronesdb_db -c "CREATE SCHEMA thrones_db_schema AUTHORIZATION thrones_db_user;" && \
	sudo -u postgres psql -d thronesdb_db -c "ALTER ROLE thrones_db_user SET search_path = thrones_db_schema;" && \
	#export PGPASSWORD=kingsguard && \
	#sudo touch /code/.pgpass && \
	#sudo echo "*:*:*:thrones_db_user:kingsguard" > /code/.pgpass && \
	#export PGPASSFILE=/code/.pgpass && \
	#echo "$PGPASSFILE" && \
	#echo "$PGPASSWORD" && \

	sudo -u postgres psql -d thronesdb_db -c "CREATE TABLE thrones_db_schema.episode ( epId serial PRIMARY KEY NOT NULL, name character varying(500), season int,episodeNumber int, description text);" && \
    sudo -u postgres psql -d thronesdb_db -c "ALTER TABLE thrones_db_schema.episode OWNER TO thrones_db_user;" && \
    sudo -u postgres psql -d thronesdb_db -c "COPY thrones_db_schema.episode FROM '/code/data/episode.csv' delimiter ',' csv;" && \

    rm -rf /code/data

	#sudo -u postgres psql -d thronesdb_db -c "insert into thrones_db_schema.episode (epId, name, season, episodeNumber, description) values (1,'test1-1',1,1,'describe1-1'), (2,'test1-2',1,2,'describe1-2'), (3,'test2-1',2,11,'describe2-1'), (4,'test2-2',2,12,'describe2-2');"


ADD ./target/thrones_db_spring-1.0-SNAPSHOT.jar /code

#EXPOSE 5432
EXPOSE 8080
CMD service postgresql start && \
    java -jar ./thrones_db_spring-1.0-SNAPSHOT.jar thrones_db_spring.Application
