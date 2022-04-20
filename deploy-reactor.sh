#!/bin/bash

#source "$HOME/.sdkman/bin/sdkman-init.sh"
#sdk use java 11.0.12-open

cd spring-reactor-demo
mvn clean package
cd target 
java -jar *.jar
