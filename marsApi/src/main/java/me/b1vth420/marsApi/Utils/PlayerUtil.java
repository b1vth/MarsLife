package me.b1vth420.marsApi.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtil {

    public static List<Player> getPlayersWithPermission(String permission) {
        List<Player> toReturn = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission(permission)) toReturn.add(p);
        }
        return toReturn;
    }
}
