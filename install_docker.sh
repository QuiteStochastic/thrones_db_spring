#!/usr/bin/env bash


#docker installation, just a general script for use on ubuntu, not particular to this project

sudo apt-get update -y
sudo apt-get upgrade -y

sudo apt-get install docker.io -y
sudo service docker start

sudo groupadd docker
sudo usermod -aG docker $USER

sudo curl -L "https://github.com/docker/compose/releases/download/1.9.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/bin/docker-compose
