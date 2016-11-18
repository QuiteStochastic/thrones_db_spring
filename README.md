# thrones_db_spring

To run, make sure you have the following installed
- Java 8
- docker [./install_docker.sh might help you properly install this]
- maven

To run the site locally, run `./local_run.sh`, that's it.  it will build the spring applicaiton, put it on a docker image, and run the docker image as a container on your local machine.

To terminate the site, you should go learn how to use docker, but to put it simply, use `docker ps` to find the container you just ran and then use `docker rm -f` to terminate it.  to keep things clean, use `docker rmi` to remove the image.

The `./remote_run.sh` allows you to run it on a remote server.  It will build the image but then instead of running it locally, the script will ssh into a remote server, send the image to the remote server, it run it there.  This is not the standard way of sending a docker image to a remote box, nor is it particularly efficient but it works.  You will need to create a file in the root level of the repo called `config.txt`.  It must be a text file with 3 lines.  line 1 is the location of the .pem file (or whatever you're using as a key) to access the server.  line 2 is the username of the remote server.  line 3 is the ip of the remote server. 
