package me.b1vth420.marsNapady.Objects;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.EconomyUtil;
import me.b1vth420.marsNapady.Managers.UserManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class User {

    private String name;
    private UUID uuid;
    private double bankMoney;
    private boolean credit;
    private int creditSize;

    public User(String name, UUID uuid, double bankMoney, boolean credit, int creditSize) {
        this.name = name;
        this.uuid = uuid;
        this.bankMoney = bankMoney;
        this.credit = credit;
        this.creditSize = creditSize;
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

    public int getCreditSize() {
        return creditSize;
    }

    public void addBankMoney(double money) {
        EconomyUtil.removeMoney(Bukkit.getPlayer(this.uuid), money);
        if (EconomyUtil.getBalance(Bukkit.getPlayer(this.uuid)) >= money) {
            bankMoney += money;
        }
    }

    public void sendMessage(String s) {
        Bukkit.getPlayer(this.uuid).sendMessage(ChatUtil.chat(s));
    }

    public void removeBankMoney(double money) {
        if (bankMoney >= money) {
            sendMessage("&aWyplaciles " + money + " ze swojego konta!");
            EconomyUtil.addMoney(Bukkit.getPlayer(uuid), money);
            bankMoney -= money;
        } else {
            sendMessage("&4Blad! &cNie masz tyle pieniedzy!");
        }
    }
}
