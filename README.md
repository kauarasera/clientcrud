
# Client CRUD API - Spring Boot

This project was developed as part of the **Modern Developer Training** course by [DevSuperior](https://devsuperior.com.br). It is a RESTful API for managing clients, featuring full CRUD operations.

## 🛠️ Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Web
- Bean Validation
- H2 In-Memory Database
- Maven

## 📚 Features

The API provides the following endpoints:

- ✅ **GET /clients** → Paginated list of clients
- ✅ **GET /clients/{id}** → Retrieve a client by ID
- ✅ **POST /clients** → Insert a new client
- ✅ **PUT /clients/{id}** → Update an existing client
- ✅ **DELETE /clients/{id}** → Delete a client

## 📋 Validation Rules

- `name`: must not be blank
- `birthDate`: must be in the past or present (`@PastOrPresent`)

## ⚠️ Exception Handling

- **404 Not Found** → Client not found by ID (GET, PUT, DELETE)
- **422 Unprocessable Entity** → Validation errors with custom messages per field

## 🔌 Postman Testing

All endpoints were tested using [Postman](https://www.postman.com/), including:

- Paginated searches with query parameters
- Insertions with valid and invalid data
- Updates with validation checks
- Deletion of existing and non-existing clients

## 💾 Database

The application uses an in-memory **H2 database**, with an automatic seed of 10 meaningful clients for testing purposes.

---

## 📁 Project Structure

```
clientcrud
├── src/main/java
│   └── com.kauarasera.clientcrud
│       ├── entities
│       ├── repositories
│       ├── services
│       ├── controllers
│       └── dtos
└── ...
```

## 🚀 How to Run the Project

1. Clone the repository:
   ```
   git clone https://github.com/kauarasera/clientcrud.git
   ```

2. Open the project in your IDE (IntelliJ or Eclipse)

3. Run the main class (`Main.java`)

4. (Optional) Access the H2 console at:
   ```
   http://localhost:8080/h2-console
   ```

5. Test the endpoints using Postman

---

## 👨‍💻 Author

Created with dedication by **Kauã Rasera**  
🔗 [LinkedIn](https://www.linkedin.com/in/kauarasera)  
📧 kauarasera.dev@outlook.com
