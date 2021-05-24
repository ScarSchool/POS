#!/bin/bash

# args: <avd-name>
# can be found with `emulator -list-avds`

echo 'Starting the emulator...'
emulator -avd $1 & adb wait-for-local-device

echo 'Building and installing the apk...'
./gradlew installDebug

echo 'Apk built and installed!'

# For more info on the commands, see
# https://developer.android.com/studio/run/emulator-commandline#starting
# Adb usage info by running `adb`
# https://developer.android.com/studio/build/building-cmdline#DebugMode
