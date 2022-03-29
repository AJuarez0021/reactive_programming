#!/bin/bash
#source "$HOME/.sdkman/bin/sdkman-init.sh"
#sdk use java 11.0.12-open

cd spring-webflux-client-academy
mvn clean package
cd target 
java -jar *.jar
