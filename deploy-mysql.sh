#!/bin/bash
docker pull mysql:5.6
docker build -f Dockerfile -t mysql:5.6 .
docker-compose up


