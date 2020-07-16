package me.hopedev.cloudwarp.Commands;

import me.hopedev.cloudwarp.utils.Appender;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.Location;
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
        switch (args[0]) {
            case "set":
                FileConfiguration warp = warpDatabaseManager.getConfig();
                String name = new Appender(2, args).getAppend().getConvertedString();
                warp.createSection("warps." + args[1]);
                warp.set("warps." + args[1] + ".name", name);

                String loctemp = "warps." + args[1] + ".location";
                Location ploc = p.getLocation();
                // TODO Faulheit loswerden

                warp.createSection(loctemp);
                warp.set(loctemp + ".world", ploc.getWorld().getName());
                warp.set(loctemp + ".x", ploc.getX());
                warp.set(loctemp + ".y", ploc.getY());
                warp.set(loctemp + ".z", ploc.getZ());
                warp.createSection(loctemp + ".looking");
                warp.set(loctemp + ".looking.yaw", ploc.getYaw());
                warp.set(loctemp + ".looking.pitch", ploc.getPitch());


                warpDatabaseManager.saveChanges(warp);
                p.sendMessage("Warp erfolgreich gesetzt!");
                break;

            case "goto":


                // Put others in here
            default:

                p.sendMessage("Testing");
                //Open GUI
                break;

        }
        return false;
    }
}
