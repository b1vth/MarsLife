package me.b1vth420.marsChoroby.Listeners;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.DataUtil;
import me.b1vth420.marsApi.Utils.ItemUtil;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        MarsUser mu = UserManager.getUser(p);

        if(p.getInventory().getItemInMainHand() == null) return;

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            for(Disease d : DiseaseManager.getDiseases().values()) {
                if(mu.hasDisease(d)) {
                    if (ItemUtil.checkItem(p.getInventory().getItemInMainHand(), d.getMedicine().getCure())) {
                        mu.getDiseasesMap().put(d, DataUtil.parseDateDiff(d.getMedicine().getCureTime(), true));
                        e.getPlayer().sendMessage(ChatUtil.chat(d.getMedicine().getCureUseMessage()));
                    }
                }
            }
        }
    }
}
