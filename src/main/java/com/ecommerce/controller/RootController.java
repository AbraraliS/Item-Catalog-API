package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new LinkedHashMap<>();
        
        response.put("application", "Item Catalog API");
        response.put("description", "A simple REST API for managing a collection of items (products, movies, etc.)");
        response.put("version", "1.0.0");
        response.put("status", "Live on Render");
        
        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("POST /api/items", "Create a new item");
        endpoints.put("GET /api/items/{id}", "Get item by ID");
        endpoints.put("GET /api/items", "Get all items");
        endpoints.put("PUT /api/items/{id}", "Update an item");
        endpoints.put("DELETE /api/items/{id}", "Delete an item");
        endpoints.put("GET /api/items/health", "Health check");
        endpoints.put("GET /api/items/stats", "Catalog statistics");
        
        response.put("endpoints", endpoints);
        response.put("documentation", "https://github.com/abraralis/JavaTaskApp");
        response.put("message", "API is running. Use the endpoints above to interact with the catalog.");
        
        return ResponseEntity.ok(response);
    }
}
