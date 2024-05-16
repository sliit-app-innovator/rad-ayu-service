# RAD Ayu Service

This repository contains the RAD Ayu Service, a backend service developed in Java for handling various operations related to the Ayu application.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [File Structure](#file-structure)
- [Contributors](#contributors)
- [License](#license)

## Installation

### Prerequisites
- Java 17 or higher
- Maven
- MySQL

### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/sliit-app-innovator/rad-ayu-service.git
    cd rad-ayu-service
    ```

2. Set up the database:
    - Create a MySQL database.
    - Run the scripts in the `db_scripts` folder to set up the necessary tables.

3. Configure the application:
    - Update the `application.properties` file with your database configurations.

4. Build the project:
    ```bash
    mvn clean install
    ```

5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage
- The service exposes various endpoints for managing the operations of the Ayu application.
- Detailed API documentation is available in the [Link](https://3.134.58.170/swagger-ui/index.html) .
  