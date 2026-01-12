package com.example.bench.EtudeCas.service;

import com.example.bench.EtudeCas.entity.Item;
import com.example.bench.EtudeCas.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> findByCategory(Long categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }
}

