# 🏍️ Harley Davidson Events

**Author:** Mariya Byehan  
**Bootcamp:** Backend + DevOps – Final Individual Project

---
 **General Objective**
This project was developed as the final individual project of the Backend + DevOps bootcamp.  
The goal is to put into practice all the concepts learned during the training, by designing and building a backend API that is **secure, scalable, and professional**.

The theme is **Harley-Davidson events**, created to unify the management of biker events, rides, and meetups, which are often organized in fragmented ways through social networks or private groups.

---
**Specific Objectives**
- Allow **admins** to publish and manage Harley-Davidson events.
- Allow **registered users (riders)** to view events, register for them, and cancel their participation.
- Guarantee **secure authentication** using JWT and **role-based access control**.
- Build a **RESTful API** following best practices, validations, DTOs, and custom exceptions.

---
**Project Description**
**Harley Events** is a RESTful API built with **Java Spring Boot** and connected to a **MySQL** database.

- **Admin role**: can create, update, delete, and list events.
- **Rider role**: can view available events and register to participate.
- **Authentication**: based on JWT tokens with role-based permissions.
- **Entities**: `User` and `Event` with a `Many-to-Many` relationship (users participate in multiple events).
- **Error handling**: through validations and custom exceptions.
- **Documentation**: via Swagger/Postman collections.

---
**Tech Stack**

**Backend**
- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- MySQL

**Tools**
- IntelliJ IDEA
- Jira (project management)
- Git & GitHub
- Postman

---

**MVP Functional Requirements**
- CRUD for **User** and **Event** entities.
- Many-to-Many relationship between User and Event.
- User Registration & Login (JWT-based).
- Roles:
    - `ADMIN`: view, create, edit, delete events.
    - `RIDER`: view events, register/cancel participation.
- DTOs with field validations.
- Custom exceptions.

---

**Non-Functional Requirements**
- Conventional commits.
- Spring Boot + Spring Security with JWT.
- Code quality: DTOs, validations, exception handling.
- Good practices in architecture and documentation.

---

**Project Architecture**
- **3-layer MVC architecture**:
    - **Controller Layer** → Handles HTTP requests/responses.
    - **Service Layer** → Business logic.
    - **Repository Layer** → Database access.

- **Security Layer**: Authentication & Authorization with JWT.

---

**Endpoints (MVP)**

### Authentication
- `POST /auth/register` → Register a new user.
- `POST /auth/login` → Authenticate and get JWT token.

### Events
- `GET /events` → List all events (all users).
- `GET /events/{id}` → Get event by ID.
- `POST /events` → Create new event (**ADMIN**).
- `PUT /events/{id}` → Update event (**ADMIN**).
- `DELETE /events/{id}` → Delete event (**ADMIN**).

### User-Event Participation
- `POST /events/{id}/join` → Rider joins event (**RIDER**).
- `DELETE /events/{id}/leave` → Rider cancels participation (**RIDER**).

---

**How to Run the Project**

### Prerequisites
- Java 21 installed
- MySQL running locally

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/Marichka75/harley-events.git
   cd harley-events
