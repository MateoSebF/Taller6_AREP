# Spring Server

This repository contains an example of how to build a web server using Spring. It covers the complete process of creating the model, repository, service, and controller, as well as an additional class for filtering data during fetching. The project uses a MySQL database to persist data and is deployed to work in the cloud with AWS.

## Getting Started

This project is built in Java using Maven. Additional Spring Boot dependencies, such as Spring Web and JPA, are included in the pom.xml file.

Important: This server requires a properly configured database to work. You must update the connection settings in the /src/main/resources/application.properties file.

### Prerequisites

Before running this project, ensure you have the following installed on your system:

* Java Development Kit (JDK) 21 or more
    * Download and install from: [Oracle JDK or OpenJDK](https://www.oracle.com/co/java/technologies/downloads/)
    * Verify installation with:
        ```
        java -version
        ```
* Apache Maven (for dependency management and build automation)
    * Install from: [Maven Downloads](https://maven.apache.org/download.cgi#Installation)
    * Verify installation with:
        ```
        mvn -version
        ```
* Git (to clone the repository)
    * Install from: [Git Downloads](https://git-scm.com/downloads)
    * Verify installation with:
        ```
        git --version
        ```

### Installing

Follow these steps to set up and run the project in your local development environment:

Clone the Repository:

```
git clone https://github.com/MateoSebF/Taller5_AREP

```

Navigate to the Project Directory:

```
cd Taller5_AREP
```

Build the Project with Maven:

```
mvn clean compile
```

Run the Web Server:

```
mvn clean compile exec:java
```
or
```
java -cp "target/classes" co.edu.eci.arep.springweb.SpringWebApplication
```

### Architecture
To understand the architecture followed in this article, we are going to use a diagram like this:

![Class Diagram](images/Class_Diagram.png)

In this architecture, we consider the interaction between the components of the app to achieve the desired result. In this case, as is usually done, we have four principal components:

Property: This is the model class where we define all the logic of a property, including attributes like id, direction, price, size, and name. This class is annotated with @Entity, indicating that it represents a table in our database.

PropertyRepository: This class allows us to interact with the database, enabling operations such as saving, deleting, and retrieving data. It is annotated with @Repository, which makes it possible to inject it into the PropertyService.

PropertyService: This class defines the logic for querying the repository. A special feature of our app is the PropertySpecification class, which allows searching for properties based on various criteria such as a minimum price range, a size range, or a specific direction. Using this class, we can query the repository with a specific specification, making the program more dynamic.

PropertyController: This class injects the PropertyService, which is annotated with @Service, making it a bean that can be injected. The controller exposes an endpoint called /api/properties, where all required queries can be made.

This image illustrates the functionality of the application, including the implementation of additional features such as pagination and the ability to filter properties using specific criteria. 

![Example of working](/images/Example_of_working.png)

The section "Deployment Demo" provides a complete demonstration of the project's use case.

## Deployment  


To deploy this project, we provide a Docker-based solution.

First, we create a MySQL image and deploy it on Azure, ensuring that port 3306 is correctly configured. After that, we update the database information in the application.properties file.

Next, we create an image using a Dockerfile to configure the environment. This file specifies important aspects such as the port configuration. This is crucial because, when deploying the application, you need to specify both the internal port used by the app and the external port to expose.

To deploy this service, you should create the image using the Dockerfile. This process automatically retrieves dependencies, compiles the classes, and includes the static files necessary for the correct functioning of the server. You must then link this image to a repository in Docker Hub. Once this is done, you can run the container on AWS. By configuring the security group to accept the necessary requests and linking a correctly configured database, the application will function properly.

### AWS Deployment  

To deploy the application on **AWS**, follow these steps:  

1. **Create an EC2 instance** according to your requirements.  
2. **Configure security rules** for the instance:  
   - Allow inbound connections to the port you want to expose (e.g., `8080`).  
3. **Connect to the EC2 instance** via SSH.  
4. **Install and configure Docker** on the instance.  
5. **Run the container** using the following command:  

   ```sh
   docker run -d -p 8080:8080 --name name_container your_user/your_repo 
   ```
    ### Container Configuration  

- `8080` is the **internal port** used by the server (defined in the `Dockerfile` as an environment variable).  
- `8080` is the **exposed port** that will be accessible from the internet.  

Once the container is running, you can access the application via the **public DNS** provided by AWS.  

### Endpoint Structure  

- **REST API endpoints:** `/app/repositories`   

### Link to Deployment Demo Demostration 

[Watch the deployment in action](https://pruebacorreoescuelaingeduco.sharepoint.com/sites/Reco842/Shared%20Documents/General/Recordings/Reuni%C3%B3n%20en%20_General_-20250305_170626-Grabaci%C3%B3n%20de%20la%20reuni%C3%B3n.mp4?web=1&referrer=Teams.TEAMS-WEB&referrerScenario=MeetingChicletGetLink.view)  


## Built With

* [Java](https://www.oracle.com/co/java/technologies/downloads/) - The programming language used
* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit](https://junit.org/junit5/) - Testing Framework for unit tests

## Versioning

We use [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) for versioning.  

## Authors

**Mateo Forero** - *Initial work* - [MateoSebF](https://github.com/MateoSebF)

## Acknowledgments

* Inspiration from various resources and tutorials