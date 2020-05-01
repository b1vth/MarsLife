package me.b1vth420.marsChoroby.Tasks;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class DiseaseCheckTask extends BukkitRunnable {

    @Override
    public void run() {
        for(MarsUser mu : UserManager.getUsers().values()) {
            if(!mu.isOnline()) continue;
            for(Map.Entry<Disease, Long> entry : mu.getDiseasesMap().entrySet()) {
                if(entry.getValue() != 0L && entry.getValue() < System.currentTimeMillis()) {
                    mu.removeDisease(entry.getKey(), Bukkit.getPlayer(mu.getUuid()));
                }
            }
        }
    }
}