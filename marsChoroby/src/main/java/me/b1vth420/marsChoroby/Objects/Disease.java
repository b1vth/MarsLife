package me.b1vth420.marsChoroby.Objects;

import me.b1vth420.marsChoroby.Enum.DiseaseCause;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import org.bukkit.Effect;
import org.bukkit.Material;

import java.util.Collection;

public class Disease {

    private String name;
    private double chance;
    private DiseaseCause cause;
    private boolean isContagious;
    private double contagiousChance;
    private int contagiousMinDistance;
    private String message;
    private boolean isBreak;
    private int fallDistance;
    private Collection<Effect> effects;
    private Medicine medicine;

    public Disease(String name, double chance, String cause, boolean isContagious, double contagiousChance, int contagiousMinDistance, String message, boolean isBreak, int fallDistance, Collection<Effect> effects, String cureName, Material cureMaterial, Collection<String> cureLore) {
        this.name = name;
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
        DiseaseManager.addDisease(this);
    }
}
