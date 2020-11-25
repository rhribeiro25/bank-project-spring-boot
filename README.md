```
8 888888888o          .8.          b.             8 8 8888     ,88'     8 888888888o   8 888888888o.      ,o888888o.                8 8888 8 8888888888       ,o888888o. 8888888 8888888888
8 8888    `88.       .888.         888o.          8 8 8888    ,88'      8 8888    `88. 8 8888    `88.  . 8888     `88.              8 8888 8 8888            8888     `88.     8 8888       
8 8888     `88      :88888.        Y88888o.       8 8 8888   ,88'       8 8888     `88 8 8888     `88 ,8 8888       `8b             8 8888 8 8888         ,8 8888       `8.    8 8888       
8 8888     ,88     . `88888.       .`Y888888o.    8 8 8888  ,88'        8 8888     ,88 8 8888     ,88 88 8888        `8b            8 8888 8 8888         88 8888              8 8888       
8 8888.   ,88'    .8. `88888.      8o. `Y888888o. 8 8 8888 ,88'         8 8888.   ,88' 8 8888.   ,88' 88 8888         88            8 8888 8 888888888888 88 8888              8 8888       
8 8888888888     .8`8. `88888.     8`Y8o. `Y88888o8 8 8888 88'          8 888888888P'  8 888888888P'  88 8888         88            8 8888 8 8888         88 8888              8 8888       
8 8888    `88.  .8' `8. `88888.    8   `Y8o. `Y8888 8 888888<           8 8888         8 8888`8b      88 8888        ,8P 88.        8 8888 8 8888         88 8888              8 8888       
8 8888      88 .8'   `8. `88888.   8      `Y8o. `Y8 8 8888 `Y8.         8 8888         8 8888 `8b.    `8 8888       ,8P  `88.       8 888' 8 8888         `8 8888       .8'    8 8888       
8 8888    ,88'.888888888. `88888.  8         `Y8o.` 8 8888   `Y8.       8 8888         8 8888   `8b.   ` 8888     ,88'     `88o.    8 88'  8 8888            8888     ,88'     8 8888       
8 888888888P .8'       `8. `88888. 8            `Yo 8 8888     `Y8.     8 8888         8 8888     `88.    `8888888P'         `Y888888 '    8 888888888888     `8888888P'       8 8888
```
## Project overview

This project's a Restful API that manages bank accounts

## Requirements

- To open an account you only need the person's full name and social security number, but only one account per person is allowed;
- With this account it is possible to make transfers to other accounts, deposit and withdraw money;
- When depositing money in the account, the customer receives from Bank another half percent of the deposited amount as a bonus;
- When withdrawing money, choose the value of one percent over the amount withdrawn, and we do not accept negative values in the accounts;
- Transfers between accounts are free and unlimited;
- It is important to have a history of all customer movements.

## Tech Stack

- Spring Boot 2.4 / MVC / Data / Security
- Docker / Docker Compose
- PostgreSQL 11 / PgAdmin
- JUnit 5 / Mockito
- Java 8
- Lombok
- Swagger 2

## Programming principles

- Behavior Driven Development
- Clean Code by Lombok
- Hibernate Validator
- Object Orientation
- Exception handling

## Software architecture

- Representational State Transfer
- Model View Controller
- Data Transfer Objects
- Data mapper

## Starting the project by Docker

- Should have Docker and Docker-compose installed
- Create the network manually using: `$ docker network create bank-project-net`
- You can boot the whole stack by using `$ docker-compose up -d` and to access logs after with `$ docker-compose logs -f api`
- Open your browser to access documentation: `http://localhost:9090/swagger-ui.html`
- You can to use Postman Collection in `doc/bank-project.postman-collection.json`

## Starting the project by IntelliJ

- Should have PostgreSQL installed and configured
- Should have IntelliJ Community, JDK 1.8 and Maven 3.6.3 installed
- Should have Lombook and Docker plugins installed in IntelliJ
- You need to update gradle dependencies
- To execute clean and build the project
- Run BankProjectSpringBootApplication.java file

## Access PGADMIN by Docker

- To access PGADMIN locally open your browser and type: `http://localhost:16543`
- login with default credentials:
  `email: rhribeiro_25@hotmail.com`
  `password: postgres`

## Database configuration in PGADMIN

- then config the connection to your database:
  `Host name/ address : pgsql`
  `port: 5432`
  `Maintenance database: bank-project-db`
  `Username: postgres`
  `Password: postgres`
  
