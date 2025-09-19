# 🗂️ Task Scheduler & Document Management Backend with Spring Boot & MongoDB with Mockito TEST🚀

<p align="center">
  <img src="https://spring.io/images/projects/spring-boot-624x351.png" alt="Spring Boot" width="280" />
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://webassets.mongodb.com/_com_assets/cms/mongodb-logo-rgb-j6w271g1xn.jpg" alt="MongoDB" width="180" />
  &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Clock_icon.svg/1024px-Clock_icon.svg.png" alt="Scheduler" width="80" />
</p>

---

## 📜 Overview

This project provides a backend system for **document management** and **scheduled task execution** built using **Spring Boot** and **MongoDB**.

- Upload and store documents as binary data in MongoDB
- Manage scheduled tasks with automatic execution using Spring’s task scheduler
- Monitor application health and tasks using Spring Boot Actuator
- Clean layered architecture with full unit test coverage

---

## ⚙️ Project Setup

### Step 1: Create Project via Spring Initializr  
1. **Project**: Maven  
2. **Language**: Java (17+)  
3. **Spring Boot**: latest 3.x version  
4. **Packaging**: Jar  
5. **Dependencies**: Spring Web, Spring Data MongoDB, Spring Boot Actuator, Validation, Lombok, Spring Boot Test

> Download and open in any IDE (IntelliJ/Eclipse/VS Code)

### Step 2: Configure MongoDB


spring.data.mongodb.database=task_doc_db
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017

Actuator monitoring enabled
management.endpoints.web.exposure.include=*

text

---

## 🧱 Code Structure

### Models

- **Document.java**  
  Holds files with fields for name, type, and content bytes
  
- **ScheduledTask.java**  
  Stores scheduled jobs with name, scheduled time, and status (`PENDING` or `COMPLETED`)

### Repositories

- `DocumentRepository` and `ScheduledTaskRepository` extend `MongoRepository` for CRUD operations.

### Services

- `DocumentService`  
  Upload documents and query all document data.
  
- `TaskSchedulerService`  
  Runs scheduled jobs every minute and updates task statuses.

### Controllers

- `DocumentController`  
  Endpoints to upload and list documents.
  
- `ScheduledTaskController`  
  Endpoints to create and retrieve scheduled tasks.

---

## 🛠 How to Run

1. Start your MongoDB service locally or connect to MongoDB Atlas  
2. Build the project using:

mvn clean install

text

3. Launch Spring Boot app:

mvn spring-boot:run

text

4. API Endpoints Examples:

| HTTP Method | Endpoint              | Description                 |
| ----------- |-----------------------|-----------------------------|
| POST        | /api/documents/upload | Upload a document            |
| GET         | /api/documents        | Retrieve all documents       |
| POST        | /api/tasks            | Create a scheduled task      |
| GET         | /api/tasks            | List all scheduled tasks     |

---

## 🧪 Testing

We use **JUnit 5** with **Mockito** and Spring’s testing utilities to ensure code quality.


## 🧪 Testing Coverage

### Mockito

- Mocked repository dependencies to isolate service layer logic.
- Stubbed repository methods with expected return values using `when(...).thenReturn(...)`.
- Verified interactions between service and repository layers.
  
### MockMvc

- Simulated REST API calls to controller endpoints.
- Tested HTTP status codes and JSON response bodies.
- Ensured robust controller logic independent of actual service or repository layers.

---

## 🛠 How to Run Tests

Run all unit and integration tests with:

mvn test

text

Tests cover service methods like document upload, retrieval, and scheduled task executions, as well as RESTful controllers for documents and task management.

---



## 🚀 Future Improvements

- Add file metadata search & filtering  
- Add user authentication & authorization  
- Gracefully handle task failures and retries  
- Full Swagger/OpenAPI documentation  
- Add front-end integration with React or Angular  

---

## 📸 Sample API Response for Document List

[
{
"id": "111",
"name": "example.pdf",
"type": "application/pdf",
"content": "base64encodeddata"
}
]



---

## 👨‍💻 Author

Sangeetha Ravichandran

---

## 📄 License

This project is licensed under the MIT License.

---

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Java_Logo.svg/1280px-Java_Logo.svg.png" alt="Java" width="120" />
  &nbsp;&nbsp;&nbsp;
  <img src="https://spring.io/images/branding/spring-logo.svg" alt="Spring" width="120"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://webassets.mongodb.com/_com_assets/cms/mongodb-logo-rgb-j6w271g1xn.jpg" alt="MongoDB" width="120"/>
</p>

Install locally or use MongoDB Atlas and set connection details in `src/main/resources/application.properties`:

