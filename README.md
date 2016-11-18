# thrones_db_spring

To run, make sure you have the following installed
- Java 8
- docker [./install_docker.sh might help you properly install this]
- maven

To run the site locally, run `./local_run.sh`, that's it.  it will build the spring applicaiton, put it on a docker image, and run the docker image as a container on your local machine.

To terminate the site, you should go learn how to use docker, but to put it simply, use `docker ps` to find the container you just ran and then use `docker rm -f` to terminate it.  to keep things clean, use `docker rmi` to remove the image.

