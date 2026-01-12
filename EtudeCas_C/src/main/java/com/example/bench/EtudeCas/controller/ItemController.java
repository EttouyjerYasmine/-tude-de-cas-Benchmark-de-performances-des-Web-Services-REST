package com.example.bench.EtudeCas.controller;

import com.example.bench.EtudeCas.entity.Item;
import com.example.bench.EtudeCas.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Item> getItemsByCategory(@PathVariable Long categoryId) {
        return itemService.findByCategory(categoryId);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        return itemService.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.delete(id);
    }
}
