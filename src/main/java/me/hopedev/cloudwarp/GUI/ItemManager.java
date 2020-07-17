package me.hopedev.cloudwarp.GUI;

import org.bukkit.inventory.ItemStack;

public class ItemManager extends ItemStack {
    boolean warpItem = true;

    public boolean isWarpItem() {
        return warpItem;
    }

    public void setWarpItem(boolean warpItem) {
        warpItem = true;
    }
}
