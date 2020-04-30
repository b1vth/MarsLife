package me.b1vth420.marsChoroby.Listeners;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import me.b1vth420.marsApi.Utils.RandomUtil;
import me.b1vth420.marsChoroby.Enum.DiseaseCause;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerConsumeListener implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {

        MarsUser mu = UserManager.getUser(e.getPlayer());

        e.getPlayer().getLocation();
        if(e.getItem().getType() == Material.ROTTEN_FLESH || e.getItem().getType() == Material.RAW_CHICKEN) {
            for(Disease d : DiseaseManager.getDiseases().values()) {
                if(!mu.hasDisease(d) && d.getCause() == DiseaseCause.EAT && RandomUtil.getChance(d.getChance())) mu.addDisease(d, e.getPlayer());
            }
        }
    }
}
