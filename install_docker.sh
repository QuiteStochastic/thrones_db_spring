#/bin/bash

#docker installation, just a general script for use on ubuntu, not particular to this project

sudo apt-get update
sudo apt-get upgrade

sudo apt-get install docker.io
sudo service docker start

sudo groupadd docker
sudo usermod -aG docker $USER