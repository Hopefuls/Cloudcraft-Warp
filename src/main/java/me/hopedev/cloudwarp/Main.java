package me.hopedev.cloudwarp;

import me.hopedev.cloudwarp.Commands.cmd_warp;
import me.hopedev.cloudwarp.GUI.GUIActionListener;
import me.hopedev.cloudwarp.handlers.InputSessionHandler;
import me.hopedev.cloudwarp.handlers.Sessionhandler;
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
        System.out.println("[CLOUDWARP] CloudWarp von HopeDev deaktiviert!");
    }

    // Default onEnable
    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("warp").setExecutor(new cmd_warp());
        System.out.println("[CLOUDWARP] Warps werden reloaded und geparst");
        warpDatabaseManager.onPluginEnable();
        System.out.println("[CLOUDWARP] Warps wurden geparst!");

        getServer().getPluginManager().registerEvents(new GUIActionListener(), this);
        getServer().getPluginManager().registerEvents(new InputSessionHandler(), this);
        getServer().getPluginManager().registerEvents(new Sessionhandler(), this);
        System.out.println("[CLOUDWARP] CloudWarp von HopeDev aktiviert!");
    }
}
