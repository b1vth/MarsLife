package me.b1vth420.marsNapady.Gui;

import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.ItemUtil;
import me.b1vth420.marsNapady.Data.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class NpcGui {
    public static InventoryView npcGui(Player p, String s) {
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatUtil.chat("&a" + s));
        inv.setItem(0, ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&cNapad"), (short) 14));
        inv.setItem(1, ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&aWyplac"), (short) 5));
        inv.setItem(2, ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&2Wplac"), (short) 4));
        inv.setItem(3, ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&aStan konta: " + ChatUtil.formatDouble(UserManager.getUser(p).getBankMoney())) + "$", (short) 12));
        inv.setItem(4, ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&bPozyczka"), (short) 11));
        for (ItemStack is : Config.getInst().heistItems) {
            inv.addItem(is);
        }
        return p.openInventory(inv);
    }

    public static InventoryView pozyczkaGui(Player p, String s) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatUtil.chat("&a" + s));
        inv.setItem(10, ItemUtil.BuildItem(Material.IRON_NUGGET, ChatUtil.chat("&a2000$")));
        inv.setItem(12, ItemUtil.BuildItem(Material.GOLD_NUGGET, ChatUtil.chat("&a5000$")));
        inv.setItem(14, ItemUtil.BuildItem(Material.GOLD_INGOT, ChatUtil.chat("&a10000$")));
        inv.setItem(16, ItemUtil.BuildItem(Material.GOLD_BLOCK, ChatUtil.chat("&a20000$")));
        if (UserManager.getUser(p).isCredit()) {
            inv.setItem(26, ItemUtil.BuildItem(Material.GOLD_NUGGET, ChatUtil.chat("&2Splac pozyczke!")));
        }
        return p.openInventory(inv);
    }
}
