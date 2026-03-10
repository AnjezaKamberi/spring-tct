# Spring Boot Library Management System

## Overview
This is a Spring Boot application that implements a library management system with user authentication using Spring Security with email and password.

## Technologies Used
- Spring Boot 3.5.10
- Spring Security
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- Maven
- Java 17

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running the Application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Database Configuration

The application uses H2 in-memory database with the following configuration:
- **JDBC URL**: `jdbc:h2:mem:libraryDB`
- **Username**: `test`
- **Password**: `test`
- **H2 Console**: `http://localhost:8080/h2-console`

## Default Test Users

After application startup, these test users are automatically created:

- **Admin User**
  - Email: `admin@example.com`
  - Password: `admin123`
  - Role: ADMIN

- **Regular User**
  - Email: `user@example.com`
  - Password: `user123`
  - Role: USER

## API Endpoints

### Authentication Endpoints (Public - No Auth Required)

#### 1. Register New User
```http
POST /auth/register
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "test123",
  "name": "Test User",
  "role": "USER"
}
```

**Response (201 Created):**
```json
{
  "id": 3,
  "email": "test@example.com",
  "name": "Test User",
  "role": "USER",
  "password": null
}
```

#### 2. Login
```http
POST /auth/login
Content-Type: application/json

{
  "email": "admin@example.com",
  "password": "admin123"
}
```

**Response (200 OK):**
```json
{
  "message": "Login u krye me sukses",
  "email": "admin@example.com",
  "name": "Administrator",
  "role": "ADMIN"
}
```

**Error Response (401 Unauthorized):**
```
Email ose fjalekalim i gabuar
```

### Protected Endpoints (Require Authentication)

All `/liber/**` and `/autor/**` endpoints require HTTP Basic Authentication.

#### Example: Get All Books (with Auth)
```http
GET /liber/all
Authorization: Basic base64(email:password)
```

Using curl:
```bash
curl -u admin@example.com:admin123 http://localhost:8080/liber/all
```

Using Postman:
1. Go to Authorization tab
2. Select "Basic Auth"
3. Username: `admin@example.com`
4. Password: `admin123`

## Security Configuration

### Public Endpoints (No Authentication)
- `/auth/**` - Registration and login
- `/tct/**` - Test endpoints
- `/h2-console/**` - H2 database console

### Protected Endpoints (Authentication Required)
- `/liber/**` - Book management
- `/autor/**` - Author management
- All other endpoints

## Testing with Postman

### 1. Test Login
```http
POST http://localhost:8080/auth/login
Body (raw JSON):
{
  "email": "admin@example.com",
  "password": "admin123"
}
```

### 2. Test Registration
```http
POST http://localhost:8080/auth/register
Body (raw JSON):
{
  "email": "newuser@example.com",
  "password": "password123",
  "name": "New User"
}
```

### 3. Test Protected Endpoint
```http
GET http://localhost:8080/liber/all
Authorization: Basic Auth
  Username: admin@example.com
  Password: admin123
```

## Security Features

### Password Encryption
- Passwords are encrypted using BCrypt
- Strength: BCrypt with default rounds (10)
- Passwords are never returned in API responses

## Project Structure

```
src/main/java/com/example/demo/
├── config/
│   ├── DataInitializer.java       # Initialize test users
│   └── SecurityConfig.java        # Spring Security configuration
├── controllers/
│   ├── AuthController.java        # Authentication endpoints
│   ├── AuthorController.java      # Author management
│   ├── LiberController.java       # Book management
│   └── TCTController.java         # Test endpoints
├── entities/
│   ├── AutorEntity.java           # Author entity
│   ├── LiberEntity.java           # Book entity
│   └── UserEntity.java            # User entity
├── exceptions/
│   ├── GlobalExceptionHandler.java
│   ├── LiberIsbnExistsException.java
│   └── LiberNotFoundException.java
├── mappers/
│   └── LiberMapper.java           # DTO mapping
├── models/
│   ├── ApiResponseError.java
│   ├── AuthorDTO.java
│   ├── AuthResponse.java          # Login response
│   ├── LiberDTO.java
│   ├── LoginRequest.java          # Login request
│   └── UserDTO.java               # User DTO
├── repositories/
│   ├── LiberRepository.java
│   └── UserRepository.java        # User data access
├── security/
│   ├── CustomUserDetails.java     # UserDetails implementation
│   └── CustomUserDetailsService.java # UserDetailsService implementation
├── services/
│   ├── LiberService.java
│   └── UserService.java           # User service
└── DemoApplication.java           # Main application class
```

## License
This project is for educational purposes.

