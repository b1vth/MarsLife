package me.b1vth420.marsChoroby.Objects;

import me.b1vth420.marsChoroby.Enum.DiseaseCause;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class Disease {

    private String name;
    private String visibleName;
    private double chance;
    private DiseaseCause cause;
    private boolean isContagious;
    private double contagiousChance;
    private int contagiousMinDistance;
    private String message;
    private String healthyMessage;
    private boolean isBreak;
    private int fallDistance;
    private boolean isDrink;
    private int minDrinkValue;
    private int noSleepTime;
    private List<PotionEffect> effects;
    private boolean isThereCure;
    private Medicine medicine;

    public Disease(String name, String visibleName, double chance, String cause, boolean isContagious, double contagiousChance, int contagiousMinDistance, String message, String healthyMessage, boolean isBreak, int fallDistance, boolean isDrink, int minDrinkValue, int noSleepTime, List<PotionEffect> effects, String cureName, Material cureMaterial, boolean isThereCure, ArrayList<String> cureLore, String cureTime, String cureUseMessage) {
        this.name = name;
        this.visibleName = visibleName;
        this.chance = chance;
        this.cause = DiseaseCause.valueOf(cause.toUpperCase());
        this.isContagious = isContagious;
        this.contagiousChance = contagiousChance;
        this.contagiousMinDistance = contagiousMinDistance;
        this.message = message;
        this.healthyMessage = healthyMessage;
        this.isBreak = isBreak;
        this.isDrink = isDrink;
        this.minDrinkValue = minDrinkValue;
        this.noSleepTime = noSleepTime;
        this.fallDistance = fallDistance;
        this.effects = effects;
        this.isThereCure = isThereCure;
        medicine = new Medicine(cureName, cureMaterial, cureLore, cureTime, cureUseMessage);
        DiseaseManager.addDisease(name,this);
    }

    public String getName() {
        return name;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public double getChance() {
        return chance;
    }

    public DiseaseCause getCause() {
        return cause;
    }

    public boolean isContagious() {
        return isContagious;
    }

    public double getContagiousChance() {
        return contagiousChance;
    }

    public int getContagiousMinDistance() {
        return contagiousMinDistance;
    }

    public String getMessage() {
        return message;
    }

    public String getHealthyMessage() {
        return healthyMessage;
    }

    public boolean isBreak() {
        return isBreak;
    }

    public int getFallDistance() {
        return fallDistance;
    }

    public boolean isDrink() {
        return isDrink;
    }

    public boolean isSleep() { return (getCause() == DiseaseCause.DRINK); }

    public int getMinDrinkValue() {
        return minDrinkValue;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getNoSleepTime() { return (noSleepTime*1000*60); }
}
