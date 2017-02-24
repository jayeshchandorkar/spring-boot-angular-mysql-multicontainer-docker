##Customer Microservice - Spring boot application

Prerequisite:

- JDK 1.8 
- Mysql 5.7

Create schema and update following values to connect to the database.
[Check for more info](src/main/resources/application.yml)

```
 datasource:
        url: jdbc:mysql://localhost:3306/game_reco
        username: root
        password: root
```


To build

```
$ gradlew build
```

To Run

```
$ java -jar build/libs/game-reco-service.jar
```

REST Endpoints

GET http://localhost:8080/customers/11111/games/recommendations?count=5
POST http://localhost:8080/customers/11111/games/recommendations    multipart
