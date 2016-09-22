#!/usr/bin/env bash


#docker installation, just a general script for use on ubuntu, not particular to this project

sudo apt-get update
sudo apt-get upgrade -y

sudo apt-get install docker.io -y
sudo service docker start

sudo groupadd docker
sudo usermod -aG docker $USER