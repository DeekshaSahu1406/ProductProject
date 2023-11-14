cd DbDocker
docker build --tag=mypostgre:latest .

cd ..
docker build --tag=productservice:latest .