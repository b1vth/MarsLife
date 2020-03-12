package me.b1vth420.marsChoroby;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main inst;

    public Main() {
        inst = this;
    }

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {

    }

    void init() {
        saveDefaultConfig();
    }

    public static Main getInst() {
        if (inst != null) return inst;
        return new Main();
    }

}
