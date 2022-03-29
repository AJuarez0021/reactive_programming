#!/bin/bash
# ANSI Colors
echoRed() { echo $'\e[0;31m'"$1"$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'"$1"$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'"$1"$'\e[0m'; }


build(){
  mvn clean package
  docker build -f Dockerfile -t academy-functional:latest .
  docker-compose up
}

echoGreen "Start deploy academy"
build
