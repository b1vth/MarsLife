package me.b1vth420.marsNapady;

import me.b1vth420.marsApi.Utils.RegisterUtil;
import me.b1vth420.marsNapady.Commands.BankCommand;
import me.b1vth420.marsNapady.Data.Config;
import me.b1vth420.marsNapady.Data.MySQL;
import me.b1vth420.marsNapady.Listeners.AsyncPlayerChatListener;
import me.b1vth420.marsNapady.Listeners.InventoryClickListener;
import me.b1vth420.marsNapady.Listeners.NpcClickListener;
import me.b1vth420.marsNapady.Listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main inst;

    public Main() {
        inst = this;
    }

    @Override
    public void onEnable() {
        init();
        registerCommands();
        registerListeners();
        MySQL.loadUsers();
    }

    @Override
    public void onDisable() {
        Config.getInst().save();
        MySQL.saveUsers();
    }

    void init() {
        Config.getInst().load();
        MySQL.createTable();
    }

    void registerCommands() {
        RegisterUtil.registerCommand(new BankCommand());
    }

    void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new NpcClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
    }


    public static Main getInst() {
        if (inst == null) return new Main();
        return inst;
    }
}
