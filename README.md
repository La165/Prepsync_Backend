# 🚀 PrepSync Backend

PrepSync Backend is a secure REST API service built using Spring Boot.

It powers the PrepSync learning platform by handling authentication, user management, subject tracking, topic management, revision scheduling, weak area analysis, and study progress monitoring.

The backend is deployed on Railway and uses MySQL as the primary database.


This repository is part of the PrepSync full-stack learning platform.

👉 Frontend Repo: https://github.com/La165/Prepsync_Frontend  
👉 Live Backend API: https://prepsyncbackend-production.up.railway.app/

---

## 🌐 Live API

👉 https://prepsyncbackend-production.up.railway.app/

---

## 🛠 Tech Stack

| Technology      | Purpose                        |
| --------------- | ------------------------------ |
| Java 17         | Programming Language           |
| Spring Boot     | Backend Framework              |
| Spring Security | Authentication & Authorization |
| JWT             | Stateless Authentication       |
| Spring Data JPA | Database Access Layer          |
| Hibernate       | ORM Framework                  |
| MySQL           | Relational Database            |
| Maven           | Dependency Management          |
| Railway         | Deployment Platform            |

---

## ✨ Features

* 🔐 JWT Authentication & Authorization
* 👤 User Registration & Login
* 📚 Subject Management
* 📝 Topic Management
* 🔁 Revision Queue Tracking
* ⚠️ Weak Areas Analysis
* 📊 Learning Progress Analytics
* 🧠 Study Plan Support
* 🔒 Protected REST APIs
* 🚀 Production Deployment on Railway

---

## 🏗️ Backend Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database (MySQL)
```

---

## 📁 Project Structure

```text
src/main/java/com/prepsync
│
├── config/          Application & CORS Configuration
├── controller/      REST API Controllers
├── dto/             Request & Response DTOs
├── entity/          JPA Entity Classes
├── enums/           Application Enums
├── exception/       Global Exception Handling
├── jwt/             JWT Utility Classes
├── repository/      JPA Repositories
├── security/        Security Configuration
├── service/         Business Logic Layer
└── PrepsyncApplication.java
```

---

## 🔐 Authentication Flow

1. User registers or logs in
2. Backend validates credentials
3. JWT token is generated
4. Token is returned to frontend
5. Frontend stores token
6. Token is sent in Authorization header
7. Protected APIs validate JWT before processing requests

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 📡 API Endpoints Overview

### 🔑 Authentication APIs

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| POST   | /auth/register | Register New User |
| POST   | /auth/login    | Authenticate User |

### 📚 Subject APIs

| Method | Endpoint       |
| ------ | -------------- |
| GET    | /subjects      |
| POST   | /subjects      |
| PUT    | /subjects/{id} |
| DELETE | /subjects/{id} |

### 📝 Topic APIs

| Method | Endpoint            |
| ------ | ------------------- |
| GET    | /topics             |
| POST   | /topics             |
| PUT    | /topics/{id}        |
| DELETE | /topics/{id}        |
| PUT    | /topics/{id}/revise |

### 🔁 Revision APIs

| Method | Endpoint               |
| ------ | ---------------------- |
| GET    | /topics/overdue        |
| GET    | /topics/revision-queue |

---

## ⚙️ Configuration

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/prepsync
spring.datasource.username=your_username
spring.datasource.password=your_password

jwt.secret=your_secret_key

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

---

## 🌍 CORS Configuration

Allowed Origins:

```text
http://localhost:5173
https://your-vercel-app.vercel.app
```

---

## 🚀 Running Locally

### Clone Repository

```bash
git clone https://github.com/La165/Prepsync_Backend.git
cd Prepsync_Backend
```

### Install Dependencies

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Backend starts at:

```text
http://localhost:8080
```

---

## ☁️ Deployment

### Backend Hosting

* Railway

### Database

* MySQL

### CI/CD

* GitHub → Railway Auto Deploy

### Environment Variables

```text
MYSQLUSER
MYSQLPASSWORD
JWT_SECRET
PORT
```

---

## 🔒 Security Features

* JWT-Based Authentication
* BCrypt Password Encryption
* Stateless Session Management
* Route Protection using Spring Security
* User Ownership Validation
* Global Exception Handling

---

## 📊 Key Highlights

* Clean Layered Architecture
* RESTful API Design
* Scalable Package Structure
* Secure Authentication
* Production Ready Deployment
* MySQL Integration
* Revision Tracking Logic
* Learning Analytics Support

---

## 📌 Sample Login Request

### Request

```http
POST /auth/login
Content-Type: application/json
```

```json
{
  "email": "user@example.com",
  "password": "123456"
}
```

### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

---

## 👩‍💻 Author

**Lalitha**

GitHub: https://github.com/La165
