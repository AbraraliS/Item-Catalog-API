# Item Catalog API

A simple Java backend RESTful API for managing a collection of items (e.g., eCommerce products or movie catalogs). Built with Spring Boot, this application provides in-memory storage for item data with full CRUD operations.

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+** for dependency management

Verify installation:
```bash
java -version
mvn -version
```

## Setup and Run Locally

1. **Clone or download the project**
   ```bash
   cd JavaTaskApp
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR directly:
   ```bash
   mvn clean package
   java -jar target/item-catalog-api-1.0.0.jar
   ```

4. **Access the API**
   
   The application runs on `http://localhost:8080`

## API Endpoints

### Create Item
**POST** `/api/items`

Creates a new item in the catalog.

**Request Body:**
```json
{
  "name": "Product Name",
  "description": "Product description (10-500 characters)",
  "category": "Electronics",
  "price": 99.99,
  "stockQuantity": 50,
  "imageUrl": "https://example.com/image.jpg",
  "rating": 4.5
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "Product Name",
  "description": "Product description (10-500 characters)",
  "category": "Electronics",
  "price": 99.99,
  "stockQuantity": 50,
  "imageUrl": "https://example.com/image.jpg",
  "rating": 4.5,
  "createdAt": "2026-02-08T10:30:00",
  "updatedAt": "2026-02-08T10:30:00"
}
```

### Get Item by ID
**GET** `/api/items/{id}`

Retrieves a single item by its ID.

**Example:**
```bash
GET http://localhost:8080/api/items/1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Product Name",
  "description": "Product description (10-500 characters)",
  "category": "Electronics",
  "price": 99.99,
  "stockQuantity": 50,
  "imageUrl": "https://example.com/image.jpg",
  "rating": 4.5,
  "createdAt": "2026-02-08T10:30:00",
  "updatedAt": "2026-02-08T10:30:00"
}
```

**Response (404 Not Found):**
```json
{
  "timestamp": "2026-02-08T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Item with ID 1 not found in the catalog",
  "path": "/api/items/1"
}
```

### Additional Endpoints

The API also supports the following operations:
- **GET** `/api/items` - Get all items (with optional `?category=` filter)
- **PUT** `/api/items/{id}` - Update an existing item
- **DELETE** `/api/items/{id}` - Delete an item
- **GET** `/api/items/health` - Health check endpoint
- **GET** `/api/items/stats` - Catalog statistics

## Validation Rules

All item fields must meet the following constraints:

| Field | Required | Constraints |
|-------|----------|-------------|
| name | Yes | 2-100 characters |
| description | Yes | 10-500 characters |
| category | Yes | 2-50 characters |
| price | Yes | Greater than 0 |
| stockQuantity | Yes | 0 or greater |
| imageUrl | No | Valid URL format |
| rating | No | 0.0 - 5.0 |

## Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Java | 17+ | Programming language |
| Spring Boot | 3.2.2 | Application framework |
| Spring Web | Included | REST API support |
| Jakarta Validation | Included | Input validation |
| Lombok | edge-SNAPSHOT | Reduce boilerplate code |
| Maven | 3.9.6 | Build automation |

All dependencies are managed in `pom.xml` and automatically downloaded during the build process.

## Production Deployment

### Deploy to Railway

1. Create account at [railway.app](https://railway.app/)
2. Click **New Project** → **Deploy from GitHub**
3. Select your repository
4. Railway automatically detects Maven and builds the application
5. Access your app at the provided URL

**Environment Variables:**
```
PORT=8080 (auto-configured by Railway)
```

### Deploy to Heroku

```bash
# Install Heroku CLI and login
heroku login

# Create new app
heroku create your-app-name

# Deploy
git push heroku main

# Open your app
heroku open
```

**Environment Variables:**
```bash
heroku config:set JAVA_TOOL_OPTIONS="-Xmx300m"
```

### Deploy with Docker

The application includes a `Dockerfile` for containerized deployment.

```bash
# Build Docker image
docker build -t item-catalog-api .

# Run container
docker run -p 8080:8080 item-catalog-api
```

Deploy to cloud platforms:
- **AWS**: Elastic Beanstalk or ECS
- **Google Cloud**: Cloud Run
- **Azure**: Container Instances

**Production Configuration:**

The application uses `application.properties` for configuration. Key settings:
- `server.port=${PORT:8080}` - Dynamic port binding for cloud platforms
- `server.error.include-stacktrace=never` - Security: no stack traces in production
- In-memory ArrayList storage (consider database for production scale)

## Testing

Test endpoints using cURL:

```bash
# Create item
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "description": "This is a test product",
    "category": "Testing",
    "price": 99.99,
    "stockQuantity": 10
  }'

# Get item by ID
curl http://localhost:8080/api/items/1

# Health check
curl http://localhost:8080/api/items/health
```

Or open in browser:
- http://localhost:8080/api/items
- http://localhost:8080/api/items/health

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
│   │   └── Item.java                    # Data model
│   └── exception/
│       ├── ItemNotFoundException.java   # Custom exception
│       ├── ErrorResponse.java           # Error response model
│       └── GlobalExceptionHandler.java  # Exception handling
├── src/main/resources/
│   └── application.properties           # Configuration
├── Dockerfile                           # Container definition
└── pom.xml                              # Maven dependencies
```

## License

This project is available for educational and commercial use.
