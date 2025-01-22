package com.example.DemoApplication.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DemoApplication.model.Item;
import com.example.DemoApplication.service.ItemService;


@RestController
@RequestMapping("/api/items")
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/hello")
  public String sayHello() {
    return "Hello, World!";
  }

  @GetMapping
  public ResponseEntity<List<Item>> getAllItems() {
    List<Item> items = itemService.getAllItems();
    return ResponseEntity.ok(items);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getItem(@PathVariable Long id) {
    Optional<Item> item = itemService.findById(id);
    return item.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Item> createItem(@RequestBody Item item) {
    Item createdItem = itemService.save(item);
    return ResponseEntity.ok(createdItem);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
    if (itemService.deleteById(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
