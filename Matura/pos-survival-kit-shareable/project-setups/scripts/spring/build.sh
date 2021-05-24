#!/bin/bash

echo 'Building the server...'
mvn -DskipTests package
echo 'Server built successfully!'
