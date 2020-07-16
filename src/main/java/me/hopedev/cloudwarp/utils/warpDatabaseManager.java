package me.hopedev.cloudwarp.utils;

import me.hopedev.cloudwarp.Main;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class warpDatabaseManager {
    static Player activePlayer;
    private static File warpFile;
    private static FileConfiguration warpFileconfig;
    private static String warpnamecheck;

    public warpDatabaseManager(Player p) {
        activePlayer = p;
    }

    public static warpDatabaseManager getWarp(String warpnamecheck) {
        warpDatabaseManager.warpnamecheck = warpnamecheck;
        return new warpDatabaseManager(activePlayer);
    }

    public static boolean exists() {

        if (getConfig().isSet("warps." + warpnamecheck))
            return true;

        else

            return false;
    }


    public static void onPluginEnable() {
        createWarpDatabase();
        warpDatabaseManager.reloadConfig(false);
    }


    public static FileConfiguration getConfig() {
        reloadConfig(true);
        return warpFileconfig;
    }

    public static void reloadConfig(boolean silent) {
        if (silent) {
            System.out.println("Reloading config/warps");
            try {
                warpFileconfig.load(warpFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }

            System.out.println("config/warps reloaded!");
            return;
        }

        try {
            warpFileconfig.load(warpFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static void setWarp(String warpname, String warptitle, Location playerLocation) {
        FileConfiguration warp = getConfig();
        warp.createSection("warps." + warpname);

        warp.set("warps." + warpname + ".name", warptitle);

        String loctemp = "warps." + warpname + ".location";

        // TODO Faulheit loswerden

        warp.createSection(loctemp);
        warp.set(loctemp + ".world", playerLocation.getWorld().getName());
        warp.set(loctemp + ".x", playerLocation.getX());
        warp.set(loctemp + ".y", playerLocation.getY());
        warp.set(loctemp + ".z", playerLocation.getZ());
        warp.createSection(loctemp + ".looking");
        warp.set(loctemp + ".looking.yaw", playerLocation.getYaw());
        warp.set(loctemp + ".looking.pitch", playerLocation.getPitch());


        warpDatabaseManager.saveChanges(warp);


    }

    public static void updateWarp(String warpname, String warptitle) {
        if (!getWarp(warpname).exists()) {
            System.err.println("Warp doesn't exist");
        } else {
            Location playerLocation = activePlayer.getLocation();
            FileConfiguration warp = getConfig();

            warp.set("warps." + warpname + ".name", warptitle);

            String loctemp = "warps." + warpname + ".location";

            // TODO Faulheit loswerden

            warp.set(loctemp + ".world", playerLocation.getWorld().getName());
            warp.set(loctemp + ".x", playerLocation.getX());
            warp.set(loctemp + ".y", playerLocation.getY());
            warp.set(loctemp + ".z", playerLocation.getZ());
            warp.set(loctemp + ".looking.yaw", playerLocation.getYaw());
            warp.set(loctemp + ".looking.pitch", playerLocation.getPitch());


            warpDatabaseManager.saveChanges(warp);
        }
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
