package me.b1vth420.marsApi.Utils;

import me.b1vth420.marsApi.Api;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EconomyUtil {

    public static double getBalance(Player p) {
        return Api.getEconomy().getBalance(Bukkit.getOfflinePlayer(p.getUniqueId()));
    }

    public static void addMoney(Player p, double d) {
        Api.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()), -(d));
    }

    public static void removeMoney(Player p, double d) {
        if (getBalance(p) >= d) Api.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()), d);
        else p.sendMessage(ChatUtil.chat("&4Blad &cNie masz tyle pieniedzy!"));
    }
}
