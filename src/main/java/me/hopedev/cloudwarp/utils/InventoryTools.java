package me.hopedev.cloudwarp.utils;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryTools {

    public static ItemStack createGUIItem(final Material material, String name, final String... lore) {

        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        // Set the name of the item
        meta.setDisplayName(name);
        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;

    }

    public static ItemStack getPane(DyeColor color) {
        final ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, color.getData());
        final ItemMeta meta = item.getItemMeta();
        // Set the name of the item
        meta.setDisplayName(" ");
        // Set the lore of the item

        item.setItemMeta(meta);
        return item;
    }

}
