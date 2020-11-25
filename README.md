```
______             _   ______          _           _   
| ___ \           | |  | ___ \        (_)         | |  
| |_/ / __ _ _ __ | | _| |_/ / __ ___  _  ___  ___| |_ 
| ___ \/ _` | '_ \| |/ /  __/ '__/ _ \| |/ _ \/ __| __|
| |_/ / (_| | | | |   <| |  | | | (_) | |  __/ (__| |_ 
\____/ \__,_|_| |_|_|\_\_|  |_|  \___/| |\___|\___|\__|
                                     _/ |              
                                    |__/         
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
- Authentications in the API:
```
Consultation
login: user
password: bankProject@2020

Modification
login: admin
password: bankProject@2020
```

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
  ```
  email: rhribeiro_25@hotmail.com
  password: postgres
  ```

## Database configuration in PGADMIN

- then config the connection to your database:
  ```
  Host name/ address : pgsql
  port: 5432
  Maintenance database: bank-project-db
  Username: postgres
  Password: postgres
  ```
  
