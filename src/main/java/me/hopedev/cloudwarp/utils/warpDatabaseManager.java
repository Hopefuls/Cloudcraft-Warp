package me.hopedev.cloudwarp.utils;

import me.hopedev.cloudwarp.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class warpDatabaseManager {
    private static File warpFile;
    private static FileConfiguration warpFileconfig;

    public warpDatabaseManager() {

    }

    public static void onPluginEnable() {
        createWarpDatabase();
        warpDatabaseManager.reloadConfig();
    }


    public static FileConfiguration getConfig() {
        reloadConfig();
        return warpFileconfig;
    }

    public static warpDatabaseManager reloadConfig() {
        System.out.println("Reloading config");
        // Insert reload method
        System.out.println("Config reloaded!");

        return new warpDatabaseManager();

    }

    public static void addWarp(String warpname, ItemStack item) {


    }

    public static void saveChanges(FileConfiguration config) {
        try {
            config.save(warpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void createWarpDatabase() {
        warpFile = new File(Main.getPlugin().getDataFolder(), "warps.yml");
        if (!warpFile.exists()) {
            warpFile.getParentFile().mkdirs();
            Main.getPlugin().saveResource("warps.yml", false);
        }

        warpFileconfig = new YamlConfiguration();
        try {
            warpFileconfig.load(warpFile);
            warpFileconfig.createSection("warps.testwarp");
            warpFileconfig.set("warps.testwarp.name", "testing this");
            warpFileconfig.save(warpFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }


    }

}
