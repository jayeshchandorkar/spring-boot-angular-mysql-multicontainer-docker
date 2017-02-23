# spring-boot-angular2-mysql-multicontainer-docker
The multi-module application using Spring boot, Angular2, Mysql and Docker.


Build

1) Build game-reco-service

$ cd game-reco-service
$ gradlew clean build


Build Docker images and run it in containers using docker-compose from parent directory
$ docker-compose up
