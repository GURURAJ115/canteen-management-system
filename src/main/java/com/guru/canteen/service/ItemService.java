package com.guru.canteen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.canteen.entity.Item;
import com.guru.canteen.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Fetch all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Save or update an item
    public Item saveOrUpdateItem(Item item) {
        return itemRepository.save(item);
    }

    // Find item by ID
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // Delete an item
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }


    // Update an existing item in the menu
    public Item updateItem(Long itemId, Item updatedItem) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        return itemRepository.save(item);
    }

    // Add a new menu item
    public Item addMenuItem(Item item) {
        return itemRepository.save(item);
    }

    // Get all items for a specific vendor
    public List<Item> getItemsByVendor(Long vendorId) {
        return itemRepository.findByVendorId(vendorId);
    }
}
