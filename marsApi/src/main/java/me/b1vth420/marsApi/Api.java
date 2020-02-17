package me.b1vth420.marsApi;

import me.b1vth420.marsApi.Data.Config;
import me.b1vth420.marsApi.Data.FileManager;
import me.b1vth420.marsApi.Data.MySQL.SQLManager;
import me.b1vth420.marsApi.Utils.SignMenuFactory;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Api extends JavaPlugin {

    private static Api inst;
    private SQLManager sql;
    private SignMenuFactory signMenuFactory;
    private static Economy econ;


    public Api() {
        inst = this;
    }

    @Override
    public void onEnable() {
        init();
    }

    @Override
    public void onDisable() {
        sql.onDisable();
    }

    private void init() {
        FileManager.check();
        Config.getInst().load();
        registerDatabase();
        setupEconomy();
        if (this.signMenuFactory == null) this.signMenuFactory = new SignMenuFactory(this);
    }

    public static Api getInst() {
        if (inst == null) return new Api();
        return inst;
    }

    private void registerDatabase() {
        sql = new SQLManager(this);
    }

    public SQLManager getSQLManager() {
        return this.sql;
    }

    public SignMenuFactory getSignMenuFactory() {
        return this.signMenuFactory;
    }

    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
