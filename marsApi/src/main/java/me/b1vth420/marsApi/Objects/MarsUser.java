package me.b1vth420.marsApi.Objects;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.EconomyUtil;
import me.b1vth420.marsChoroby.Api.PlayerDiseaseChangeEvent;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class MarsUser {

    private String name;
    private UUID uuid;
    private double bankMoney;
    private boolean credit;
    private int creditSize;
    private long creditTime;
    private long lastSleepTime;
    private HashMap<Disease, String> diseases;

    public MarsUser(String name, UUID uuid, double bankMoney, boolean credit, int creditSize, long creditTime, HashMap<Disease, String> diseases) {
        this.name = name;
        this.uuid = uuid;
        this.bankMoney = bankMoney;
        this.credit = credit;
        this.creditSize = creditSize;
        this.creditTime = creditTime;
        this.lastSleepTime = System.currentTimeMillis();
        this.diseases = diseases;
        UserManager.addUser(this);
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getBankMoney() {
        return bankMoney;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public void setCreditSize(int size) {
        this.creditSize = size;
    }

    public int getCreditSize() {
        return creditSize;
    }

    public long getCreditTime() {
        return creditTime;
    }

    public long getLastSleepTime() { return lastSleepTime; }

    public void setLastSleepTime(long time) { this.lastSleepTime = time; }

    public void setCreditTime(long time) {
        this.creditTime = time;
    }

    public void addBankMoney(double money) {
        if (EconomyUtil.getBalance(Bukkit.getPlayer(this.uuid)) >= money) {
            EconomyUtil.removeMoney(Bukkit.getPlayer(this.uuid), money);
            bankMoney += money;
            sendMessage(ChatUtil.chat("&aWplaciles " + ChatUtil.formatDouble(money) + "$"));
        } else {
            sendMessage(ChatUtil.chat("&4Blad! &cNie masz tyle pieniedzy!"));
        }
    }

    public void removeBankMoney(double money) {
        if (bankMoney >= money) {
            sendMessage("&aWyplaciles " + money + "$ ze swojego konta!");
            EconomyUtil.addMoney(Bukkit.getPlayer(uuid), money);
            bankMoney -= money;
        } else {
            sendMessage("&4Blad! &cNie masz tyle pieniedzy!");
        }
    }

    public boolean hasDisease(Disease d) {
        if(diseases.containsKey(d)) return true;
        return false;
    }

    public void addDisease(Disease d, Player p) {
        PlayerDiseaseChangeEvent event = new PlayerDiseaseChangeEvent(p, d);
        Bukkit.getPluginManager().callEvent(event);
        if(hasDisease(d)) event.setCancelled(true);
        if (!event.isCancelled()) {
            diseases.put(d, "0");
            p.addPotionEffects(d.getEffects());
            p.sendMessage(ChatUtil.chat(d.getMessage()));
        }
    }

    public HashMap<Disease, String> getDiseasesMap() {
        return new HashMap<>(diseases);
    }

    public void removeDisease(Disease d) {
        if(diseases.containsKey(d)) diseases.remove(d);
    }

    public Set<Disease> getDiseases() { return diseases.keySet(); }

    public void sendMessage(String s) {
        Bukkit.getPlayer(this.uuid).sendMessage(ChatUtil.chat(s));
    }

    public void resetCredit() {
        this.setCredit(false);
        this.setCreditSize(0);
        this.setCreditTime(0);
    }

}
