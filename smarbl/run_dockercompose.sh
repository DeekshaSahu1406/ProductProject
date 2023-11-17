#!/bin/bash

# Build Docker images
cd DbDocker
docker build --tag=mypostgre:latest .

# shellcheck disable=SC2103
cd ..

docker build --tag=productservice:latest .

# Stop and remove existing containers (if any)
docker-compose down

# Run the containers using Docker Compose
docker-compose up -d

# Run the Spring Boot app
mvn spring-boot:run