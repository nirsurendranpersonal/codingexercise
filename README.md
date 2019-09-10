# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/maven-plugin/)

### Prerequisites
* Postgres installed (brew install postgresql)
* Docker installed


### Run
* Start by entering this command on the command line:
  ####psql postgres
* Execute SQL commands listed in database-creation-population.sql from psql command line
* Package application by typing the following command from the root directory of the project
  ####mvn clean package
* Build the docker image
  ####docker build -t codingexercise .
* Run the docker image
  #### docker run -p 5000:8080 codingexercise

