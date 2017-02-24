# spring-boot-angular2-mysql-multicontainer-docker
The multi-module application using Spring boot, Angular, Mysql and Docker.

The application structure is as follows.
- **game-reco-service** - Microservice implemented using Spring boot. [More info](game-reco-service/README.md)
- **game-reco-ui** - A NodeJs application implemented using Angular. This consumes services hosted by game-reco-service.  [More info](game-reco-ui/README.md)
- **docker-compose.yml** - Docker compose file to run game-reco-service in container.

### Build

#### 1) Build game-reco-service

```
$ cd game-reco-service
$ gradlew clean build
```

#### 2) Build Docker images and run it in containers using docker-compose from parent directory.
   This also create container for Mysql and run it.
   
```
$ docker-compose up
```

#### 3) Build and run game-reco-ui application

```
$ cd game-reco-ui
$ npm install
$ npm start
```

### Access application using following URL

```
http://localhost:8000
```

### The game-reco-ui application has two tabs
- Administration - To upload csv file containing recommendations for the cstomers.
- Game Center - To view recommendations for the customer.


