package me.b1vth420.marsNapady.Listeners;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsApi.Utils.EconomyUtil;
import me.b1vth420.marsApi.Utils.ItemUtil;
import me.b1vth420.marsApi.Utils.RandomUtil;
import me.b1vth420.marsNapady.Data.Config;
import me.b1vth420.marsNapady.Gui.NpcGui;
import me.b1vth420.marsNapady.Main;
import me.b1vth420.marsNapady.Managers.UserManager;
import me.b1vth420.marsNapady.Objects.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        User u = UserManager.getUser(p);
        ItemStack item = e.getCurrentItem();

        if (Config.getInst().npc.contains(e.getInventory().getName().replace("§a", ""))) {
            if (ItemUtil.checkItem(e.getCurrentItem(), ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&cNapad"), (short) 11))) {
                for (Player police : Bukkit.getOnlinePlayers()) {
                    if (police.hasPermission(Config.getInst().policePermission)) {
                        police.sendMessage(ChatUtil.chat("&8### &4Uwaga! &cNapad w toku! (" + p.getLocation().getBlockX() + " " + p.getLocation().getBlockZ() + ") &8###"));
                    }
                }
                p.sendTitle(ChatUtil.chat("&2Napad"), ChatUtil.chat("&aSprzedawca wyjmuje pieniadze z kasy"), 20, 100, 20);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInst(), new Runnable() {
                    @Override
                    public void run() {
                        int money = RandomUtil.getRandInt(20000, 100000);
                        EconomyUtil.addMoney(p, money);
                        p.closeInventory();
                        p.sendTitle(ChatUtil.chat("&2Napad"), ChatUtil.chat("&aOtrzymales " + money + "$ od sprzedawcy"), 20, 100, 20);
                    }
                }, 40 * 20L);
            }

            if (ItemUtil.checkItem(e.getCurrentItem(), ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&aWyplac"), (short) 5))) {
                AsyncPlayerChatListener.addData(p, "wyplacanie");
                p.sendTitle(ChatUtil.chat("&2Wyplacanie"), ChatUtil.chat("&aPodaj kwote na chacie"), 20, 100, 20);
                p.closeInventory();
            }

            if (ItemUtil.checkItem(e.getCurrentItem(), ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&2Wplac"), (short) 4))) {
                AsyncPlayerChatListener.addData(p, "wplacanie");
                p.sendTitle(ChatUtil.chat("&2Wplacanie"), ChatUtil.chat("&aPodaj kwote na chacie"), 20, 100, 20);
                p.closeInventory();
            }

            if (ItemUtil.checkItem(e.getCurrentItem(), ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&bPozyczka"), (short) 14))) {
                NpcGui.pozyczkaGui(p, "&aPozyczka");
            }
            e.setCancelled(true);
        }

        if (e.getInventory().getName().replace("§a", "").equals("Pozyczka")) {
            final int slot = e.getSlot();
            e.setCancelled(true);

            if (slot == 26) {
                if (u.isCredit() && EconomyUtil.getBalance(p) >= u.getCreditSize() + (0.1 * u.getCreditSize())) {
                    EconomyUtil.removeMoney(p, u.getCreditSize() + (0.1 * u.getCreditSize()));
                    p.sendMessage(ChatUtil.chat("&2Splaciles pozyczke!"));
                    p.closeInventory();
                    u.resetCredit();
                } else {
                    p.sendMessage(ChatUtil.chat("&4Blad! &cNie masz przy sobie wystarczajacej kwoty!"));
                }
                return;
            }

            if (u.isCredit()) {
                p.sendMessage(ChatUtil.chat("&4Blad! &cMasz juz jedna pozyczke"));
                p.closeInventory();
                return;
            }
            if (slot == 10) {
                EconomyUtil.addMoney(p, 2000);
                u.setCreditSize(2000);
                u.setCredit(true);
                p.closeInventory();
            }
            if (slot == 12) {
                EconomyUtil.addMoney(p, 5000);
                u.setCreditSize(5000);
                u.setCredit(true);
                p.closeInventory();
            }
            if (slot == 14) {
                EconomyUtil.addMoney(p, 10000);
                u.setCreditSize(10000);
                u.setCredit(true);
                p.closeInventory();
            }
            if (slot == 16) {
                EconomyUtil.addMoney(p, 20000);
                u.setCreditSize(20000);
                u.setCredit(true);
                p.closeInventory();
            }
        }

        if (e.getInventory().getName().equals(ChatUtil.chat("&aBankomat"))) {
            if (item.getType().equals(Material.HOPPER)) {
                AsyncPlayerChatListener.addData(p, "wyplacanie");
                p.sendTitle(ChatUtil.chat("&2Wyplacanie"), ChatUtil.chat("&aPodaj kwote na chacie"), 20, 100, 20);
                p.closeInventory();
            }

            if (item.getType().equals(Material.SIGN)) {
                AsyncPlayerChatListener.addData(p, "wplacanie");
                p.sendTitle(ChatUtil.chat("&2Wplacanie"), ChatUtil.chat("&aPodaj kwote na chacie"), 20, 100, 20);
                p.closeInventory();
            }
        }
    }
}
