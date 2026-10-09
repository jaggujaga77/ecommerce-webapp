# E-Commerce Web Application

A full-stack e-commerce web application developed using React.js, Java, Spring Boot, and MySQL. The application provides product browsing, user registration and login, and wallet management functionality.

## Project Overview

This project demonstrates the development of an online shopping application with a React.js frontend and Spring Boot backend services. It uses MySQL for data persistence and JWT-based authentication for user access.

## Technologies Used

**Frontend**

* HTML5
* CSS3
* JavaScript
* React.js

**Backend**

* Java
* Spring Boot
* REST APIs
* JWT Authentication

**Database**

* MySQL

**Tools**

* Git and GitHub
* npm

## Key Features

* User registration and login
* JWT-based user authentication
* Secure password hashing
* Product listing and retrieval
* User profile retrieval
* Wallet balance management
* REST API integration between frontend and backend services

## Application Architecture

The application consists of three main components:

1. **Frontend:** React.js user interface.
2. **User Service:** Handles user-related operations, authentication, and wallet management.
3. **Product Service:** Provides product-related functionality through REST APIs.

## Project Structure

```text
ecommerce-webapp/
├── backend/
│   ├── user-service/
│   └── product-service/
├── frontend/
├── LICENSE
└── README.md
```

*Note: The exact backend folder names and structure may differ. Refer to the actual project files.*

## Prerequisites

Install the following software before running the application:

* Java Development Kit (JDK)
* Node.js and npm
* MySQL Server
* Git

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/jaggujaga77/ecommerce-webapp.git
cd ecommerce-webapp
```

### 2. Configure the Database

Create the required MySQL database:

```sql
CREATE DATABASE stock_db;
```

Update the database username and password in the `application.properties` files for the relevant backend services.

```properties
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

Use your local MySQL credentials. Do not commit real passwords or other secrets to GitHub.

### 3. Install Frontend Dependencies

```bash
cd frontend
npm install
```

### 4. Configure and Run the Backend

Open the backend services in your Java IDE or run them using their available Maven or Gradle configuration. Configure the database connection and frontend CORS origin for each relevant service.

Ensure both backend services are running before using the frontend.

### 5. Run the Frontend

From the `frontend` directory, run:

```bash
npm start
```

If the project uses Vite or another development server, use the command specified in `package.json`, such as `npm run dev`.

## Backend API Endpoints

| Method | Endpoint                     | Description                  |
| ------ | ---------------------------- | ---------------------------- |
| GET    | `/products/`                 | Retrieve all products        |
| GET    | `/users/{userName}`          | Retrieve user details        |
| POST   | `/users/{userName}/addMoney` | Add money to a user's wallet |
| POST   | `/signin`                    | Authenticate a user          |
| POST   | `/signup`                    | Register a new user          |

**Default service addresses documented in the original project:**

* Product service: `http://localhost:8080`
* User service: `http://localhost:9000`

The actual ports and endpoints depend on the application configuration.

## Learning Outcomes

This project provides practical exposure to:

* Full-stack web application development
* React.js frontend development
* Java and Spring Boot backend services
* REST API communication
* MySQL database integration
* JWT-based authentication
* Git and GitHub version control

