1>REMOVE IMAGES AND RUNNING CONTAINERS
docker stop $(docker ps -q)
docker rm $(docker ps -aq)
docker rmi $(docker images -q) -f

2> docker network inspect app-network


docker images
docker ps 
docker ps -a
docker stop <container_id>
docker rm <container_id>
docker rmi -f spring-boot-docker.jar
-------------------------------------------------------------------------------------------------REDIS----------
LOCAL : 
sudo apt update
sudo apt install redis-server
redis-cli

docker pull redis
docker run --name redis-data -d -p 6379:6379 redis

redis-cli -h 172.17.0.2 -p 6379
172.17.0.2:6379>

docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' redis-data
172.17.0.2   
---------------------------------------------------------------------------------------------------app jar--------
docker build -t spring-boot-docker.jar .
docker run -p 8081:8081 spring-boot-docker.jar

docker run -p 9090:8080 -e SPRING_REDIS_HOST=redis -e SPRING_REDIS_PORT=6379 spring-boot-docker.jar

--------------------------------------------------------------------------------------------------POSTGRES---------
docker pull postgres
docker run --name postgres-container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root@123 -e POSTGRES_DB=mydb -p 5432:5432 -d postgres

VOLUME
docker run --name postgres-container -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root@123 -e POSTGRES_DB=mydb -p 5432:5432 -v postgres-data:/var/lib/postgresql/data -d postgres


docker exec -it postgres-container psql -U postgres -d mydb

docker inspect postgres-con

docker run -d -p 5432:5432 \
  -e POSTGRES_PASSWORD=root@123 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_DB=mydb \
  -v "//c/Workspace/postgres_container_data:/var/lib/postgresql/data" \
  --name=postgres_con postgres
  ---------------NGNIX--------------------------------------------------
docker pull nginx
docker run --name nginx-server -p 8080:80 -d nginx
http://localhost:8080
mkdir -p ~/nginx/html ~/nginx/conf.d
echo "<h1>Hello, Nginx from Docker!</h1>" > ~/nginx/html/index.html
docker run --name nginx-server -p 8080:80 -v ~/nginx/html:/usr/share/nginx/html:ro -v ~/nginx/conf.d:/etc/nginx/conf.d:ro -d nginx
-v ~/nginx/html:/usr/share/nginx/html:ro: Maps your local html folder to the Nginx default content directory in the container.
-v ~/nginx/conf.d:/etc/nginx/conf.d:ro: Maps your local configuration files to the Nginx configuration directory in the 
container.

docker stop nginx-server
docker start nginx-server
docker rm -f nginx-server



docker run -p 8080:80 ngnix


sakshi@IN-PG04G54Q:~$ docker run --name nginx-server -p 8081:80 -d nginx
be38624b24d8d9858c5350ecb2135173fec29cfb3465f1cfb10ada032af190e8
sakshi@IN-PG04G54Q:~$ docker run --name nginx-server-2 -p 8081:80 -d nginx
bed08ada4190fb0b9a132ed3b1e6fb6a581551f975a3ca9366062da4c28617fd
docker: Error response from daemon: driver failed programming external connectivity on endpoint nginx-server-2 (92db643d4b0fb9655fe25fc69670053a06d4ceb1a41fb5e657572f257251863e): Bind for 0.0.0.0:8081 failed: port is already allocated.
sakshi@IN-PG04G54Q:~$


http://localhost:8080/api/hello

docker-compose build
docker-compose up

http://localhost:8080/h2-console


#----------- Tag and push each service----------------------------------------------------------------------------------------
docker tag web-service your-dockerhub-username/web-service:latest
docker push your-dockerhub-username/web-service:latest

docker tag cache-service your-dockerhub-username/cache-service:latest
docker push your-dockerhub-username/cache-service:latest
----------------------------------------------------------------------------------------------------------------
