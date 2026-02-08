# Item Catalog API

A simple Java backend RESTful API for managing a collection of items (e.g., eCommerce products or movie catalogs). Built with Spring Boot, this application provides in-memory storage with full CRUD operations.

**🌐 Live Deployment:** [https://item-catalog-api-jxqg.onrender.com](https://item-catalog-api-jxqg.onrender.com)

> The API is currently deployed and running on Render. Visit the base URL to see API information and available endpoints.

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+** for dependency management

## Local Development

### Setup and Run

1. **Clone the repository**
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

4. **Access the API**
   ```
   http://localhost:8080
   ```

## API Endpoints

**Base URL (Local):** `http://localhost:8080`  
**Base URL (Production):** `https://item-catalog-api-jxqg.onrender.com`

### Core Endpoints

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| GET | `/` | API information | 200 |
| POST | `/api/items` | Create a new item | 201, 400 |
| GET | `/api/items/{id}` | Get item by ID | 200, 404 |
| GET | `/api/items` | Get all items | 200 |
| PUT | `/api/items/{id}` | Update an item | 200, 400, 404 |
| DELETE | `/api/items/{id}` | Delete an item | 200, 404 |
| GET | `/api/items/health` | Health check | 200 |
| GET | `/api/items/stats` | Catalog statistics | 200 |

### Example Requests

#### 1. Get API Information (GET /)
```bash
curl https://item-catalog-api-jxqg.onrender.com/
```

**Response (200 OK):**
```json
{
  "application": "Item Catalog API",
  "description": "A simple REST API for managing a collection of items",
  "version": "1.0.0",
  "status": "Live on Render",
  "endpoints": {
    "POST /api/items": "Create a new item",
    "GET /api/items/{id}": "Get item by ID",
    "GET /api/items": "Get all items",
    "PUT /api/items/{id}": "Update an item",
    "DELETE /api/items/{id}": "Delete an item",
    "GET /api/items/health": "Health check",
    "GET /api/items/stats": "Catalog statistics"
  },
  "message": "API is running. Use the endpoints above to interact with the catalog."
}
```

#### 2. Create Item (POST /api/items)
```bash
curl -X POST https://item-catalog-api-jxqg.onrender.com/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Product Name",
    "description": "Product description (10-500 characters)",
    "category": "Electronics",
    "price": 99.99,
    "stockQuantity": 50,
    "rating": 4.5
  }'
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
  "rating": 4.5,
  "createdAt": "2026-02-08T10:30:00",
  "updatedAt": "2026-02-08T10:30:00"
}
```

#### 3. Get Item by ID (GET /api/items/{id})
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items/1
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
  "rating": 4.5,
  "createdAt": "2026-02-08T10:30:00",
  "updatedAt": "2026-02-08T10:30:00"
}
```

#### 4. Get All Items (GET /api/items)
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Sample Product",
    "description": "This is a sample product for demonstration purposes",
    "category": "Electronics",
    "price": 99.99,
    "stockQuantity": 50,
    "rating": 4.5,
    "createdAt": "2026-02-08T09:00:00",
    "updatedAt": "2026-02-08T09:00:00"
  }
]
```

#### 5. Update Item (PUT /api/items/{id})
```bash
curl -X PUT https://item-catalog-api-jxqg.onrender.com/api/items/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Product",
    "description": "Updated description with more details here",
    "category": "Electronics",
    "price": 149.99,
    "stockQuantity": 75,
    "rating": 4.8
  }'
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Updated Product",
  "description": "Updated description with more details here",
  "category": "Electronics",
  "price": 149.99,
  "stockQuantity": 75,
  "rating": 4.8,
  "createdAt": "2026-02-08T09:00:00",
  "updatedAt": "2026-02-08T10:45:00"
}
```

#### 6. Delete Item (DELETE /api/items/{id})
```bash
curl -X DELETE https://item-catalog-api-jxqg.onrender.com/api/items/1
```

**Response (200 OK):**
```json
{
  "message": "Item with ID 1 deleted successfully",
  "deleted": true
}
```

#### 7. Health Check (GET /api/items/health)
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items/health
```

**Response (200 OK):**
```json
{
  "status": "UP",
  "message": "Item Catalog API is running",
  "timestamp": "2026-02-08T10:30:00"
}
```

#### 8. Catalog Statistics (GET /api/items/stats)
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items/stats
```

**Response (200 OK):**
```json
{
  "totalItems": 1,
  "totalCategories": 1,
  "message": "Catalog statistics retrieved successfully"
}
```

## Error Responses

### Validation Error (400 Bad Request)
```json
{
  "timestamp": "2026-02-08T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Input validation failed. Please check the errors below.",
  "path": "/api/items",
  "errors": [
    "name: Item name must be between 2 and 100 characters",
    "price: Price must be greater than 0",
    "description: Description must be between 10 and 500 characters"
  ]
}
```

### Item Not Found (404 Not Found)
```json
{
  "timestamp": "2026-02-08T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Item with ID 999 not found in the catalog",
  "path": "/api/items/999"
}
```

### Internal Server Error (500)
```json
{
  "timestamp": "2026-02-08T10:30:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred",
  "path": "/api/items"
}
```

## Validation Rules

| Field | Required | Constraints |
|-------|----------|-------------|
| name | Yes | 2-100 characters |
| description | Yes | 10-500 characters |
| category | Yes | 2-50 characters |
| price | Yes | Greater than 0 |
| stockQuantity | Yes | 0 or greater |
| rating | No | 0.0 - 5.0 |

## Deploy to Render

### Quick Deploy (5 Minutes)

1. **Push your code to GitHub**
   ```bash
   git init
   git add .
   git commit -m "Deploy to Render"
   git push origin main
   ```

2. **Create Render account**
   - Visit [render.com](https://render.com)
   - Sign up
   - Connect your GitHub account

3. **Deploy your application**

   **Option A: Blueprint**
   - Click **New** → **Blueprint**
   - Select your repository
   - Click **Apply**
   - Render automatically uses `render.yaml` configuration

   **Option B: Manual Setup**
   - Click **New** → **Web Service**
   - Connect your GitHub repository
   - Configure:
     - **Name**: `item-catalog-api`
     - **Environment**: `Docker`
     - **Branch**: `main`
     - **Dockerfile Path**: `./Dockerfile`

4. **Set Environment Variables** 
   ```
   PORT=8080
   JAVA_TOOL_OPTIONS=-Xmx512m -Xms256m
   ```

5. **Wait for deployment**
   - Build time: 3-5 minutes
   - Status will change from "Building" → "Live"

6. **Access your API**
   ```
   https://your-app-name.onrender.com
   ```

### Render Configuration

The application includes a `render.yaml` file that automatically configures:

```yaml
services:
  - type: web
    name: item-catalog-api
    env: docker
    dockerfilePath: ./Dockerfile
    envVars:
      - key: PORT
        value: 8080
      - key: JAVA_TOOL_OPTIONS
        value: -Xmx512m -Xms256m
    healthCheckPath: /api/items/health
```

### Build and Start Commands

Render uses Docker, so the build and start commands are defined in the `Dockerfile`:

- **Build**: Multi-stage Docker build (Maven + JRE)
- **Start**: `java -jar app.jar`
- **Health Check**: `/api/items/health`

### Environment Variables

| Variable | Value | Description |
|----------|-------|-------------|
| `PORT` | 8080 | Application port (auto-configured by Render) |
| `JAVA_TOOL_OPTIONS` | -Xmx512m -Xms256m | JVM memory settings for free tier |

### Testing Your Deployment

**Health Check**
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items/health
```

**Create an Item**
```bash
curl -X POST https://item-catalog-api-jxqg.onrender.com/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "description": "Testing Render deployment with this product",
    "category": "Testing",
    "price": 99.99,
    "stockQuantity": 10,
    "rating": 4.0
  }'
```

**Get All Items**
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items
```

**Get Catalog Statistics**
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items/stats
```

### Troubleshooting

**Build Fails**
- Verify `Dockerfile` is in the repository root
- Check build logs in Render dashboard
- Ensure Java 17 is specified in Docker image

**Application Won't Start**
- Check application logs in Render dashboard
- Verify environment variables are set
- Ensure health check endpoint returns 200 OK

**Free Tier Spin-Down**
- Free tier services sleep after 15 minutes of inactivity
- First request after sleep takes 30-60 seconds
- Upgrade to Starter plan ($7/month) for always-on service

## Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Java | 17+ | Programming language |
| Spring Boot | 3.2.2 | Application framework |
| Spring Web | Included | REST API |
| Jakarta Validation | Included | Input validation |
| Lombok | edge-SNAPSHOT | Reduce boilerplate |
| Maven | 3.9.6 | Build tool |

All dependencies are managed in `pom.xml`.

## Project Structure

```
JavaTaskApp/
├── src/
│   └── main/
│       ├── java/com/ecommerce/
│       │   ├── ItemCatalogApplication.java
│       │   ├── controller/ItemController.java
│       │   ├── service/ItemService.java
│       │   ├── model/Item.java
│       │   └── exception/
│       └── resources/
│           └── application.properties
├── Dockerfile                  # Docker configuration
├── render.yaml                 # Render deployment config
├── pom.xml                     # Maven dependencies
└── README.md                   # This file
```

## Testing

**Local Testing**
```bash
curl http://localhost:8080/api/items/health
```

**Production Testing**
```bash
curl https://item-catalog-api-jxqg.onrender.com/api/items/health
```

<!-- ## License

This project is available for educational and commercial use. -->
