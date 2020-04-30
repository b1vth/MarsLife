package me.b1vth420.marsChoroby.Objects;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public class Medicine {

    private String name;
    private Material material;
    private Collection<String> lore;
    private ItemStack cure;

    public Medicine(String name, Material material, List<String> lore) {
        this.name = name;
        this.material = material;
        this.lore = lore;
        this.cure = ItemUtil.BuildItem(material, ChatUtil.chat(name), lore);
    }

    public ItemStack getCure() {
        return cure;
    }
}
