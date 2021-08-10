# Retrieve Marketing Preferences Microservice

This is the retrieve preference microservice that is responsible to retrieve 
Marketing preferences of customers. This microservice is  written in Java/Spring Boot.

## Steps to run without docker

### Pre-requisites:

* Install Java
* Install Gradle
* Install Mysql

### Steps to run locally:

* Run ```gradle build```
* cd into build/libs folder
* Run java -jar RetrievePreference-1.0.0.jar

### Steps to run using docker:

* Install Docker

# Docker Build

```
docker build -t retreivepreference:lts .
```

# Docker Run

```
docker run -p 8081:8081 --init -e SPRING_PROFILES_ACTIVE='demo' --rm -d --name retrievepreference_service retreivepreference:lts
```

## Run non detach

```
docker run -p 8081:8081 --init -e SPRING_PROFILES_ACTIVE='demo' --rm --name retrievepreference_service retreivepreference:lts
```

## Check container logs

```
docker logs <image_name>
docker logs retrievepreference_app
```

## Kill container

```
docker kill <container-id>
docker rm retrievepreference_app
```



