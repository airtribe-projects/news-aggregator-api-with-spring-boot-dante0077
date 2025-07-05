# 📰 News Aggregator API (Spring Boot)

A simple RESTful API built using **Spring Boot** that allows users to register, log in, manage their news preferences, and fetch news articles based on those preferences from external news sources like **NewsAPI.org**.

---

## 📌 Features

- ✅ User registration and login with **JWT token-based authentication**
- ✅ Store user data and news preferences using an **in-memory H2 database**
- ✅ Fetch personalized news articles based on user preferences
- ✅ Secure endpoints with **Spring Security**
- ✅ Make external API calls using **WebClient**
- ✅ Exception handling for validation, authentication, and external API errors
- ✅ Input validation using **Hibernate Validator** annotations
- ✅ Tested using **Postman** and **curl**

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- H2 In-Memory Database
- JWT (JSON Web Token)
- WebClient (Reactive HTTP Client)
- Gradle

---

## 📁 Project Structure
##
```plaintext
src/main/java/com/news/aggregator/
│
├── controller/ # API endpoints (Register, Login, Preferences, News)
├── service/ # Business logic (UserService, NewsService)
├── model/ # Entities and DTOs (User, Article, NewsApiResponse)
├── security/ # JWT filters, token utilities
├── config/ # WebClient and Security configurations
└── NewsAggregatorApiApplication.java
```
---

## 🔐 Authentication

- **JWT** is used for stateless authentication.
- Token is issued upon login and must be included in `Authorization` header for protected endpoints.

Authorization: Bearer <token>

---

## 🧪 REST API Endpoints

### 🔸 Auth & User

| Method | Endpoint         | Description                    | Auth Required |
|--------|------------------|--------------------------------|---------------|
| POST   | `/api/register`  | Register a new user            | ❌            |
| POST   | `/api/login`     | Log in and receive JWT token   | ❌            |

### 🔸 Preferences

| Method | Endpoint             | Description                          | Auth Required |
|--------|----------------------|--------------------------------------|---------------|
| GET    | `/api/preferences`   | Get user's current news preferences  | ✅            |
| PUT    | `/api/preferences`   | Update news preferences              | ✅            |

### 🔸 News

| Method | Endpoint       | Description                                 | Auth Required |
|--------|----------------|---------------------------------------------|---------------|
| GET    | `/api/news`    | Get news articles based on user preferences| ✅            |

---

## 🧑‍💻 Sample User Flow

1. **Register**
   ```bash
   POST /api/register
   {
     "email": "user@example.com",
     "password": "securePassword123"
   }
2. **Login**
    ```bash
    POST /api/login
    {
      "email": "user@example.com",
      "password": "securePassword123"
    }
   Response:
   {
      "token": "<JWT_TOKEN>"
    }
3. **Update Preferences**

    ```bash
    PUT /api/preferences
    Authorization: Bearer <JWT_TOKEN>
    Body:
    ["technology", "sports", "business"]

4. **Get News**

    ```bash
    GET /api/news
    Authorization: Bearer <JWT_TOKEN>
🛡️ Input Validation
    Email must be valid 
    Password must have minimum length

Preferences list cannot be empty
Implemented using @NotBlank, @Size, etc.

❗ Error Handling
Global exception handling for:

Validation errors

Authentication failures

Authorization errors

External API issues

🌐 **External News API**
This app integrates with:

NewsAPI.org

Free tier: 100 requests/day — make sure to sign up and use your API key in the NewsService.

🚀 Running the Application

**Gradle**
    
    ```bash 
    ./gradlew bootRun
H2 Console (for debugging):
http://localhost:8080/h2-console
(JDBC URL: jdbc:h2:mem:newsdb)

🧪 **Testing**
Use Postman or curl to test each endpoint.
Don't forget to include the JWT token for protected routes.

📦 **Future Improvements**
Persist users in a real database (e.g., PostgreSQL)

📬 License
This project is for educational/demo purposes only. Free to use and modify.

