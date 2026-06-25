# LIC Policy Management System

## Project Overview


LIC Policy Management System is a backend application developed using Spring Boot, Spring Security, JWT, Hibernate (JPA), and MySQL.

The system allows users to register, login securely, manage insurance policies, create claims, and enables administrators to approve or reject claims using role-based access control.

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Maven
- Postman

---

## Project Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

### Controller

Handles HTTP requests from Postman or Frontend.

Example:

```http
POST /api/users/login
```

### Service

Contains business logic.

Example:

```text
Validate Email
Validate Password
Generate JWT Token
```

### Repository

Communicates with the database.

Example:

```java
userRepository.findByEmail(email);
```

---

## User Module

### Registration

```http
POST /api/users
```

Request:

```json
{
  "username": "Vishnu",
  "email": "vishnu@gmail.com",
  "password": "12345",
  "role": "CUSTOMER"
}
```

### Registration Flow

1. User sends registration request.
2. Email uniqueness is checked.
3. Password is encrypted using BCrypt.
4. User details are stored in database.

---

### Login

```http
POST /api/users/login
```

Request:

```json
{
  "email": "vishnu@gmail.com",
  "password": "12345"
}
```

### Login Flow

1. User enters email and password.
2. Credentials are validated.
3. JWT token is generated.
4. Token is returned to the user.

Example Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## Why BCrypt?

Passwords should never be stored in plain text.

Without BCrypt:

```text
12345
```

With BCrypt:

```text
$2a$10$5fP...
```

BCrypt encrypts passwords and improves security.

---

## JWT Authentication Flow

```text
User Login
    ↓
JWT Token Generated
    ↓
Token Sent in Authorization Header
    ↓
JwtAuthenticationFilter
    ↓
Token Validation
    ↓
Extract User Email
    ↓
Load User Details
    ↓
Store Authentication in SecurityContext
    ↓
Access Protected APIs
```

### Example Authorization Header

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## Authentication vs Authorization

### Authentication

**Who are you?**

Example:

```text
Email + Password Login
```

Result:

```text
Identity Verified
```

### Authorization

**What are you allowed to do?**

ADMIN:

- Manage Users
- Approve Claims
- Reject Claims
- Access Dashboard

CUSTOMER:

- View Policies
- Create Claims

---

## Roles

### ADMIN

Responsibilities:

- View Users
- Delete Users
- Manage Policies
- View Claims
- Approve Claims
- Reject Claims
- Access Dashboard

### CUSTOMER

Responsibilities:

- Register
- Login
- View Policies
- Create Claims

---

## Policy Module

Stores insurance policy information.

Example:

```http
POST /api/policies
```

Stores:

- Policy Name
- Premium Amount
- Coverage Amount
- Policy Term

---

## Claim Module

Used when a customer requests insurance benefits.

Example:

```http
POST /api/claims
```

Request:

```json
{
  "policyId": 1,
  "claimAmount": 50000,
  "claimReason": "Medical Emergency"
}
```

### Claim Lifecycle

```text
Claim Created
    ↓
PENDING
    ↓
Admin Review
    ↓
APPROVED / REJECTED
```

### Approve Claim

```http
PUT /api/claims/{id}/approve
```

### Reject Claim

```http
PUT /api/claims/{id}/reject
```

---

## Admin Dashboard

Endpoint:

```http
GET /admin/dashboard
```

Accessible only by ADMIN users.

Example Response:

```json
{
  "totalUsers": 5,
  "totalCustomers": 3,
  "totalPolicies": 2,
  "totalClaims": 1
}
```

Dashboard provides system statistics.

---

## Validation

Implemented using Jakarta Validation.

Examples:

```java
@NotBlank
@Email
@Size
```

Benefits:

- Prevent invalid inputs
- Improve data quality

---

## Exception Handling

Global Exception Handling is implemented for:

- User Not Found
- Policy Not Found
- Claim Not Found
- Duplicate Email
- Validation Errors

Benefits:

- Consistent API responses
- Cleaner controller code

---

## Database Tables

```text
users
customers
policies
claims
```

---

## Complete Business Flow

```text
User Registration
        ↓
Password Encryption (BCrypt)
        ↓
User Login
        ↓
JWT Token Generated
        ↓
Access Protected APIs
        ↓
Customer Creates Claim
        ↓
Claim Status = PENDING
        ↓
Admin Reviews Claim
        ↓
Approve / Reject Claim
        ↓
Dashboard Displays Statistics
```

---

## Key Features

✅ Spring Boot REST APIs

✅ MySQL Database Integration

✅ Spring Data JPA

✅ DTO Pattern

✅ BCrypt Password Encryption

✅ JWT Authentication

✅ Spring Security

✅ Role-Based Authorization

✅ Claim Approval Workflow

✅ Admin Dashboard

✅ Validation

✅ Global Exception Handling

---

