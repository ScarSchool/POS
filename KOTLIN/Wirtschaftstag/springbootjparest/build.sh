#!/bin/bash

# -----------------------------------------------------------------------------------
# Launches the spring boot JPA webservice in a docker container with a mysql database
# -----------------------------------------------------------------------------------

cnt=1       # This is for better user ouput
lim=6       # this also

# Source folders
src='./springdatajpa'
bin='./target'

DockerComposeFileName='docker-compose.yml'

# Docker container names
DockerDatabaseContainer='SpringDataDB'
DockerWebserviceContainer='SpringDataJPA'

# Docker image names
DockerJPAImageName='springbootappjpa'

# Information on where to find the webservice
WebserverProtocol='http'
WebserverHostname='localhost'
WebserverPort='8080'

WebserverQueriesLimit=20	# How many queries will occur before the loop will abort
WebserverQueriesTimeout=0.5	# seconds

# ------------------------------------
# Do not change values below this line
# ------------------------------------


# Changing context
echo "Changing context to $src"
cd $src

# Replacing target folder
if [ -d $bin ]; then
	echo "$bin exists. Removing..."
	rm -r $bin
fi

# Starting the database
# The db needs to run so the webapp can be packaged properly
echo;echo "$cnt/$lim Starting the mysql database..."; cnt=$[$cnt+1]
	docker container start $DockerDatabaseContainer
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

# Compiling spring boot and creating target
echo;echo "$cnt/$lim Compiling the spring-boot webservice..."; cnt=$[$cnt+1]
	mvn package
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

# Building the docker container
echo;echo "$cnt/$lim Building the docker container..."; cnt=$[$cnt+1]
	docker build -t $DockerJPAImageName .
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

# Starting docker containers
echo;echo "$cnt/$lim Starting the docker containers..."; cnt=$[$cnt+1]
	docker-compose up -d
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

resCode=
i=0
# Waiting for the webserver to go online
echo;printf "$cnt/$lim Pinging the webserver..."; cnt=$[$cnt+1]		# Using printf so next output is on same line
	while [ $i -lt $WebserverQueriesLimit ] && [[ $resCode -ne 200 ]];
	do
		printf "."
		resCode=$(curl -sI $WebserverProtocol://$WebserverHostname:$WebserverPort | cut -f2 -d " " | head -n 1)		# Extracting just the status code
		if [[ $code -ne 200 ]]; then
			sleep 0.5
		fi
		i=$[$i+1]
	done

	if [ $i -eq $WebserverQueriesLimit ] && [[ $resCode -ne 200 ]]; then
		echo "Error."
		echo "The server did not answer. Check the docker container logs for more information..."	
		$(exit 1)
	else
		printf " Success!\n"
	fi
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

echo 
echo "Server can now be reached at $WebserverProtocol://$WebserverHostname:$WebserverPort"

# Opening the server in firefox
echo;printf "$cnt/$lim Opening firefox..."; cnt=$[$cnt+1]
	firefox "$WebserverProtocol://$WebserverHostname:$WebserverPort" &	# & so it is non blocking
if [ $? -ge 1 ]; then echo "An error occured. Aborting."; exit; fi

echo
echo "Script finished successfully!"