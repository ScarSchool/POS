#!/bin/bash
echo "Opening WebBrowser"
firefox http://localhost:8080/

echo "gradling"
cd ./WTMobile
./gradlew run

echo "Starting emulator"
~/Android/Sdk/tools/emulator -avd Pixel_XL_API_29
