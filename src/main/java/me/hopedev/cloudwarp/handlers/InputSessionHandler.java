package me.hopedev.cloudwarp.handlers;

import me.hopedev.cloudwarp.utils.warpDatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class InputSessionHandler implements Listener {
    public static HashMap<Player, CurrentSession> session = new HashMap<>();
    public static HashMap<Player, HashMap<CurrentSession, String>> sessionInput = new HashMap<>();

    public static void setSession(Player p, CurrentSession currentSession) {
        session.put(p, currentSession);
    }

    public static String getSessionContent(Player p, CurrentSession currentSession) {
        return sessionInput.get(p).get(currentSession);
    }

    public static void updateSessionInput(Player p, CurrentSession currentSession, String text) {
        HashMap<CurrentSession, String> hashy;
        if (sessionInput.get(p) == null) {
            hashy = new HashMap<>();
        } else {
            hashy = sessionInput.get(p);
        }
        hashy.put(currentSession, text);
        sessionInput.put(p, hashy);

        // System.out.println("Does this work? " + sessionInput.get(p).get(currentSession));
        // System.out.println("Successfully set session to " + getSessionContent(p, currentSession));
    }

    public static void deleteSession(Player p) {
        if (session.get(p) != null) {
            session.remove(p);
            sessionInput.remove(p);
        }
    }

    @EventHandler
    public void onSessionInput(AsyncPlayerChatEvent event) {
        warpDatabaseManager dbmgr = new warpDatabaseManager(event.getPlayer());
        Player p = event.getPlayer();
        if (!p.hasPermission("cloudwarp.admin")) {
            return;
        }

        if (session.get(p) == null) {
            return;
        }

        if (session.get(p).equals(CurrentSession.WarpName)) {
            event.setCancelled(true);
            if (event.getMessage().equalsIgnoreCase("abort")) {
                event.getPlayer().sendMessage("§cAbgebrochen.");
                p.playSound(p.getLocation(), Sound.BAT_DEATH, 1L, 1L);

                deleteSession(p);
                return;
            }

            String[] split = event.getMessage().split(" ");
            if (split.length > 1) {
                p.sendMessage("§cDer Warpname darf nur 1 Wort lang sein!");
            } else {
                updateSessionInput(p, CurrentSession.WarpName, event.getMessage());
                setSession(p, CurrentSession.WarpTitle);
                p.sendMessage("§c");
                p.sendMessage("§bDer neue Warp wird §9" + event.getMessage() + " §bheißen");
                p.sendMessage("§bGebe bitte jetzt einen Titel für den Warp ein (Mehrere Wörter, alle Zeichen) (Colorcodes könnenn verwendet werden)");
                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1L, 1L);

            }

        } else if (session.get(p).equals(CurrentSession.WarpTitle)) {
            event.setCancelled(true);
            if (event.getMessage().equalsIgnoreCase("abort")) {
                deleteSession(p);
                event.getPlayer().sendMessage("§cAbgebrochen");
                p.playSound(p.getLocation(), Sound.BAT_DEATH, 1L, 1L);

            } else {
                updateSessionInput(p, CurrentSession.WarpTitle, event.getMessage());

                setSession(p, CurrentSession.WarpDescription);
                p.sendMessage("§c");
                p.sendMessage("§bDer Warp wird den Titel \"" + event.getMessage().replaceAll("&", "§") + "§r§b\" haben");
                p.sendMessage("§bGebe bitte jetzt eine Beschreibung für den Warp ein. Benutze %next% um die Zeile zu wechseln (Beispiel: \"Hallo das ist Zeile1%next%Hallo das ist Zeile2\")");
                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1L, 1L);

                // HashMap<CurrentSession, String> hashy = sessionInput.get(p);
                // System.out.println("Inputsessionhandler hashy " + hashy.get(CurrentSession.WarpName));
                // warpDatabaseManager.setWarp(getSessionContent(p, CurrentSession.WarpName), getSessionContent(p, CurrentSession.WarpTitle), p.getLocation(), Bukkit.getOfflinePlayer(p.getUniqueId()));
                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1L, 2L);


            }
        } else if (session.get(p).equals(CurrentSession.WarpDescription)) {
            event.setCancelled(true);
            if (event.getMessage().equalsIgnoreCase("abort")) {
                deleteSession(p);
                event.getPlayer().sendMessage("§cAbgebrochen");
                p.playSound(p.getLocation(), Sound.BAT_DEATH, 1L, 1L);
            } else {

                updateSessionInput(p, CurrentSession.WarpDescription, event.getMessage().replaceAll("&", "§"));

                // HashMap<CurrentSession, String> hashy = sessionInput.get(p); --> UNUSED FOR NOW

                // System.out.println("Inputsessionhandler hashy " + hashy.get(CurrentSession.WarpName));
                warpDatabaseManager.setWarp(getSessionContent(p, CurrentSession.WarpName), getSessionContent(p, CurrentSession.WarpTitle), p.getLocation(), Bukkit.getOfflinePlayer(p.getUniqueId()), getSessionContent(p, CurrentSession.WarpDescription));
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1L, 2L);
                deleteSession(p);
            }
        }

    }

    public enum CurrentSession {
        WarpName,
        WarpTitle,
        WarpDescription,
    }
}
