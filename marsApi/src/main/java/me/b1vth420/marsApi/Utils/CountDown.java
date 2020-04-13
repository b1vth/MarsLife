package me.b1vth420.marsApi.Utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class CountDown {

    private int time;

    protected BukkitTask task;
    protected Plugin plugin;
    protected Player p;

    public CountDown(int time, Player p, Plugin plugin) {
        this.plugin = plugin;
        this.time = time;
        this.p = p;
    }

    public abstract void count(int current);

    public final void start() {
        task = new BukkitRunnable() {

            @Override
            public void run() {
                count(time);
                if (time-- <= 0) cancel();
            }

        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }
}
