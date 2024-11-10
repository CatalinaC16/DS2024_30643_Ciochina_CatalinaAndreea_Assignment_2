# Energy Monitoring Application

This repository contains an energy monitoring application consisting of two microservices: `usersms` and `devicesms`
and a frontend application in Angular (`frontend_energyapp`). Each microservice has its own PostgreSQL database.

## Prerequisites

- Docker and Docker Compose installed.
- Node.js and npm (only required for local development of the Angular frontend).
- Java 17 (only required for local development of the microservices).

## Project Structure

- **UserMicroService**: Contains code for `usersms` microservice.
- **DeviceMicroService**: Contains code for `devicesms` microservice.
- **Frontend_EnergyApp**: Angular-based frontend application.
- **docker-compose.yml**: Docker Compose configuration for deploying the entire application stack.

For generating the jar of each microservice run before step 1. 
#mvn clean install
For generating the dist folder on frontend run this command before step 1.
#ng build --prod


## Build and Run with Docker

### Step 1: Build and Start Containers

To build and run all services using Docker Compose, run the following command in a cmd
from the project root directory:

#docker-compose up --build

This command will:

Build Docker images for usersms, devicesms, and the Angular frontend.
Set up PostgreSQL databases for each microservice.
Start all containers.

### Step 2: Access the Application
Frontend: The frontend will be accessible at http://localhost:4200.
Users Microservice: Accessible at http://172.16.0.3:8080.
Devices Microservice: Accessible at http://172.16.0.5:8081.

### Step 3: Stopping the Application
To stop and remove containers, use in a cmd from root directory:

#docker-compose down

This command stops all running containers and removes them along with any networks created by Docker Compose.