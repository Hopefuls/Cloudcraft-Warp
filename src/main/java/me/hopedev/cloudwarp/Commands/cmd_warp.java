package me.hopedev.cloudwarp.Commands;

import me.hopedev.cloudwarp.utils.warpParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
                // setwarp in here
                break;


            // Put others in here
            default:
                p.sendMessage("Testing");
                //Open GUI
                break;

        }
        return false;
    }
}
