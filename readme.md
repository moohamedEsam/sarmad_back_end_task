# 🛠️ Java Backend Task \
## ✅ Overview \
This project is a Spring Boot application that handles order processing with:

MongoDB persistence.\
RabbitMQ message queuing.\
## 📦 Deliverables
### 1️⃣ Source Code

2️⃣ Database Schema
MongoDB Collections:
javascript
Copy
Edit
// Collection: orders
{
"_id": ObjectId,
"customerId": String,
"status": String, // e.g., NEW, PROCESSING, COMPLETED, FAILED
"items": [
{ "productId": String, "quantity": Number }
],
"createdAt": ISODate,
"updatedAt": ISODate
}
3️⃣ Message Queue Configuration
RabbitMQ Setup:
Exchange: orders.exchange (Direct Exchange)
Main Queue: orders.queue
Dead Letter Queue (DLQ): orders.dead.queue
Routing Key: orders.route
Queue Arguments:
x-dead-letter-exchange: "" (default)
x-dead-letter-routing-key: orders.dead.queue
4️⃣ API Documentation
Example APIs:
Method	Endpoint	Description
POST	/api/orders	Create a new order
GET	/api/orders/{id}	Get order by ID
PATCH	/api/orders/{id}	Update order status
Documentation:
Swagger UI available at: http://localhost:8080/swagger-ui.html
Postman collection included in the repository (optional).
5️⃣ Instructions
Running the application locally:
✅ Requirements:
Java 17+
Maven
MongoDB
RabbitMQ
✅ Steps:
bash
Copy
Edit
# Clone the repository
git clone <repo_url>
cd <repo_name>

# Start MongoDB and RabbitMQ locally
docker-compose up -d

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
✅ Environment Variables:
Create a .env file or configure in application.properties:

ini
Copy
Edit
spring.data.mongodb.uri=mongodb://localhost:27017/orders-db
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
🚀 Features
Handles order creation and processing.
Updates status to FAILED if message processing throws exceptions.
Supports Dead Letter Queue (DLQ) for failed messages.
📝 Notes
Make sure RabbitMQ queues are created before running.
DLQ listener can be implemented for advanced failure handling.
Scaling is supported by adjusting consumer concurrency.
