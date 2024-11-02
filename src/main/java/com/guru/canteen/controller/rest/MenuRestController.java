package com.guru.canteen.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guru.canteen.entity.Item;
import com.guru.canteen.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    @Autowired
    private ItemService itemService;

    // Get all menu items
    @GetMapping
    public List<Item> getAllMenuItems() {
        return itemService.getAllItems();
    }

    // Add a new menu item
    @PostMapping
    public Item addMenuItem(@RequestBody Item item) {
        return itemService.addMenuItem(item);
    }

    // Update menu item
    @PutMapping("/{itemId}")
    public Item updateMenuItem(@PathVariable Long itemId, @RequestBody Item updatedItem) {
        return itemService.updateItem(itemId, updatedItem);
    }

    // Delete a menu item
    @DeleteMapping("/{itemId}")
    public void deleteMenuItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
    }
}
