package me.b1vth420.marsNapady.Objects;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.EconomyUtil;
import me.b1vth420.marsNapady.Managers.UserManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class MarsUser {

    private String name;
    private UUID uuid;
    private double bankMoney;
    private boolean credit;
    private int creditSize;
    private long creditTime;

    public MarsUser(String name, UUID uuid, double bankMoney, boolean credit, int creditSize, long creditTime) {
        this.name = name;
        this.uuid = uuid;
        this.bankMoney = bankMoney;
        this.credit = credit;
        this.creditSize = creditSize;
        this.creditTime = creditTime;
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

    public void sendMessage(String s) {
        Bukkit.getPlayer(this.uuid).sendMessage(ChatUtil.chat(s));
    }

    public void resetCredit() {
        this.setCredit(false);
        this.setCreditSize(0);
        this.setCreditTime(0);
    }

}
