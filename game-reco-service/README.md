##Customer Microservice - Spring boot application

### Prerequisite:

- JDK 1.8 
- Mysql 5.7


### DB
Create schema and update following values to connect to the database.
[Check for more info](src/main/resources/application.yml)

```
 datasource:
        url: jdbc:mysql://localhost:3306/game_reco
        username: root
        password: root
```


### Build

```
$ gradlew build
```

### Run

```
$ java -jar build/libs/game-reco-service.jar
```

### REST Endpoints

GET http://localhost:8080/customers/11111/games/recommendations?count=5

POST http://localhost:8080/customers/11111/games/recommendations

GET http://localhost:8080/customers



### Unit tests

 ```
  $ gradlew test
 ```


### Integration tests

 ```
  $ gradlew integrationTest
 ```

### NOTE:
- docker-compose.yml file from parent directory can be used to run the service in Docker.
  Run following command from parent directory. The docker-compose will take care of mysql installation as well.
  
  ```
  $ docker-compose up
  ```
