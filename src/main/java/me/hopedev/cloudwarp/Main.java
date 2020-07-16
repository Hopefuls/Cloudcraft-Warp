package me.hopedev.cloudwarp;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    // Plugin default shit
    static Plugin plugin;


    // Default onEnable
    @Override
    public void onEnable() {
        this.plugin = this;
        System.out.println("Plugin enabled");
    }

    //Default onDisable
    @Override
    public void onDisable() {
        System.out.println("Plugin disabled");
    }

    //Default getPlugin()
    public Plugin getPlugin() {
        return plugin;
    }
}
