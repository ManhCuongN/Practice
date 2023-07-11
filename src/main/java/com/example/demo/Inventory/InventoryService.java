package com.example.demo.Inventory;

import com.example.demo.Discount.DiscountDTO;
import com.example.demo.Discount.DiscountEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryEntity createInventory(InventoryDTO inventoryDTO) {
        InventoryEntity inventory = new InventoryEntity();
        inventory.setQuantity(inventoryDTO.getQuantity());
        return inventoryRepository.save(inventory);
    }

    public InventoryEntity updateInventory(UUID idInventory, InventoryDTO inventoryDTO) {
        InventoryEntity existingInventory = inventoryRepository.findById(idInventory).orElse(null);
        if (existingInventory == null) {
            return null;
        }
        existingInventory.setQuantity(inventoryDTO.getQuantity());

        return inventoryRepository.save(existingInventory);
    }


}
