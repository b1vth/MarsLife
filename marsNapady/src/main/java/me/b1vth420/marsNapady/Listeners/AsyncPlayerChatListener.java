package me.b1vth420.marsNapady.Listeners;

import me.b1vth420.marsNapady.Managers.UserManager;
import me.b1vth420.marsNapady.Objects.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class AsyncPlayerChatListener implements Listener {

    private static HashMap<Player, String> data = new HashMap<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        User u = UserManager.getUser(p);
        if (!data.containsKey(p)) {
            return;
        }

        if (data.get(p).equals("wyplacanie")) {
            e.setCancelled(true);
            u.removeBankMoney(Double.parseDouble(e.getMessage().split(" ")[0]));
            removeData(p);
        }

        if (data.get(p).equals("wplacanie")) {
            e.setCancelled(true);
            u.addBankMoney(Double.parseDouble(e.getMessage().split(" ")[0]));
            removeData(p);
        }

    }

    public static void addData(Player p, String s) {
        data.put(p, s);
    }

    public static void removeData(Player p) {
        data.remove(p);
    }

    public static HashMap<Player, String> getData() {
        return new HashMap<>(data);
    }
}
