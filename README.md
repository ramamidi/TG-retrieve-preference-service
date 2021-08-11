# Retrieve Marketing Preferences Microservice

This is the retrieve preference microservice that is responsible to retrieve Marketing preferences of customers. This
microservice is written in Java/Spring Boot.

# Steps to run without docker

## Pre-requisites:

* Install Java 16 [Install open jdk 16 on your machine](https://openjdk.java.net/)
* Install Gradle [Install open jdk 16 on your machine](https://gradle.org/install/)

### Steps to run locally:

* Run ```gradle build```
* ```cd build/libs``` folder
* Run java -jar retrieve-preference-service-1.0.0.jar

# Steps to run using docker:

* Install Docker [Install docker on your machine](https://www.docker.com/products/docker-desktop)

## Docker Build

```
docker build -t retreivepreference:lts .
```

## Docker Run

```
docker run -p 8081:8081 --init -e SPRING_PROFILES_ACTIVE='demo' -d docker  --rm --name retrievepreference_service retreivepreference:lts
```

## Run non detach

```
docker run -p 8081:8081 --init -e SPRING_PROFILES_ACTIVE='demo' --rm --name retrievepreference_service retreivepreference:lts
```

## Check container logs

```
docker logs <image_name>
docker logs retrievepreference_service
```

## Kill container

```
docker kill <container-id>
docker rm retrievepreference_service
```



