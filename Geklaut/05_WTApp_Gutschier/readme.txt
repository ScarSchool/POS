How to use:

generate Jar file (ideally via console command 'mvn package', because IntelliJ sometimes doesn't work)
generate the docker image
start the docker-image via the 'given docker-compose.yaml'
wait until the server started (usually takes about 6 seconds)
make a request on http://localhost:8080/
enjoy!