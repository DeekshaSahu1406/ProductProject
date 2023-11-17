#!/bin/bash

docker stop my-postgres-container
docker rm my-postgres-container
docker run --name=my-postgres-container -d -p 5000:5432 mypostgre:latest