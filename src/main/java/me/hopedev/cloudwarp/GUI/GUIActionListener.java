package me.hopedev.cloudwarp.GUI;

import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class GUIActionListener implements Listener {

    @EventHandler
    private void inventoryClick(InventoryClickEvent event) {

        Player p = (Player) event.getWhoClicked();
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUIHolder) {
            event.setCancelled(true);
            int page = ((GUIHolder) event.getInventory().getHolder()).getPage();

            HashMap<Integer /*Page*/, HashMap<Integer /*Slot*/, String /*Warpname*/>> PagerHolder = GUIManager.WarpNameGetter;
            HashMap<Integer /*Slot*/, String /*Warpname*/> pagedInventories = PagerHolder.get(page);

            System.out.println(PagerHolder.get(page).size());
            System.out.println(pagedInventories.size());
            if (event.getSlot() == 5 * 9 - 1) {
                if (page + 1 > GUIManager.pagedInventory.size()) {
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 1L, 2L);

                } else {
                    DefaultPage.open((Player) event.getWhoClicked(), page + 1);
                    // event.getWhoClicked().openInventory(GUIManager.pagedInventory.get(page+1));
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_BASS, 1L, 1L);
                }

            } else if (event.getSlot() == 5 * 9 - 9) {

                if (page - 1 == 0) {
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 1L, 2L);
                } else {
                    DefaultPage.open((Player) event.getWhoClicked(), page - 1);
                    // event.getWhoClicked().openInventory(GUIManager.pagedInventory.get(page-1));
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_BASS, 1L, 1L);
                }
            } else if (event.getSlot() == 5 * 9 - 5) {
                if (event.getWhoClicked().hasPermission("cloudwarp.admin")) {

                }
            }

            new warpDatabaseManager(p).teleportToWarp(null, p, pagedInventories.get(event.getSlot()));
            p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1L, 1L);


        }
    }
}
