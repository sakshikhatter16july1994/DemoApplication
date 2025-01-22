package com.example.DemoApplication.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DemoApplication.service.RedisCacheService;

@RestController
@RequestMapping("/api/cache")
public class RedisCacheController {

  @Autowired
  private RedisCacheService redisCacheService;

  @PostMapping("/add")
  public String addItem(@RequestParam String key, @RequestParam String value, @RequestParam long ttl) {
    redisCacheService.addItem(key, value, ttl);
    return "Item added successfully!";
  }

  @GetMapping("/get")
  public String getItem(@RequestParam String key) {
    String value = redisCacheService.getItem(key);
    return value != null ? value : "Item not found!";
  }
}
