# TASK SCHEDULER

## What does it do?
This application allows the user to schedule tasks for sometime in the future. All tasks
are persisted on a mysql database.

### Creating new tasks
New tasks can be created by making a post request to localhost:8080/schedule with a request
body like:
`{
    "taskName": "some string",
    "scheduledTime": "2024-10-08T16:11:30",
    "status": "PENDING"
}`


### View the status of tasks
The current status of the tasks can be viewed at localhost:8080/all


## How to Run?

1. Create the application.properties file in src/main/resources with following content:

    `spring.application.name=scheduler
    spring.flyway.enabled=true
    spring.flyway.baseline-on-migrate=true
    spring.datasource.url=jdbc:mysql://<link to the database>
    spring.datasource.username=<mysql username>
    spring.datasource.password=<mysql password>
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.main.allow-circular-references=true`

2. Run the spring-boot application using your preferred method.