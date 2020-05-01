package me.b1vth420.marsChoroby.Objects;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public class Medicine {

    private ItemStack cure;
    private String cureTime;
    private String cureUseMessage;

    public Medicine(String name, Material material, List<String> lore, String cureTime, String cureUseMessage) {
        this.cure = ItemUtil.BuildItem(material, ChatUtil.chat(name), lore);
        this.cureTime = cureTime;
        this.cureUseMessage = cureUseMessage;
    }

    public ItemStack getCure() {
        return this.cure;
    }

    public String getCureTime() { return this.cureTime; }

    public String getCureUseMessage() { return this.cureUseMessage; }
}
