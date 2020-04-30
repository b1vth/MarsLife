package me.b1vth420.marsChoroby.Listeners;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        MarsUser mu = UserManager.getUser(e.getPlayer());
        if(e.getPlayer().getLastPlayed() + (30*1000*60) > System.currentTimeMillis()) mu.setLastSleepTime(System.currentTimeMillis());
    }
}
