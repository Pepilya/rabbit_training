#! /bin/bash
cd producer
echo "Building producer ..."
./gradlew clean build
cd ..
cd listener
echo "Building listener ..."
./gradlew clean build
cd ..