package me.hopedev.cloudwarp.GUI;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GUIHolder implements InventoryHolder {

    int page;

    public GUIHolder(int page) {
        this.page = page;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    public int getPage() {
        return page;
    }


}
