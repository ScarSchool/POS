#!/bin/bash
echo "Opening WebBrowser"
firefox http://localhost:8080/

echo "gradling"
cd ./18_Tomassetti_AsyncTasks
./gradlew run

echo "Starting emulator"
~/Android/Sdk/tools/emulator -avd Pixel_2_API_28
