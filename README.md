# todoapp
Application for managing tasks.

## Getting Started
Application was created using Spring Boot and deployed using Tomcat.

MySQL is used database. Necessary script to build database with all required tables and records is in main folder, named 'database_script.sql'

You can use one of two predefined users to sign in to application:
```
username: anna
password: 123456

username: robert
password: 123456
```
MySQL user account is defined as:
```
username: root
password: password
```
It is also defined in application.properties file in src/main/resources so it may be easily changed.

Additionally I have added some tasks, so that you can see how GUI shows data without adding tasks in application.

Maven was used as a build automation tool.

I hope I cover every point to make this application working.
