Ich habe den Webservice nun angepasst, das Script funktioniert bei mir.
Das Skript beinhält folgende Funktionalitäten:
	- Kompiliert den Webservice
	- Baut ein docker image mit den Daten des Webservices
	- Startet den Webservice Container und den Datenbank Container (durch das docker-compose.yml file mit der docker-compose cli)

Auszuführen mit:

	build.sh
	
	
Wenn der Webservice läuft können Sie die index.html abrufen indem Sie die root URL, also

	http://localhost:8080/

aufrufen. Dort finden Sie eine statische HTML-Seite die über den Webservice geserved wird und eine Liste mit Links anzeigt mit denen Sie schnell die einzelnen URL-Endpoints abrufen können.
