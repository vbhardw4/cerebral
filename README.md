# Cerebral
This repository is a backend for the [front-end](https://github.com/vbhardw4/student-result-management-system). This backend is written using below tools and technologies -  
1. #### Spring-Boot Framework
2. #### Hibernate 
3. #### Docker (*Containerizing Appliation*)
4. #### Kubernetes (*Cluster Orchestration*)
5. #### Postgres SQL (*Persistent Database*)
6. #### Redis (*In memory Database*)

### Steps to start this application in the minikube cluster environment
1. Although the application contains several components which needs to be initialized and deploy before the application can handle requests such as
* Redis, PostgresSQL
2. However, I have created the below script which deploy all the several components and services altogether needed for this application.
* [kubectl_backend_deployment_script.sh](https://github.com/vbhardw4/cerebral/blob/main/kubectl_backend_deployment_script.sh)
3. Once the application has been deployed and services are up and running, please use below command to get the URL where this application is deployed.
* `minikube service --url cerebral`

_*Note_* *that there is a config-yaml and secrets yaml which contains the Postgres DB and other essential configurations and secrets which are used across the cluster pods*

### Requests handled by the application controllers - 
1. CRUD operations related to Student handled by StudentController.java
2. CRUD operations related to Courses handled by CoursesController.java
3. CRUD operations related to Results handled by ResultsController.java

### Automation

1. **Unit Tests** and **Integration Tests** have been written to test each and every component of the application.
2. **Github Actions** is used for the CICD part of this application. This [workflow](https://github.com/vbhardw4/cerebral/blob/main/.github/workflows/build-maven.yml) runs the integration tests for this application and build the application with maven
