package me.hopedev.cloudwarp.Commands;

import me.hopedev.cloudwarp.utils.Appender;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class cmd_warp implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Dieser Befehl kann nur von Spielern benutzt werden");
            return true;
        }
        Player p = (Player) sender;

        if (args.length < 1) {
            p.sendMessage("Testing");
            //Open GUI
            return true;
        }
        FileConfiguration warp = warpDatabaseManager.getConfig();

        switch (args[0]) {

            case "set":
                if (args.length < 2) {
                    p.sendMessage("Bitte benenne deinen Warp");
                    break;
                }

                if (warpDatabaseManager.getWarp(args[1]).exists()) {

                }
                String name = new Appender(2, args).getAppend().getConvertedString();

                warpDatabaseManager.setWarp(args[1], name, p.getLocation());

                p.sendMessage("Warp gesetzt!");
                break;

            case "goto":
                String warpname = args[1];
                String locatemp = "warps." + args[1] + ".location";

                World world = Bukkit.getServer().getWorld(warp.getString(locatemp + ".world"));

                double x = warp.getDouble(locatemp + ".x");
                double y = warp.getDouble(locatemp + ".y");
                double z = warp.getDouble(locatemp + ".z");
                float pitch = Float.parseFloat(warp.getString(locatemp + ".looking.pitch"));
                float yaw = Float.parseFloat(warp.getString(locatemp + ".looking.yaw"));


                Location location = new Location(world, x, y, z, yaw, pitch);
                p.teleport(location);
                p.sendMessage("Teleportiert!");
                break;

            //other

            default:

                p.sendMessage("Testing");
                //Open GUI
                break;

        }
        return false;
    }
}
