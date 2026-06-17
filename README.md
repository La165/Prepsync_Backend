🚀 PrepSync Backend

PrepSync Backend is a secure REST API service built using Spring Boot.

It powers the PrepSync learning platform by handling authentication, user management, subject tracking, topic management, revision scheduling, weak area analysis, and study progress monitoring.

The backend is deployed on Railway and uses MySQL as the primary database.

🌐 Live API

👉 https://prepsyncbackend-production.up.railway.app/

🛠 Tech Stack
Technology	Purpose
Java 17	Programming Language
Spring Boot	Backend Framework
Spring Security	Authentication & Authorization
JWT	Stateless Authentication
Spring Data JPA	Database Access Layer
Hibernate	ORM Framework
MySQL	Relational Database
Maven	Dependency Management
Railway	Deployment Platform
✨ Features
🔐 JWT Authentication & Authorization
👤 User Registration & Login
📚 Subject Management
📝 Topic Management
🔁 Revision Queue Tracking
⚠️ Weak Areas Analysis
📊 Learning Progress Analytics
🧠 Study Plan Support
🔒 Protected REST APIs
🚀 Production Deployment on Railway
📸 Application Screenshots

⚠️ Make sure your images are inside a folder like:
screenshots/

Then use this format:

🔐 Login

📝 Register

📊 Dashboard

📚 Subjects Management

📝 Topics Management

🔁 Revision Queue

⚠️ Weak Areas

🧠 Study Plan

👤 Profile

🏗️ Backend Architecture
Controller
    ↓
Service
    ↓
Repository
    ↓
Database (MySQL)
📁 Project Structure
src/main/java/com/prepsync
│
├── config/
├── controller/
├── dto/
├── entity/
├── enums/
├── exception/
├── jwt/
├── repository/
├── security/
├── service/
└── PrepsyncApplication.java
🔐 Authentication Flow
User registers or logs in
Backend validates credentials
JWT token is generated
Token returned to frontend
Frontend stores token
Token sent in Authorization header
Backend validates token for protected APIs
Authorization: Bearer <JWT_TOKEN>
📡 API Endpoints
🔑 Auth
Method	Endpoint
POST	/auth/register
POST	/auth/login
📚 Subjects
Method	Endpoint
GET	/subjects
POST	/subjects
PUT	/subjects/{id}
DELETE	/subjects/{id}
📝 Topics
Method	Endpoint
GET	/topics
POST	/topics
PUT	/topics/{id}
DELETE	/topics/{id}
PUT	/topics/{id}/revise
🔁 Revision
Method	Endpoint
GET	/topics/overdue
GET	/topics/revision-queue
⚙️ Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/prepsync
spring.datasource.username=your_username
spring.datasource.password=your_password

jwt.secret=your_secret_key

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
🌍 CORS Configuration
http://localhost:5173
https://your-vercel-app.vercel.app
🚀 Running Locally
git clone https://github.com/La165/Prepsync_Backend.git
cd Prepsync_Backend
mvn clean install
mvn spring-boot:run

Backend runs at:

http://localhost:8080
☁️ Deployment
Railway (Backend Hosting)
MySQL (Database)
GitHub → Railway Auto Deploy
🔒 Security Features
JWT Authentication
BCrypt Password Encryption
Stateless Session Management
Role-based API Protection
Global Exception Handling
👩‍💻 Author

Lalitha
GitHub: https://github.com/La165
