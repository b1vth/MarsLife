package me.b1vth420.marsChoroby.Objects;

import me.b1vth420.marsChoroby.Enum.DiseaseCause;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
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
    private boolean isBreak;
    private int fallDistance;
    private List<PotionEffect> effects;
    private Medicine medicine;

    public Disease(String name, String visibleName, double chance, String cause, boolean isContagious, double contagiousChance, int contagiousMinDistance, String message, boolean isBreak, int fallDistance, List<PotionEffect> effects, String cureName, Material cureMaterial, Collection<String> cureLore) {
        this.name = name;
        this.visibleName = visibleName;
        this.chance = chance;
        this.cause = DiseaseCause.valueOf(cause.toUpperCase());
        this.isContagious = isContagious;
        this.contagiousChance = contagiousChance;
        this.contagiousMinDistance = contagiousMinDistance;
        this.message = message;
        this.isBreak = isBreak;
        this.fallDistance = fallDistance;
        this.effects = effects;
        medicine = new Medicine(cureName, cureMaterial, cureLore);
        DiseaseManager.addDisease(name,this);
    }

    public String getName() {
        return name;
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

    public boolean isBreak() {
        return isBreak;
    }

    public int getFallDistance() {
        return fallDistance;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }

    public Medicine getMedicine() {
        return medicine;
    }
}
