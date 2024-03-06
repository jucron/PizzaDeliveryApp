#Work in Progress

##TODO
1. Migrate Java Backend to TypeScript aws lambda functions
2. Create an UX Planning and finish Angular front
3. Implement Auth features
4. Implement Notification features

# Pizza Delivery App 

### Website of the App: 
>[TODO: AWS server (live testing)](https://joao-sports-buddy.herokuapp.com/)

### Summary:
The application simulates a Pizza Delivery webpage in which you can create an Account and Order Pizzas to be delivered in your house. 
The frontend was implemented with Angular and the backend is constructed with two MicroServices: Resouce-Server and Authorization-Server.

## Features
### Login
* Account creation with unique username and e-mail
* Password safely encoded and stored in database
* Credentials check and login with session 
<img src="https://user-images.githubusercontent.com/79875515/157717950-8c5fc224-2504-4ac9-96b3-63336839a600.gif" width=75% height=75%>

### Password recovery
* Send a message to the registered e-mail containing a new password
### Account update
* Option to update Name and Password when logged in
### TODO: Create Order
* Match creation with field validation
* Match participation in which you are not participating
* Match leaving in which you are participating
* Match delete if you are the owner
<img src="https://user-images.githubusercontent.com/79875515/157723193-fffd03c9-a913-41b2-8cf4-cfd604f80fc3.gif" width=75% height=75%>

### Database Diagram
For the database modeling, a diagram with each table and relationships was created for the SportsBuddy application:
<img src="https://user-images.githubusercontent.com/79875515/158018955-9ad71cf2-4872-415f-aea6-062185d617cd.png" width=75% height=75%>

## Getting Started
### Data Initialization
* Use the file [configure-postgres.sql](https://github.com/jucron/SportsBuddy/blob/master/src/main/resources/scripts/configure-postgres.sql) in order to give your PostGresSQL database the necessary configuration. This way we can properly use the different profiles: `dev` and `prod`.
* Use the file [start_data-postgres.sql](https://github.com/jucron/SportsBuddy/blob/master/src/main/resources/scripts/start_data-postgres.sql) to initialize tables and constraints in your PostGresSQL database. So just run the application for the first time, it will automatically input data if not existent.

### Via Docker
1. After changes run `docker build -t sportsbuddy .` in a project root directory to build application image.
2. After image is fully built, run `docker run -d -p 8080:8080 sportsbuddy` to start the image
3. With a browser, access the app via http://localhost:8080/ 
4. (Optional) run `docker logs -t <container>` to see the logs 
#### Notes:
Database for testing is in-memory type, for fast and convenient usage. It will be updated in the future.
You won't be able to send the message via e-mail, unless account details are changed in application.properties file
## Conceptual Design
> Object Oriented Programming

> Dependency Injection (Design pattern)

> MVC Structure (Model, View, Controller)
## Technology 
  - Java
  - Spring-Boot
  - Java Persistence API (JPA)
  - Hibernate (object relational mapping)
  - Thymeleaf (Frontend java engine)
  - Maven (build, dependencies)
  - Testing (JUnit, Mockito)
  - Spring Security (Form based login)
  - JavaScript (DOM manipulation)
  - HTML
  - Bootstrap
  - Code coverage
  - CircleCI
