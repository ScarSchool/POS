#!/bin/bash

echo 'Starting the services...'
docker-compose up

echo 'Waiting for the services to start (30s)'
sleep 30

echo 'Opening the html file...'
firefox http://localhost:8080/
