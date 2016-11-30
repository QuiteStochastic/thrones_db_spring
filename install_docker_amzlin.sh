sudo yum update
sudo yum upgrade -y

sudo yum install docker -y
sudo service docker start

sudo groupadd docker
sudo usermod -aG docker $USER
