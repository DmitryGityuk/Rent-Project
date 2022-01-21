# Rent-Project
Rent A House is a sample Spring Boot MVC (Thymeleaf + REST) project.
## Description
Rent A House is an application which provides the user with a web application and a REST API to perform CRUD operations on houses, rentals and many more.
## Technologies
The goal of this application is to get an in-depth feeling for the usage of the Spring framework. The following subjects were applied inside this project:

* Spring Boot
* Spring Security
* Spring MVC
* Thymeleaf
* Bootstrap 
* MySQL in memory database
* JPA ORM (Hibernate impl.)

## (Maven) project structure
There are many packages inside the project, in order to keep the code clean:

__src/main/java/...__
* config: Contains all configuration classes. In particular messages and security.
* controller: Classes annotated with @Controller, which are used to return dynamically rendered HTML views.
* models: Contains all JPA entity classes.
* repository: Contains all repositories to access the database.
* service: Contains all service classes which perform further business logic.
* util: Contains classes that help reducing code repeating.

__src/main/resources/...__

Contains templates, insert scripts and configuration files.

* application.properties: Contains the server configuration.
* application.yml: Contains the aws s3 configuration.
* templates: This package consists of the all files .html (using the Thymeleaf Dialect). 

## Authentication

### - Roles
There are three access types (user roles) in this application:

#### User
By logging in as a user that has the role of "USER" the user can issue new rentals. 

#### Owner
Users with the role of "OWNER" have access to house listings but cannot rent.

#### Public
Anyone can call the root website. The user can see a form in which he can select a city. After submitting the form, the server will return a new page where all free houses in the selected city are displayed in a pretty way.
Public users do not have a role defined in the code.

#### Redirection
If unauthenticated users try to access paths of a specific role, which they are not part of, they will be redirected to the login screen.

### Main page
![Image alt](https://github.com/DmitryGityuk/Rent-Project/raw/master/src/main/resources/static/images/desc.jpg)
