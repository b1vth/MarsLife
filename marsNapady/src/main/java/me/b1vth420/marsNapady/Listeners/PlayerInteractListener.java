package me.b1vth420.marsNapady.Listeners;

import me.b1vth420.marsNapady.Data.Config;
import me.b1vth420.marsNapady.Gui.BankierGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (Config.getInst().bankomat.contains(e.getClickedBlock().getLocation())) {
                BankierGui.npcGui(e.getPlayer(), "Bankomat");
            }
        }
    }
}
