package me.b1vth420.marsNapady.Gui;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.ItemUtil;
import me.b1vth420.marsNapady.Managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class BankierGui {
    public static InventoryView npcGui(Player p, String s) {
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatUtil.chat("&a" + s));
        inv.setItem(0, ItemUtil.BuildItem(Material.HOPPER, ChatUtil.chat("&aWplac")));
        inv.setItem(2, ItemUtil.BuildItem(Material.GOLD_NUGGET, ChatUtil.chat("&aStan konta " + UserManager.getUser(p).getBankMoney())));
        inv.setItem(4, ItemUtil.BuildItem(Material.SIGN, ChatUtil.chat("&2Wplac")));
        return p.openInventory(inv);
    }
}
