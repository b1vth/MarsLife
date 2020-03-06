package me.b1vth420.marsNapady.Listeners;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsNapady.Managers.UserManager;
import me.b1vth420.marsNapady.Objects.MarsUser;
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
        MarsUser u = UserManager.getUser(p);
        if (!getData().containsKey(p) || getData().get(p) == null) {
            return;
        }

        if (getData().get(p).equals("wyplacanie")) {
            e.setCancelled(true);
            if (!ChatUtil.isDouble(e.getMessage().split(" ")[0])) {
                p.sendMessage(ChatUtil.chat("&4Blad! &cTo nie jest liczba!"));
                return;
            }
            u.removeBankMoney(Double.parseDouble(e.getMessage().split(" ")[0]));
            removeData(p);
            return;
        }

        if (getData().get(p).equals("wplacanie")) {
            e.setCancelled(true);
            if (!ChatUtil.isDouble(e.getMessage().split(" ")[0])) {
                p.sendMessage(ChatUtil.chat("&4Blad! &cTo nie jest liczba!"));
                return;
            }
            u.addBankMoney(Double.parseDouble(e.getMessage().split(" ")[0]));
            removeData(p);
            return;
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
