package com.ecommerce.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long id;

    @NotBlank(message = "Item name is required and cannot be empty")
    @Size(min = 2, max = 100, message = "Item name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required and cannot be empty")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotBlank(message = "Category is required and cannot be empty")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String category;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Price cannot exceed 999999.99")
    private Double price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @Pattern(regexp = "^(https?://.*|/.*)?$", message = "Image URL must be a valid HTTP/HTTPS URL or relative path")
    private String imageUrl;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double rating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
