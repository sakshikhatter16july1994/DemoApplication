package com.example.DemoApplication.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.DemoApplication.model.Item;


@Service
public class ItemService {

  private final List<Item> items;

  public ItemService() {
    // Hardcoded initial data
    items = List.of(
        new Item(1L, "Item1", "This is item 1"),
        new Item(2L, "Item2", "This is item 2"),
        new Item(3L, "Item3", "This is item 3")
    );
  }

  // Retrieve all items
  public List<Item> getAllItems() {
    return items;
  }

  // Get item by ID
  public Optional<Item> findById(Long id) {
    return items.stream()
        .filter(item -> item.getId().equals(id))
        .findFirst();
  }

  // Save a new item (add to the list)
  public Item save(Item item) {
    items.add(item); // In-memory addition
    return item;
  }

  // Delete item by ID
  public boolean deleteById(Long id) {
    return items.removeIf(item -> item.getId().equals(id));
  }
}
