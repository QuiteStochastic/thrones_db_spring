#/bin/bash




##build the jar
#mvn clean package


##if we just wanted to run locally without docker the following is what we'd run
#java -jar ./target/thrones_db_spring-1.0-SNAPSHOT.jar thrones_db_spring.Application

##build the docker image
docker build -t thrones-server .

##this is just the exit code of previous command
exit_code=$?;






if [[ $exit_code == 0 ]]
then
    ##read in the IP and username of remote cloud service host from config.txt.  also read in the local path of the key needed to ssh access the remote host
    IFS=$'\n' read -d '' -r -a configs_array < ./config.txt

    key_path=${configs_array[0]}
    echo "key_path: $key_path"

    remote_user=${configs_array[1]}
    echo "remote_user: $remote_user"

    remote_ip=${configs_array[2]}
    echo "remote_ip: $remote_ip"

    ##send the docker image to remote host and run the new image on the remote host

    docker save thrones-server | bzip2 | ssh -i "$key_path" "$remote_user"@"$remote_ip" 'bunzip2 | docker load; docker run -d -p 8080:8080 thrones-server'
fi



