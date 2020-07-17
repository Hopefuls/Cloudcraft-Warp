package me.hopedev.cloudwarp.utils;

import me.hopedev.cloudwarp.GUI.GUIManager;
import me.hopedev.cloudwarp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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

        return getConfig().isSet("warps." + warpnamecheck);
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
                return;
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
        System.err.println(warpname);
        System.out.println("Warp set");
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

        if (warp.get("warps." + warpname + ".location.world") != null) {
            activePlayer.sendMessage("§aWarp wurde aktualisiert!");
        } else {
            activePlayer.sendMessage("§aWarp wurde erstellt!");
        }
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
            GUIManager.updateWarpGUI();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void createWarpDatabase() {
        warpFile = new File(Main.getPlugin().getDataFolder(), "warps.yml");
        if (!warpFile.exists()) {
            System.out.println("Warp Datenbank existiert nicht! Erstelle eine..");
            warpFile.getParentFile().mkdirs();

            Main.getPlugin().saveResource("warps.yml", false);
            System.out.println("Erstellt!");
        }
        warpFileconfig = new YamlConfiguration();

        try {
            warpFileconfig.load(warpFile);
            GUIManager.updateWarpGUI();

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void teleportToWarp(Player sender, Player target, String warpname) {
        if (!getWarp(warpname).exists()) {
            sender.sendMessage("Dieser warp existiert nicht!");
            return;
        }
        FileConfiguration warp = getConfig();
        String locatemp = "warps." + warpname + ".location";

        World world = Bukkit.getServer().getWorld(warp.getString(locatemp + ".world"));

        double x = warp.getDouble(locatemp + ".x");
        double y = warp.getDouble(locatemp + ".y");
        double z = warp.getDouble(locatemp + ".z");
        float pitch = Float.parseFloat(warp.getString(locatemp + ".looking.pitch"));
        float yaw = Float.parseFloat(warp.getString(locatemp + ".looking.yaw"));


        Location location = new Location(world, x, y, z, yaw, pitch);
        target.teleport(location);
        target.sendMessage("Du wurdest teleportiert");
        if (sender != null) {
            sender.sendMessage("Du hast " + target.getName() + " teleportiert!");

        }
    }

    public static void deleteWarp(String warpname) {
        FileConfiguration warp = getConfig();

        if (warp.get("warps." + warpname) == null) {
            System.out.println("Warp does not exist, returning");
            return;
        }
        warp.set("warps." + warpname, null);
        saveChanges(warp);
    }

}
