
# Employee Management System – Spring Boot + Spring Security (JWT)

## Overview

This project is a **RESTful Employee Management System** built using **Spring Boot 3**, **Spring Data JPA**, **MySQL**, and **Spring Security**.

The primary objective is to **implement secure authentication** using **Spring Security** and **JWT (JSON Web Tokens)** while exposing CRUD APIs for employees, departments, and projects.

This repository reflects **incremental development**, with a strong focus on understanding **how Spring Security works under the hood**, rather than rushing to feature completion.

---

## 🧱 Tech Stack

- **Java 17**
- **Spring Boot 3.2.x**
- **Spring Security 6**
- **Spring Data JPA**
- **JWT (jjwt 0.11.5)**
- **MySQL**
- **Gradle**
- **Hibernate**
- **Tomcat (embedded)**

---

## 📂 Project Structure

```

employee-management/
│
├── src/main/java/com/example/employeemanagement
│   ├── config
│   │   └── SecurityConfig.java
│   │
│   ├── controller
│   │   ├── EmployeeController.java
│   │   └── AuthController.java   (in progress)
│   │
│   ├── entity
│   │   ├── Employee.java
│   │   ├── Department.java
│   │   └── Project.java
│   │
│   ├── repository
│   │   ├── EmployeeRepository.java
│   │   ├── DepartmentRepository.java
│   │   └── ProjectRepository.java
│   │
│   ├── security
│   │   ├── EmployeeUserDetails.java
│   │   ├── EmployeeUserDetailsService.java
│   │   ├── JwtUtil.java
│   │   └── JwtAuthenticationFilter.java (next step)
│   │
│   ├── service
│   │   ├── EmployeeService.java
│   │   └── impl/EmployeeServiceImpl.java
│   │
│   └── EmployeeManagementApplication.java
│
├── src/main/resources
│   ├── application.properties
│
└── build.gradle

````

---

## ⚙️ Installation & Setup

### 1️⃣ Prerequisites

Ensure the following are installed:

- Java 17  
  ```
  java -version
  ```

* Gradle

  ```bash
  gradle -v
  ```

* MySQL (running)

---

### 2️⃣ Database Setup

```sql
CREATE DATABASE employee_management;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Build & Run

```bash
./gradlew clean build
./gradlew bootRun
```

Application starts on:

```
http://localhost:8080
```

---

## 🔐 Security Implementation (So Far)

### ✔ Spring Security Enabled

* Default Spring Security filter chain is active
* All endpoints are secured **except authentication APIs**
* CSRF disabled (required for stateless REST APIs)

```java
.requestMatchers("/api/auth/**").permitAll()
.anyRequest().authenticated()
```

---

### ✔ Custom User Authentication

* `Employee` entity acts as the **authenticated user**
* `EmployeeUserDetails` implements `UserDetails`
* `EmployeeUserDetailsService` loads employee from database

```java
public class EmployeeUserDetails implements UserDetails {
    private final Employee employee;
}
```

---

### ✔ Password Hashing (BCrypt)

* Passwords are **never stored in plain text**
* `BCryptPasswordEncoder` is used
* Passwords are hashed **before persisting to DB**

```java
employee.setPassword(passwordEncoder.encode(employee.getPassword()));
```

---

### ✔ Authentication Flow (Current)

1. HTTP request hits Spring Security filter chain
2. Endpoint is checked for public access
3. If protected:

    * Authentication is required
    * Missing authentication → `401 Unauthorized`
4. Password verification is done using BCrypt

---

## 🔑 JWT Support (Foundation Complete)

### ✔ JWT Utility Implemented

* Token generation
* Token validation
* Username extraction

```java
jwtUtil.generateToken(username);
jwtUtil.isTokenValid(token);
jwtUtil.extractUsername(token);
```

JWT properties:

```properties
jwt.secret=very-secret-key
jwt.expiration=3600000
```

📌 JWT is **stateless** — no server-side session storage.

---

## 🔄 High-Level Request & Authentication Flow

### 🧭 JWT-Based Request Lifecycle

```
Client
  |
  |  (1) HTTP Request
  |      - /api/auth/login   (public)
  |      - /api/**           (protected)
  |
  v
Spring Security Filter Chain
  |
  |-- Is endpoint public?
  |     └── YES → Controller
  |
  |-- NO (Protected)
  |     |
  |     |-- JWT present in Authorization header?
  |     |     └── NO → 401 Unauthorized
  |     |
  |     |-- JWT valid & not expired?
  |           └── NO → 401 Unauthorized
  |
  v
JwtAuthenticationFilter
  |
  |-- Extract username from token
  |-- Load Employee via UserDetailsService
  |-- Set Authentication in SecurityContext
  |
  v
Controller → Service → Repository → MySQL
  |
  v
Response → Client
```

---

## 🚀 Upcoming Steps

1. Implement Login API
2. Authenticate credentials using `AuthenticationManager`
3. Generate JWT on successful login
4. Add `JwtAuthenticationFilter`
5. Validate JWT on each request
6. Disable HTTP Basic authentication
7. Enforce fully stateless security

---

## 🧑‍💻 Coding Practices Followed

* Java Style Guide (Google):
  [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html)

* Clean Code principles:
  [https://www.youtube.com/watch?v=UjhX2sVf0eg](https://www.youtube.com/watch?v=UjhX2sVf0eg)

---

## 📚 References & Learning Resources

### Spring & Architecture

* Spring Boot Architecture
  [https://www.javatpoint.com/spring-boot-architecture](https://www.javatpoint.com/spring-boot-architecture)

* Controller–Service–Repository Layers
  [https://sagarkudu.medium.com/how-controller-service-layer-and-dao-layer-works-in-spring-boot-introduction-f7706308eb9a](https://sagarkudu.medium.com/how-controller-service-layer-and-dao-layer-works-in-spring-boot-introduction-f7706308eb9a)

* MySQL Integration
  [https://spring.io/guides/gs/accessing-data-mysql/](https://spring.io/guides/gs/accessing-data-mysql/)

---

### Validation

* Request Validation in Spring Boot
  [https://medium.com/@tericcabrel/validate-request-body-and-parameter-in-spring-boot-53ca77f97fe9](https://medium.com/@tericcabrel/validate-request-body-and-parameter-in-spring-boot-53ca77f97fe9)

---

### Dependency Injection (Deep Dive)

* Baeldung – IoC & DI
  [https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring)

* Martin Fowler – Dependency Injection
  [https://martinfowler.com/articles/injection.html](https://martinfowler.com/articles/injection.html)

* Spring Docs (Beans)
  [https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/beans.html](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/beans.html)

---

## 🧪 Testing (Planned Extension)

### Tools

* JUnit 5
* Mockito
* H2 (Test DB)

### Testing Strategy

* Unit Tests (mocked dependencies)
* Integration Tests (`@WebMvcTest`, `@DataJpaTest`)
* End-to-End Tests (`@SpringBootTest`)

References:

* Testing Spring Boot Applications
* Mocking in Spring Boot
* Code Coverage in IntelliJ

---
