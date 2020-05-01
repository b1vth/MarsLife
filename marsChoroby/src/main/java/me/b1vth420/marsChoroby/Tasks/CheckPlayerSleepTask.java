package me.b1vth420.marsChoroby.Tasks;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckPlayerSleepTask extends BukkitRunnable {
    @Override
    public void run() {
        for(MarsUser mu : UserManager.getUsers().values()) {
            if(!mu.isOnline()) continue;;
            for(Disease d : DiseaseManager.getDiseases().values()) {
                if (d.isSleep() && mu.getLastSleepTime() + d.getNoSleepTime() > System.currentTimeMillis()) {
                    mu.addDisease(d, Bukkit.getPlayer(mu.getUuid()));
                }
            }
        }
    }
}
