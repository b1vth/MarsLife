package me.b1vth420.marsApi.Data;

import me.b1vth420.marsApi.Api;
import org.bukkit.configuration.file.FileConfiguration;


public class Config {

    private static Config inst;
    private FileConfiguration cfg = Api.getInst().getConfig();

    private Config() {
        inst = this;
    }

    public String mysqlIP;
    public int mysqlPort;
    public String mysqlDatabase;
    public String mysqlUsername;
    public String mysqlPassword;
    public boolean mysqlSafeSave;


    public void load() {
        mysqlIP = cfg.getString("MySQL.ip");
        mysqlPort = cfg.getInt("MySQL.port");
        mysqlDatabase = cfg.getString("MySQL.database");
        mysqlUsername = cfg.getString("MySQL.username");
        mysqlPassword = cfg.getString("MySQL.password");
        mysqlSafeSave = cfg.getBoolean("MySQL.safeSave");
    }

    public static Config getInst() {
        if (inst == null) {
            return new Config();
        }
        return inst;
    }
}
