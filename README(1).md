## Health Record Management System
## Overview

This Spring Boot application provides a simple health record management system, allowing users to create, view, update, and delete their health records.
Key Features

    HealthRecord Entity: Stores recordId, userId, appointmentDate, vitalSigns, and medications.
    RESTful API: Provides endpoints for CRUD operations on health records.
    Database Integration: Uses an in-memory H2 database for storage.
    User Authentication: Implements basic authentication for user association.
    Swagger UI: Provides API documentation and interaction.
    Unit Tests: Ensures functionality of CRUD operations.

## Technologies Used

    Java
    Spring Boot
    Spring Data JPA
    Spring Security
    H2 Database
    Swagger UI
    JUnit

## Getting Started

Prerequisites:

        Java 17 or later
        Maven
    
    
Clone the repository:

git clone https://github.com/Oluwayhemisi/health-record-management-system.git


## Run the application:

1. cd health-record-management-system
2. mvn spring-boot:run
 
## API Endpoints

    Access the API documentation:
    Open http://localhost:8080/swagger-ui/ in your browser.

## Testing

    mvn test

## Additional Information

    1. Authentication: Use basic authentication with a valid username and password to access protected endpoints.
    2. Database: The H2 database console is accessible at http://localhost:8080/h2-console (use JDBC URL jdbc:h2:mem:healthrecord).
    3. Customization: Customize database configuration, authentication mechanisms, and API details as needed.
