FROM maven:3-jdk-8-alpine
RUN mkdir /code
ADD ./target/thrones_db_spring-1.0-SNAPSHOT.jar /code
WORKDIR /code

#RUN apt-get -y install sudo
#RUN apt-get -y install postgresql
#RUN update-rc.d postgresql defaults
#RUN service postgresql start && \
#	sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'gotswe';" && \
#	sudo -u postgres PGPASSWORD=gotswe createdb housedowning -h 127.0.0.1 && \
#	sudo python3 app.py --make-tables && \
#	sudo -u postgres PGPASSWORD=gotswe psql -d housedowning -a -f ./raw_data/copy.sql
#EXPOSE 5432
EXPOSE 8080
CMD java -jar ./thrones_db_spring-1.0-SNAPSHOT.jar thrones_db_spring.Application
