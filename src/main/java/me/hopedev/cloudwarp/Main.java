package me.hopedev.cloudwarp;

import me.hopedev.cloudwarp.Commands.cmd_warp;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    // Plugin default shit
    static Plugin plugin;


    //Default getPlugin()
    public static Plugin getPlugin() {
        return plugin;
    }

    //Default onDisable
    @Override
    public void onDisable() {
        System.out.println("CloudWarp von HopeDev deaktiviert!");
    }

    // Default onEnable
    @Override
    public void onEnable() {
        this.plugin = this;
        this.getCommand("warp").setExecutor(new cmd_warp());
        System.out.println("Warps werden reloaded und geparst");
        warpDatabaseManager.onPluginEnable();
        System.out.println("Warps geparst!");
        System.out.println("CloudWarp von HopeDev aktiviert!");
    }
}
