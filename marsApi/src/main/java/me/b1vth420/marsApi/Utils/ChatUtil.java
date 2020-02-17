package me.b1vth420.marsApi.Utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class ChatUtil {

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sendActionBar(Player p, String message) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(chat(message)).create());
    }

    public static void broadcastActionBar(String message) {
        for (Player p : Bukkit.getOnlinePlayers())
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(chat(message)).create());
    }

    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(d);
    }

    public static boolean isDouble(String str) {
        try {
            double v = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }
}
