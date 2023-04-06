# Telegram bot


Java Spring + Postgres project
[Link at task](Task.md)

## Requirements

---
For building adn running the application you need:
* [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
* [MAVEN 3](https://maven.apache.org/)

## Running the application locally

---

The are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the project class from your IDE.

Alternatively you can use Maven plugin like:
```
mvn spring-boot:run
```

If u wanna start your application with arguments
```
mvn spring-boot:run -D spring-boot.run.arguments="arg1 arg2"
```
Where arg1 is token from TelegramAPI and arg2 is delay
## About the Service

---

The service is just a simple parking lot review REST service. It uses a database (PostgreSQL) to store the data.

Database have 1 tables which  interaction of program<br>
Main table is `User` contains information about users messages and last message time.

## Documentation

---

Swagger was used for recorded and tested application<br>
[link localhost for checking last received message](http://localhost:8080/receiveLastMessage)'

