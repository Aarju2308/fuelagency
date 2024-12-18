
# Cylinder Management Module

### Overview
The Cylinder Management module is a part of the Fuel Agency Operations Suite, designed to streamline the handling of gas cylinders. This module facilitates inventory management, including tracking cylinder types (full/empty), statuses (available/delivered), and managing refill processes.

### Key Features
- **Cylinder Registration**: Add new cylinders to the system with detailed attributes (weight, type, status, and date).
- **Type & Status Updates**: Update cylinder types when returned and statuses upon delivery or refill.
- **Reorder Notifications**: Automatically send email notifications to administrators when available cylinders fall below customer demand.

### Technologies Used
- **Backend**: Java, Spring Boot REST Services, Spring Boot Data JPA, MySQL
- **Frontend**: Thymeleaf

### Team 2 Members
- Aarju Patel
- Prithika S
- Harshitha Sure
- Sanvi Agarwal

### Setup Instructions
1. Clone the repository:
   ```bash  
   git clone https://github.com/Aarju2308/fuelagency.git  
   cd fuelagency 
   ```  
2. Configure the database:
    - Set up MySQL and create a database for the module.
    - Update the `application.properties` file with your database credentials.
3. Run the application:
   ```bash  
   mvn spring-boot:run  
   ```  
4. Access the frontend:  
   Open your browser and navigate to `http://localhost:8080`.

### Contribution Guidelines
- Follow the coding standards outlined in the Fuel Agency Operations Suite project.
- Ensure proper testing for all features before merging.

### Contact
For queries or support, please contact **Team 2**.  
