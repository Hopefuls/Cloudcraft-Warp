package me.hopedev.cloudwarp.Commands;

import me.hopedev.cloudwarp.GUI.DefaultPage;
import me.hopedev.cloudwarp.handlers.InputSessionHandler;
import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class cmd_warp implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[CLOUDWARP] Dieser Befehl kann nur von Spielern benutzt werden");
            return true;
        }
        Player p = (Player) sender;

        if (args.length < 1) {
            if (InputSessionHandler.session.containsKey(p)) {
                p.sendMessage("§b§l[CloudCraft]§r§c Du kannst derzeit das Warp Menü nicht öffnen, da du im Erstellungsmodus bist");
                p.sendMessage("§cSchreibe \"abort\" um abzubrechen");
                return true;
            }
            // p.sendMessage("Testing");

            DefaultPage.open(p, 1);
            return true;
        }
        FileConfiguration warp = warpDatabaseManager.getConfig();

        switch (args[0]) {

            case "forcereload":
                if (!p.hasPermission("cloudwarp.admin")) {
                    p.sendMessage("§b§l[CloudCraft]§r§c Dazu bist du nicht berechtigt!");
                    return true;
                }

                warpDatabaseManager.reloadConfig(false);

                p.sendMessage("§b§l[CloudCraft]§9§l Warp-Datenbank neugeladen!");
                break;

            default:
                DefaultPage.open(p, 1);
                break;
        }
        return true;
    }
}
