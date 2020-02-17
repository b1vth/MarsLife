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
                        int money = RandomUtil.getRandInt(20000, 10000);
                        EconomyUtil.addMoney(p, money);
                        p.sendTitle(ChatUtil.chat("&2Napad"), ChatUtil.chat("&aOtrzymales " + money + " od sprzedawcy"), 20, 100, 20);
                    }
                }, Config.getInst().heistTime * 20L);
            }

            if (ItemUtil.checkItem(e.getCurrentItem(), ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&aWplac"), (short) 5))) {
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

        if (e.getInventory().getName().equals(ChatUtil.chat("&aPozyczka"))) {
            e.setCancelled(true);
            if (u.isCredit()) {
                p.sendMessage(ChatUtil.chat("&4Blad! &cMasz juz jedna pozyczke"));
                return;
            }
            if (e.getSlot() == 9) {
                EconomyUtil.addMoney(p, 2000);
                u.setCredit(true);
            }
            if (e.getSlot() == 11) {
                EconomyUtil.addMoney(p, 5000);
                u.setCredit(true);
            }
            if (e.getSlot() == 13) {
                EconomyUtil.addMoney(p, 10000);
                u.setCredit(true);
            }
            if (e.getSlot() == 15) {
                EconomyUtil.addMoney(p, 20000);
                u.setCredit(true);
            }
            if (e.getSlot() == 26) {
                if (EconomyUtil.getBalance(p) >= u.getCreditSize()) {
                    EconomyUtil.removeMoney(p, u.getCreditSize());
                } else {
                    p.sendMessage(ChatUtil.chat("&4Blad! &cNie masz przy sobie wystarczajacej kwoty!"));
                }
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
