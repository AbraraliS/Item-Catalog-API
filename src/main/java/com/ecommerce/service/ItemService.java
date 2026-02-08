package com.ecommerce.service;

import com.ecommerce.exception.ItemNotFoundException;
import com.ecommerce.model.Item;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {

    private final List<Item> itemStore = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public ItemService() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        createItem(Item.builder()
            .name("Sample Product")
            .description("This is a sample product for demonstration purposes")
            .category("Electronics")
            .price(99.99)
            .stockQuantity(50)
            .rating(4.5)
            .build());
    }

    public Item createItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        item.setId(idCounter.incrementAndGet());
        LocalDateTime now = LocalDateTime.now();
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        itemStore.add(item);

        return item;
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(itemStore);
    }

    public Optional<Item> getItemById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Item ID must be a positive number");
        }
        return itemStore.stream()
            .filter(item -> item.getId().equals(id))
            .findFirst();
    }

    public Item getItemByIdOrThrow(Long id) {
        return getItemById(id)
            .orElseThrow(() -> new ItemNotFoundException("Item with ID " + id + " not found in the catalog"));
    }

    public Item updateItem(Long id, Item updatedItem) {
        if (updatedItem == null) {
            throw new IllegalArgumentException("Updated item cannot be null");
        }

        Item existingItem = getItemByIdOrThrow(id);
        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setStockQuantity(updatedItem.getStockQuantity());
        existingItem.setImageUrl(updatedItem.getImageUrl());
        existingItem.setRating(updatedItem.getRating());
        existingItem.setUpdatedAt(LocalDateTime.now());

        return existingItem;
    }

    public boolean deleteItem(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Item ID must be a positive number");
        }
        return itemStore.removeIf(item -> item.getId().equals(id));
    }

    public List<Item> getItemsByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return itemStore.stream()
            .filter(item -> item.getCategory().equalsIgnoreCase(category.trim()))
            .toList();
    }

    public long getItemCount() {
        return itemStore.size();
    }
}
