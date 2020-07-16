package me.hopedev.cloudwarp;

import me.hopedev.cloudwarp.Commands.cmd_warp;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    // Plugin default shit
    static Plugin plugin;


    // Default onEnable
    @Override
    public void onEnable() {
        this.plugin = this;
        this.getCommand("warp").setExecutor(new cmd_warp());

        System.out.println("CloudWarp von HopeDev aktiviert!");
    }

    //Default onDisable
    @Override
    public void onDisable() {
        System.out.println("CloudWarp von HopeDev deaktiviert!");
    }

    //Default getPlugin()
    public Plugin getPlugin() {
        return plugin;
    }
}
