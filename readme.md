# 🛠️ Java Backend Task
## ✅ Overview
This project is a Spring Boot application that handles order processing with:
MongoDB persistence.

RabbitMQ message queuing.
## 📦 Deliverables
### 1️⃣ Source Code
https://github.com/moohamedEsam/sarmad_back_end_task 

### 2️⃣ Database Schema
MongoDB Collections:

order \
`{
"_id": String, '
"customerName": String,
"status": String, // e.g., PENDING, PROCESSING, COMPLETED, FAILED
"quantity": Float,
"productName" : String
}`
### 3️⃣ Message Queue Configuration
RabbitMQ Setup:

Exchange: exchangeQueue (Direct Exchange) 

Main Queue: orders.queue 

Routing Key: somethingRandom

Max Attempt: 3

### 4️⃣ API Documentation
Example APIs:

Method	Endpoint	Description

POST	/api/orders	Create a new order

GET	/api/orders/{id}	Get order by ID

### Documentation:
Swagger UI available at: http://localhost:8080/swagger-ui.html

### 5️⃣ Instructions
Running the application locally:

✅ Requirements:

Java 23

Gradle

MongoDB

RabbitMQ

### 📝 Notes
#### k6 test
k6 test with 1_000 virtual users then peek at 10_000 virtual users
handled 1_128_041 requests in 1m40s
to run the test run `k6 run k6-test.js`
[Test Case](k6-test.js)

[Test Results](k6-results.txt)
