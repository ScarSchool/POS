#!/bin/bash

cnt=1
lim=5      

bin='./target'

DockerComposeFileName='docker-compose.yml'

# Docker container names
DockerDatabaseContainer='SpringBootJPADB'
DockerWebserviceContainer='SpringBootApp'

# Docker image names
DockerJPAImageName='springbootappjpa'

# Do not change values below this line
# ------------------------------------

# Replacing target folder
if [ -d $bin ]; then
	echo "$bin exists. Removing..."
	rm -r $bin
fi

# Starting the database
echo;echo "$cnt/$lim Starting the mysql database..."; cnt=$[$cnt+1]
docker container start $DockerDatabaseContainer
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

# Compiling spring boot and creating target
echo;echo "$cnt/$lim Compiling the spring-boot webservice..."; cnt=$[$cnt+1]
mvn package
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

# Building the docker container
echo;echo "$cnt/$lim Building the docker container..."; cnt=$[$cnt+1]
docker build -t springdatajpa .
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

# Starting docker containers
echo;echo "$cnt/$lim Starting the docker containers..."; cnt=$[$cnt+1]
docker-compose up
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi


firefox localhost:8080/api/admins