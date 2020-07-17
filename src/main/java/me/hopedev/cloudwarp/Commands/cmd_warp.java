package me.hopedev.cloudwarp.Commands;

import me.hopedev.cloudwarp.GUI.DefaultPage;
import me.hopedev.cloudwarp.GUI.GUIManager;
import me.hopedev.cloudwarp.utils.Appender;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
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
            DefaultPage.open(p, 1);
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
                warpDatabaseManager.teleportToWarp(p, p, warpname);
                break;

            //other
            case "open":
                GUIManager.pagedInventory.keySet().forEach(System.out::println);
                p.openInventory(GUIManager.pagedInventory.get(Integer.valueOf(args[1])));
                break;
            default:

                p.sendMessage("Testing");
                //Open GUI
                break;

        }
        return false;
    }
}
