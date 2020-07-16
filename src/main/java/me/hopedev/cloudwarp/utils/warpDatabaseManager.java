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
        warpDatabaseManager.reloadConfig(false);
    }


    public static FileConfiguration getConfig() {
        reloadConfig(true);
        return warpFileconfig;
    }

    public static warpDatabaseManager reloadConfig(boolean silent) {
        if (silent) {
            System.out.println("Reloading config/warps");
            try {
                warpFileconfig.load(warpFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }

            System.out.println("config/warps reloaded!");
            return new warpDatabaseManager();
        }

        try {
            warpFileconfig.load(warpFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return new warpDatabaseManager();
    }

    public static void addWarp(String warpname, String warptitle, ItemStack item) {


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
            System.out.println("Warp datenbank existiert nicht! Erstelle eine..");
            warpFile.getParentFile().mkdirs();

            Main.getPlugin().saveResource("warps.yml", false);
            System.out.println("Erstellt!");
        }
        warpFileconfig = new YamlConfiguration();

        try {
            warpFileconfig.load(warpFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
