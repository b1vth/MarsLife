package me.b1vth420.marsChoroby.Objects;

import org.bukkit.Material;

import java.util.Collection;

public class Medicine {

    private String name;
    private Material material;
    private Collection<String> lore;

    public Medicine(String name, Material material, Collection<String> lore) {
        this.name = name;
        this.material = material;
        this.lore = lore;
    }
}
