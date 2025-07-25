# JobWeb

## Overview
JobWeb is a full-stack web application for managing clubs and events. It provides secure user authentication, registration, and role-based access control, allowing users to create, edit, and view clubs and events through a modern, responsive interface.

## Features
- User registration and login
- Role-based access control (admin, user)
- CRUD operations for clubs and events
- Responsive UI with Thymeleaf templates
- Secure authentication with Spring Security
- Asset management (images, CSS, JS)

## Technologies Used
- Java, Spring Boot
- Maven
- Thymeleaf
- HTML, CSS, JavaScript
- Spring Security

## Getting Started

### Prerequisites
- Java 17+
- Maven

### Installation
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd JobWeb
   ```
3. Build the project:
   ```
   ./mvnw clean install
   ```
4. Run the application:
   ```
   ./mvnw spring-boot:run
   ```
5. Access the app at `http://localhost:8080`

### Configuration
Edit `src/main/resources/application.properties` to configure database and other settings.

## Folder Structure
- `src/main/java/com/QT/JobWeb/` – Java source code
- `src/main/resources/static/` – Static assets (CSS, JS, images)
- `src/main/resources/templates/` – Thymeleaf HTML templates

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License.
