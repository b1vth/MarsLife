package me.b1vth420.marsChoroby.Listeners;

import marslife.drinking.api.DrinkChangeEvent;
import me.b1vth420.marsChoroby.Enum.DiseaseCause;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDrinkChangeListener implements Listener {

    @EventHandler
    public void drinkChange(DrinkChangeEvent e) {
        for(Disease d : DiseaseManager.getDiseases().values()) {
            if(d.getCause() == DiseaseCause.DRINK) {

            }
        }
    }
}
