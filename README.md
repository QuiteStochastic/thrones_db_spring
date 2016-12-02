# thrones_db_spring

To run, make sure you have the following installed
- Java 8
- docker [./install_docker.sh might help you properly install this]
- maven

To run the site locally, run `./local_run.sh`, that's it.  The script will build the spring applicaiton, put the spring application on a docker image, and run the docker image as a container on your local machine.

To terminate the site, you should go learn how to use docker, but to put it simply, use `docker ps` to find the container you just ran and then use `docker rm -f` to terminate it.  to keep things clean, use `docker rmi` to remove the image.

The `./remote_run.sh` allows you to run the application on a remote server.  The script will build the image but then instead of running the image as a container locally, the script will ssh into a remote server, copy the image to the remote server, then run the image as a container on the remote server.  This is not the standard way of sending a docker image to a remote box (that would be using a Docker Registry), nor is it particularly efficient but it works.  To run this scriot, you will need to create a file in the root level of the repo called `config.txt`.  This file must be a text file with 3 lines.  line 1 is the location of the .pem file (or whatever you're using as a key) to access the server.  line 2 is the username of the remote server.  line 3 is the ip of the remote server.  The `config.txt` file is git-ignored.  The remote server needs to have docker pre-installed on it, it also of course must be set up with ssh, it requires no other configuration.
