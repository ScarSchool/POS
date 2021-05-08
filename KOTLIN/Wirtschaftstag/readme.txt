Ein Projekt welches in Zusammenarbeit mit Herrn Tschlatscher entstanden ist.
Wir haben leider nicht alle vorgegebenen UseCases erfolgreich ausprogrammieren können, da uns die Zeit ausgegangen ist.
Im Folder "Screenshots" befinden sich die entsprechenden screenshots.

Das Skript beinhält folgende Funktionalitäten:
    - Kompiliert den Webservice
    - Baut ein docker image mit den Daten des Webservices
    - Startet den Webservice Container und den Datenbank Container (durch das docker-compose.yml file mit der docker-compose cli)

Auszuführen mit:

    build.sh


Wenn der Webservice läuft können Sie die index.html abrufen indem Sie die root URL aufrufen:

    http://localhost:8080/

Mithilfe des "run.sh" scripts wird die Mobile applikation gestartet und (hoffentlich) compiliert.

Ich habe großteils mit dem Emulator des Google Pixel XL mit dem API level 29 getestet, 
jedoch wurde nie mit einem physischen Telefon gearbeitet