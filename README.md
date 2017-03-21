# Problem 1: Create a Spring Service to consume a SOAP endpoint.

Develop a Spring Service using a TDD approach to consume the value from from the web
service: DailyDilbert published in the following endpoint:
http://www.gcomputer.net/webservices/dilbert.asmx
1. WSDL: http://www.gcomputer.net/webservices/dilbert.asmx?WSDL

Build System: Maven | Gradle


#### Application componentes: src/main/java
* Application.java (main class to run the application)
* ComputerClient.java ()
* ComputerService.java (class to perform service tasks)
* ComputerConfiguration.java ()

#### Application componentes: src/main/resources
* application.yml (properties) 

#### Application componentes: src/test/java
* ComputerServiceTest.java (class to test the application)    
    
  
## Building a Project with Maven
The vast majority of Maven-built projects can be built with the following command:
```[javascript]
mvn clean install
```
This command tells Maven to build all the modules, and to install it in the local repository. The local repository is created in your home directory (or alternative location that you created it), and is the location that all downloaded binaries and the projects you built are stored.

## How to run unit test with Maven
To run unit test via Maven, issue this command :
```[javascript]
mvn test
```
This will run the entire unit tests in your project.