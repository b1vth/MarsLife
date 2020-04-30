package me.b1vth420.marsChoroby;

import me.b1vth420.marsApi.Utils.DataUtil;
import me.b1vth420.marsChoroby.Listeners.PlayerDrinkChangeListener;
import me.b1vth420.marsChoroby.Utils.DiseasesLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main inst;

    public Main() {
        inst = this;
    }

    @Override
    public void onEnable() {
        init();
        registerListeners();
        System.out.println("THIS IS SIMPLE DEBUGGER WITH LONG STRING TO EASY SEE IN CONSOLE " + DataUtil.parseDateDiff("30m", false));
    }

    @Override
    public void onDisable() {

    }

    void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerDrinkChangeListener(), this);
    }

    void init() {
        saveDefaultConfig();
        DiseasesLoader.loadDiseases();
    }

    public static Main getInst() {
        if (inst != null) return inst;
        return new Main();
    }

}
