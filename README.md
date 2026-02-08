# Item Catalog API

A production-ready RESTful API for managing item catalogs, built with Spring Boot. Perfect for eCommerce platforms, inventory management, or media catalogs.

## Features

- ✅ RESTful API with 8 endpoints
- ✅ In-memory data storage with thread-safe operations
- ✅ Comprehensive input validation
- ✅ Global exception handling
- ✅ Production-ready configuration
- ✅ Docker support
- ✅ Health check endpoint

## Technologies & Dependencies

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17+ | Programming language |
| Spring Boot | 3.2.2 | Application framework |
| Spring Web | 3.2.2 | REST API support |
| Jakarta Validation | 3.0.2 | Input validation |
| Lombok | edge-SNAPSHOT | Reduce boilerplate code |
| Maven | 3.9.6 | Build tool |
| SLF4J/Logback | (included) | Logging framework |

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker (optional, for containerized deployment)

Verify installation:
```bash
java -version
mvn -version
```

## Setup

Build the project:
```bash
mvn clean install
```

## Running the Application

Using Maven:
```bash
mvn spring-boot:run
```

Using JAR file:
```bash
mvn clean package
java -jar target/item-catalog-api-1.0.0.jar
```

The application runs on **http://localhost:8080**

## API Endpoints

Base URL: `http://localhost:8080/api/items`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/items/health` | Health check |
| GET | `/api/items/stats` | Catalog statistics |
| POST | `/api/items` | Create item |
| GET | `/api/items` | Get all items |
| GET | `/api/items?category={category}` | Get items by category |
| GET | `/api/items/{id}` | Get item by ID |
| PUT | `/api/items/{id}` | Update item |
| DELETE | `/api/items/{id}` | Delete item |

## Request Examples

### Create Item
```bash
POST http://localhost:8080/api/items
Content-Type: application/json

{
  "name": "Samsung Galaxy S24",
  "description": "Premium smartphone with advanced features",
  "category": "Electronics",
  "price": 1299.99,
  "stockQuantity": 75,
  "rating": 4.7
}
```

### Get Item by ID
```bash
GET http://localhost:8080/api/items/1
```

### Update Item
```bash
PUT http://localhost:8080/api/items/1
Content-Type: application/json

{
  "name": "Updated Name",
  "description": "Updated description here",
  "category": "Electronics",
  "price": 1199.99,
  "stockQuantity": 50,
  "rating": 4.8
}
```

### Delete Item
```bash
DELETE http://localhost:8080/api/items/1
```

## Validation Rules

| Field | Constraints |
|-------|-------------|
| name | 2-100 characters, required |
| description | 10-500 characters, required |
| category | 2-50 characters, required |
| price | > 0, required |
| stockQuantity | >= 0, required |
| imageUrl | Valid URL format, optional |
| rating | 0.0 - 5.0, optional |

## Error Responses

HTTP Status Codes:
- `200 OK` - Success
- `201 Created` - Item created
- `400 Bad Request` - Validation error
- `404 Not Found` - Item not found
- `500 Internal Server Error` - Server error

Error Response Format:
```json
{
  "timestamp": "2026-02-08T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Item with ID 999 not found in the catalog",
  "path": "/api/items/999"
}
```

## Testing

Using cURL:
```bash
# Health check
curl http://localhost:8080/api/items/health

# Get all items
curl http://localhost:8080/api/items

# Create item
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Item","description":"Test description here","category":"Test","price":99.99,"stockQuantity":10}'
```

Using browser (GET requests only):
- http://localhost:8080/api/items
- http://localhost:8080/api/items/health

## Production Features

### Security
- No stack traces exposed in production mode
- Validation errors don't leak sensitive information
- Exception messages sanitized for public consumption
- Docker container runs as non-root user

### Performance
- Response compression enabled
- Optimized JSON serialization
- Thread-safe in-memory data store
- Efficient ArrayList-based storage

### Monitoring
- Health check endpoint at `/api/items/health`
- Statistics endpoint at `/api/items/stats`
- Structured logging with SLF4J
- Application startup banner with configuration details

### Error Handling
- Global exception handler for consistent responses
- Proper HTTP status codes (200, 201, 400, 404, 500)
- Validation errors with detailed field-level messages
- Graceful handling of unexpected errors

## Architecture

```
┌─────────────────────────────────────────┐
│         ItemController (REST API)        │
│  - POST /api/items                      │
│  - GET /api/items/{id}                  │
│  - PUT /api/items/{id}                  │
│  - DELETE /api/items/{id}               │
│  - GET /api/items?category=...          │
│  - GET /api/items/health                │
│  - GET /api/items/stats                 │
└────────────┬────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────┐
│      ItemService (Business Logic)       │
│  - createItem()                         │
│  - getAllItems()                        │
│  - getItemById()                        │
│  - updateItem()                         │
│  - deleteItem()                         │
│  - getItemsByCategory()                 │
└────────────┬────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────┐
│     ArrayList<Item> (Data Store)        │
│  - Thread-safe with synchronized        │
│  - AtomicLong for ID generation         │
│  - In-memory persistence                │
└─────────────────────────────────────────┘
```

## Project Structure

```
JavaTaskApp/
├── src/main/java/com/ecommerce/
│   ├── ItemCatalogApplication.java      # Main application
│   ├── controller/
│   │   └── ItemController.java          # REST endpoints
│   ├── service/
│   │   └── ItemService.java             # Business logic
│   ├── model/
│   │   └── Item.java                    # Entity model
│   └── exception/
│       ├── ItemNotFoundException.java   # Custom exception
│       ├── ErrorResponse.java           # Error response model
│       └── GlobalExceptionHandler.java  # Exception handler
├── src/main/resources/
│   ├── application.properties           # Default config
│   ├── application-production.properties # Production config
│   └── static/
│       └── favicon.svg                  # Custom favicon
├── Dockerfile                           # Container definition
├── docker-compose.yml                   # Docker Compose config
├── pom.xml                              # Maven configuration
└── README.md                            # Documentation
```

## License

This project is created for educational and commercial purposes.

## Deployment

### Docker Deployment (Recommended)

Build and run with Docker:
```bash
# Build Docker image
docker build -t item-catalog-api .

# Run container
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=production item-catalog-api

# Or use Docker Compose
docker-compose up -d
```

Access the API at: http://localhost:8080

### Cloud Platform Deployment

#### Railway.app
1. Create account at [railway.app](https://railway.app/)
2. Click "New Project" → "Deploy from GitHub"
3. Select your repository
4. Set environment variable: `SPRING_PROFILES_ACTIVE=production`
5. Railway auto-builds and deploys

#### Heroku
```bash
# Login to Heroku
heroku login

# Create app
heroku create your-app-name

# Set config
heroku config:set SPRING_PROFILES_ACTIVE=production

# Deploy
git push heroku main
```

#### AWS/Google Cloud/Azure
Deploy the Docker container to:
- AWS Elastic Beanstalk / ECS
- Google Cloud Run
- Azure Container Instances

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SPRING_PROFILES_ACTIVE` | default | Set to `production` for production |
| `PORT` | 8080 | Server port (auto-set by some platforms) |

### Production Checklist

- [x] Stack traces disabled in error responses
- [x] Debug logging disabled
- [x] Input validation on all endpoints
- [x] Global exception handling
- [x] Non-root user in Docker container
- [x] Health check endpoint configured
- [x] Compression enabled for responses
- [x] Security headers configured
