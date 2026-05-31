
🚀 PrepSync Backend

PrepSync Backend is a secure REST API service built using Spring Boot.
It powers the PrepSync learning platform by handling authentication, user data, subjects, topics, and progress tracking.

The backend is deployed on Railway and uses MySQL as the database.

🌐 Live API Base URL

👉 https://prepsyncbackend-production.up.railway.app/

🛠 Tech Stack
☕ Java 17+
🌱 Spring Boot
🔐 Spring Security + JWT Authentication
🗄️ Spring Data JPA (Hibernate)
🐬 MySQL Database
🚂 Railway (Deployment)
📦 Maven
📁 Project Structure
src/main/java/com/prepsync
│
├── config/        # CORS, application configuration
├── controller/    # REST API endpoints
├── dto/           # Data Transfer Objects
├── entity/        # Database entities
├── enums/         # Application enums (status, roles, etc.)
├── exception/     # Global exception handling
├── jwt/           # JWT utilities and filters
├── repository/    # JPA repositories
├── security/      # Security configuration
├── service/       # Business logic layer
└── PrepsyncApplication.java
🔐 Authentication Flow (JWT)
User registers or logs in
Backend validates credentials
JWT token is generated
Token is returned to frontend
Frontend stores token in localStorage
Token is sent in every request:
Authorization: Bearer <token>
📡 API Endpoints Overview
🔑 Auth APIs
POST /auth/register → Register new user
POST /auth/login → Authenticate user & get token
📚 Core APIs (Protected)
Subjects management
Topics tracking
Weak areas analysis
Study plan generation
Revision tracking
⚙️ Configuration (application.properties)
spring.datasource.url=jdbc:mysql://<host>:<port>/<database>
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
🌍 CORS Configuration

Frontend is deployed on Vercel.

Allowed origins:

http://localhost:5173
https://your-vercel-frontend-url
🚀 Deployment
Backend hosted on Railway
Auto-deploy from GitHub
MySQL database connected via Railway plugin
Environment variables managed in Railway dashboard

🔐 Security Features
JWT-based authentication
Password encryption using BCrypt
Protected REST APIs
Role-based access support (if implemented)
Stateless session management

🧠 Key Highlights
Clean layered architecture (Controller → Service → Repository)
Scalable package structure
Secure authentication system
Production-ready deployment setup
RESTful API design

📌 Example Request (Login)
POST /auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "123456"
}
Response:
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
👩‍💻 Author

Lalitha

GitHub: https://github.com/La165   
