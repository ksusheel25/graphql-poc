# GraphQL Spring Boot POC

A simple **Spring Boot GraphQL Proof of Concept** demonstrating how to build CRUD APIs using **Spring GraphQL, Spring Data JPA, and H2 Database**. This project uses **Java records** for input DTOs to reduce boilerplate and keep the code clean.

---

## 🚀 Tech Stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring GraphQL
* H2 In-Memory Database
* Lombok

---

## 📁 Project Structure (High Level)

```
com.sushilk.graphqlpoc
 ├── controllers
 │   ├── UserController.java
 │   └── OrderController.java
 ├── dtos
 │   ├── UserInput.java (record)
 │   └── OrderInput.java (record)
 ├── entities
 │   ├── User.java
 │   └── Order.java
 ├── services
 │   ├── UserService.java
 │   └── OrderService.java
 └── resources
     ├── application.properties
     └── schema.graphqls
```

---

## ⚙️ Application Configuration

### `application.properties`

```properties
spring.application.name=graphQL-poc

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=password

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

spring.graphql.graphiql.enabled=true
```

### Useful URLs

* GraphiQL UI: `http://localhost:8080/graphiql`
* H2 Console: `http://localhost:8080/h2-console`

---

## 📜 GraphQL Schema

### `schema.graphqls`

```graphql
type User {
    id: ID!
    name: String
    email: String
    phone: String
    city: String
    orders: [Order]
}

type Order {
    id: ID!
    productName: String
    address: String
    quantity: Int
    price: Float
    status: String
    user: User
}

input UserInput {
    name: String
    email: String
    phone: String
    city: String
}

input OrderInput {
    productName: String!
    address: String
    quantity: Int!
    price: Float!
    status: String!
    userId: ID!
}

type Query {
    getUserById(id: ID!): User
    getAllUsers: [User]
    getOrderById(id: ID!): Order
    getAllOrders: [Order]
}

type Mutation {
    createUser(input: UserInput): User
    deleteUser(id: ID!): Boolean

    createOrder(input: OrderInput): Order
    deleteOrder(id: ID!): Boolean
}
```

---

## ▶️ Running the Application

```bash
mvn spring-boot:run
```

Once started, open **GraphiQL** to test APIs.

---

## 🔑 GraphQL APIs

### 🧑 Create User

**Mutation**

```graphql
mutation {
  createUser(input: {
    name: "Sushil"
    email: "sushil@gmail.com"
    phone: "11111111"
    city: "Bangalore"
  }) {
    id
    name
    email
    city
  }
}
```

---

### 📥 Get All Users

```graphql
query {
  getAllUsers {
    id
    name
    email
    city
  }
}
```

---

### 🛒 Create Order

```graphql
mutation {
  createOrder(input: {
    userId: 1
    productName: "MacBook"
    address: "Bangalore"
    quantity: 1
    price: 150000
    status: "PLACED"
  }) {
    id
    productName
    status
    user {
      name
    }
  }
}
```

---

### 📦 Get All Orders

```graphql
query {
  getAllOrders {
    id
    productName
    quantity
    price
    status
    user {
      name
    }
  }
}
```

---

## 🧾 DTO Design (Why Java Records?)

This project uses **Java record classes** for GraphQL input DTOs:

```java
public record UserInput(String name, String email, String phone, String city) {}
```

### ✅ Why Records?

* Immutable by default
* Less boilerplate (no getters/setters)
* Perfect for request/response models
* Thread-safe

### ❌ When to use normal classes?

* When validation logic or mutation is required
* When frameworks require no-args constructors

---

## ⚠️ Common Issues

### `userInput is null`

* Ensure GraphQL argument name matches exactly
* Use `@Argument("input")`
* Schema input must not be mismatched

---

## 📌 Future Enhancements

* Pagination support
* Validation using `@Validated`
* Exception handling with GraphQL error mapping
* JWT Authentication
* Docker support

---

## 👨‍💻 Author

**Sushil Kumar**
Java Backend Developer | Spring Boot | GraphQL | Microservices

---

⭐ If you found this useful, give the repo a star!
