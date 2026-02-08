package com.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * Main Spring Boot application for the Item Catalog RESTful API.
 */
@SpringBootApplication
public class ItemCatalogApplication {

    private static final Logger logger = LoggerFactory.getLogger(ItemCatalogApplication.class);

    public static void main(String[] args) {
        var context = SpringApplication.run(ItemCatalogApplication.class, args);
        Environment env = context.getEnvironment();
        String port = env.getProperty("server.port", "8080");
        String profile = env.getProperty("spring.profiles.active", "default");
        
        logger.info("=========================================");
        logger.info("Item Catalog API is running!");
        logger.info("Access the API at: http://localhost:{}", port);
        logger.info("Active Profile: {}", profile);
        logger.info("=========================================");
    }
}
