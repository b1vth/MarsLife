package me.b1vth420.marsApi.Utils;

import me.b1vth420.marsApi.Api;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class EconomyUtil {

    public static double getBalance(Player p) {
        OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
        return Api.getEconomy().getBalance(op);
    }

    public static void addMoney(Player p, double d) {
        Api.getEconomy().depositPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()), d);
    }

    public static void removeMoney(Player p, double d) {
        Api.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()), d);
    }
}
