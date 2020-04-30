package me.b1vth420.marsChoroby.Listeners;

import marslife.drinking.api.DrinkChangeEvent;
import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.RandomUtil;
import me.b1vth420.marsChoroby.Enum.DiseaseCause;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

public class PlayerDrinkChangeListener implements Listener {

    @EventHandler
    public void drinkChange(DrinkChangeEvent e) {

        MarsUser mu = UserManager.getUser(e.getPlayer());

        if(e.getNawodnienieNew() > e.getNawodnienieOld()) {
            for(Disease d : mu.getDiseases()) {
                if(d.isDrink() && e.getNawodnienieNew() > d.getMinDrinkValue()) {
                    mu.removeDisease(d);
                    e.getPlayer().sendMessage(ChatUtil.chat(d.getHealthyMessage()));
                    for(PotionEffect ef : d.getEffects()) {
                        e.getPlayer().removePotionEffect(ef.getType());
                    }
                }
            }
            return;
        }

        for (Disease d : DiseaseManager.getDiseases().values()) {
            if (!mu.hasDisease(d) && d.getCause() == DiseaseCause.DRINK && d.isDrink() && RandomUtil.getChance(d.getChance()) && e.getNawodnienieNew() < d.getMinDrinkValue()) mu.addDisease(d, e.getPlayer());
        }
    }
}