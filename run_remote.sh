#/bin/bash


IFS=$'\n' read -d '' -r -a configs_array < ./config.txt

key_path=${configs_array[0]}
echo "key_path: $key_path"

remote_user=${configs_array[1]}
echo "remote_user: $remote_user"

remote_ip=${configs_array[2]}
echo "remote_ip: $remote_ip"



mvn clean package
#java -jar ./target/thrones_db_spring-1.0-SNAPSHOT.jar thrones_db_spring.Application

docker build -t thrones-server .
docker save thrones-server | bzip2 | ssh -i "$key_path" "$remote_user"@"$remote_ip" 'bunzip2 | docker load; docker run -d -p 8080:8080 thrones-server'