package me.hopedev.cloudwarp.GUI;

import me.hopedev.cloudwarp.utils.InventoryTools;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Set;

public class GUIManager {
    // Needed for the GUIActionListener.java to get the warps and teleport to them
    public static HashMap<Integer /*Page*/, HashMap<Integer /*Slot*/, String /*Warpname*/>> WarpNameGetter = new HashMap<>();

    // Get the whole inventory with their warp names
    public static HashMap<Integer /*Page Number*/, Inventory> pagedInventory = new HashMap<>();

    public static void updateWarpGUI() {
        int page = 1;

        FileConfiguration database = new warpDatabaseManager(null).getConfig();

        // System.out.println(database.getStringList("warps"));
        try {
            database.getConfigurationSection("warps").getKeys(false);
        } catch (NullPointerException ex) {

            System.out.println("Configuration Empty");
            HashMap<Integer, String> WarpNameSlot = new HashMap<>();
            Inventory inv = Bukkit.getServer().createInventory(new GUIHolder(page), 5 * 9, "§bCloud§9Warp §7| §9" + page + "/1");
            for (int i = 3 * 9; i < 5 * 9; i++) {
                inv.setItem(i, InventoryTools.getPane(DyeColor.BLACK));
            }
            inv.setItem(5 * 9 - 1, InventoryTools.createGUIItem(Material.SIGN, "§cNächste Seite"));
            inv.setItem(5 * 9 - 9, InventoryTools.createGUIItem(Material.SIGN, "§cVorherige Seite"));
            inv.setItem(5 * 9 - 5, InventoryTools.createGUIItem(Material.SIGN, "§cNächste Seite"));
            pagedInventory.put(page, inv);
            WarpNameGetter.put(page, WarpNameSlot);
            return;
        }

        // System.out.println(database.getConfigurationSection("warps").getKeys(false));

        int count = 0;
        int predictedPageCount = 1;
        Set<String> Warps = database.getConfigurationSection("warps").getKeys(false);

        for (String placeholder : Warps) {
            count++;
            if (count == 27) {
                count = 0;
                predictedPageCount++;
            }
        }

        // Contains the slot Number and the Warp Name
        HashMap<Integer, String> WarpNameSlot = new HashMap<>();
        Inventory inv = Bukkit.getServer().createInventory(new GUIHolder(page), 5 * 9, "§bCloud§9Warp §7| §9" + page + "/" + predictedPageCount);

        int slot = 0;

        for (String Warpname : Warps) {

            // Glasspanes at the bottom
            for (int i = 3 * 9; i < 5 * 9; i++) {
                inv.setItem(i, InventoryTools.getPane(DyeColor.BLACK));
            }

            String warptitle = database.getString("warps." + Warpname + ".name");

            OfflinePlayer player = database.getOfflinePlayer("warps." + Warpname + ".player");
            inv.setItem(slot, InventoryTools.createGUIItem(Material.PAPER, warptitle.replaceAll("&", "§"), "§eWarpname: §6" + Warpname, "§eErstellt von: §6" + player.getName(), "§c", "§c", "§a§oKlicke zum Teleportieren"));
            WarpNameSlot.put(slot, Warpname);
            // System.out.println("added " + Warpname + " on page " + page);
            slot++;

            if (slot == 27) {

                int pgmath = page + 1;
                inv.setItem(5 * 9 - 1, InventoryTools.createGUIItem(Material.SIGN, "§cNächste Seite"));
                inv.setItem(5 * 9 - 9, InventoryTools.createGUIItem(Material.SIGN, "§cVorherige Seite"));
                inv.setItem(5 * 9 - 5, InventoryTools.createGUIItem(Material.SIGN, "§cNächste Seite"));

                pagedInventory.put(page, inv);
                WarpNameGetter.put(page, WarpNameSlot);
                // System.out.println("27 reached, resetting ");

                slot = 0;

                page++;
                inv = Bukkit.getServer().createInventory(new GUIHolder(page), 5 * 9, "§bCloud§9Warp §7| §9" + page + "/" + predictedPageCount);
                // System.out.println("Page is " + page);

                // System.out.println("Page is now " + page);

            }


        }
        for (int i = 3 * 9; i < 5 * 9; i++) {
            inv.setItem(i, InventoryTools.getPane(DyeColor.BLACK));
        }
        inv.setItem(5 * 9 - 1, InventoryTools.createGUIItem(Material.SIGN, "§cNächste Seite"));
        inv.setItem(5 * 9 - 9, InventoryTools.createGUIItem(Material.SIGN, "§cVorherige Seite"));
        inv.setItem(5 * 9 - 9, InventoryTools.createGUIItem(Material.SIGN, "§cVorherige Seite"));

        pagedInventory.put(page, inv);
        WarpNameGetter.put(page, WarpNameSlot);


    }


}
