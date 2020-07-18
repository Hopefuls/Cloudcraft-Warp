package me.hopedev.cloudwarp.handlers;

import me.hopedev.cloudwarp.GUI.GUIHolder;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Sessionhandler implements Listener {

    @EventHandler
    public void onServerLeave(PlayerQuitEvent event) {
        warpDatabaseManager.activeSessions.remove(event.getPlayer());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Player p = (Player) event.getPlayer();
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUIHolder) {
            warpDatabaseManager.activeSessions.remove(p);
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        Player p = (Player) event.getPlayer();
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUIHolder) {
            warpDatabaseManager.activeSessions.add(p);
        }

    }
}
