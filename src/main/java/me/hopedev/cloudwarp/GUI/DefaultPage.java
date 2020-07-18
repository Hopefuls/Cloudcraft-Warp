package me.hopedev.cloudwarp.GUI;

import me.hopedev.cloudwarp.utils.InventoryTools;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DefaultPage {


    public static void open(Player p, int page) {


        // Here we create our named help "inventory
        // p.sendMessage(GUIManager.pagedInventory.size() + "");
        Inventory inv = GUIManager.pagedInventory.get(page);
        for (int i = 3 * 9; i < 5 * 9; i++) {
            inv.setItem(i, InventoryTools.getPane(DyeColor.BLACK));
        }
        if (p.hasPermission("cloudwarp.admin")) {

            inv.setItem(5 * 9 - 5, InventoryTools.createGUIItem(Material.EMERALD_BLOCK, "§aNeuer Warp", "§c", "§bErstelle einen neuen Warp", "§c", "§6§oWichtig: Du wirst im Chat den Namen des warps und", "§6§oden Titelnamen des warps schreiben müssen."));
            inv.setItem(5 * 9 - 4, InventoryTools.createGUIItem(Material.BARRIER, "§4Administratorfunktionen", "§c", "§dShift-klick §7- §6Lösche einen Warp (Ohne Bestätigung)"));
        } else {
            inv.setItem(5 * 9 - 5, InventoryTools.getPane(DyeColor.BLACK));
            inv.setItem(5 * 9 - 4, InventoryTools.getPane(DyeColor.BLACK));
        }

        inv.setItem(5 * 9 - 1, InventoryTools.createGUIItem(Material.SIGN, "§cNächste Seite"));
        inv.setItem(5 * 9 - 9, InventoryTools.createGUIItem(Material.SIGN, "§cVorherige Seite"));
        inv.setItem(5 * 9 - 9, InventoryTools.createGUIItem(Material.SIGN, "§cVorherige Seite"));


        p.openInventory(inv);
        warpDatabaseManager.activeSessions.add(p);
    }


    //Here opens the inventory

}
