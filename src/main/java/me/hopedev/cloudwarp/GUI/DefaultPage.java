package me.hopedev.cloudwarp.GUI;

import me.hopedev.cloudwarp.utils.InventoryTools;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DefaultPage {


    public static void open(Player p, int page) {


        // Here we create our named help "inventory
        p.sendMessage(GUIManager.pagedInventory.size() + "");
        Inventory inv = GUIManager.pagedInventory.get(page);
        if (p.hasPermission("cloudwarp.admin")) {
            inv.setItem(5 * 9 - 5, InventoryTools.createGUIItem(Material.EMERALD_BLOCK, "§aErstelle einen neuen Warp bei deiner Position", "§c", "§6§oWichtig: Du wirst im Chat den Namen des warps und", "§6§oden Titelnamen des warps schreiben müssen."));
            inv.setItem(5 * 9 - 4, InventoryTools.createGUIItem(Material.BARRIER, "§4Administratorfunktionen", "§c", "§dShift-klick §7- §6Lösche einen Warp (Ohne Bestätigung)"));
        }


        p.openInventory(inv);
    }


    //Here opens the inventory

}
