# Reactive Programming...

This training requires some components to be run before executing any service.

## MySQL 
Docker required to build the components

```shell
docker pull mysql:5.6
docker build -f Dockerfile -t mysql:5.6 .
docker-compose up

```

## Academy-Annotations
Apache Maven and Java Development Kit (JDK) 11 are required to build the components

```shell
cd spring-webflux-academy
mvn clean package
cd target
java -jar *.jar
```

## Academy-Functional

Apache Maven and Java Development Kit (JDK) 11 are required to build the components

```shell
cd spring-webflux-academy-functional
mvn clean package
cd target
java -jar *.jar
```
## Academy-Client

Apache Maven and Java Development Kit (JDK) 11 are required to build the components

```shell
cd spring-webflux-client-academy
mvn clean package
cd target
java -jar *.jar
```

## Reactor-Demo

Apache Maven and Java Development Kit (JDK) 11 are required to build the components

```shell
cd spring-reactor-demo
mvn clean package
cd target
java -jar *.jar
```

## Destroy all components
```shell

docker ps -a
docker stop CONTAINER_ID
docker rm CONTAINER_ID

Example:
docker stop 2b579c3d584e
docker rm 2b579c3d584e

cd spring-webflux-academy-functional
mvn clean
cd ..

cd spring-webflux-client-academy
mvn clean
cd ..


cd spring-webflux-academy
mvn clean
cd ..


cd spring-reactor-demo
mvn clean
```
