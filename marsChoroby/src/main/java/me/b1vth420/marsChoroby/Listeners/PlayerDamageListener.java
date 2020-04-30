package me.b1vth420.marsChoroby.Listeners;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import me.b1vth420.marsApi.Utils.RandomUtil;
import me.b1vth420.marsChoroby.Enum.DiseaseCause;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            MarsUser mu = UserManager.getUser((Player) e.getEntity());
            for (Disease d : DiseaseManager.getDiseases().values()) {
                if (!mu.hasDisease(d) && d.getCause() == DiseaseCause.LEG || d.getCause() == DiseaseCause.HAND && RandomUtil.getChance(d.getChance()))
                    mu.addDisease(d, ((Player) e.getEntity()));
            }
        }

        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            MarsUser mu = UserManager.getUser((Player) e.getEntity());
            for(Disease d : DiseaseManager.getDiseases().values()) {
                if(!mu.hasDisease(d) && d.getCause() == DiseaseCause.HAND && RandomUtil.getChance(d.getChance()))
                    mu.addDisease(d, ((Player) e.getEntity()));
            }
        }
    }
}
