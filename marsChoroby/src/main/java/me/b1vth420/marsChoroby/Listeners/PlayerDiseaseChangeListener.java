package me.b1vth420.marsChoroby.Listeners;

import me.b1vth420.marsChoroby.Api.PlayerDiseaseChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDiseaseChangeListener implements Listener {

    @EventHandler
    public void onChange(PlayerDiseaseChangeEvent e) {
        if(e.getPlayer().isOp()) e.setCancelled(true);
    }
}
