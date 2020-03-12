package me.b1vth420.marsNapady.Listeners;

import me.b1vth420.marsApi.Utils.*;
import me.b1vth420.marsNapady.Data.Config;
import me.b1vth420.marsNapady.Gui.NpcGui;
import me.b1vth420.marsNapady.Main;
import me.b1vth420.marsNapady.Managers.UserManager;
import me.b1vth420.marsNapady.Objects.MarsUser;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        MarsUser u = UserManager.getUser(p);
        ItemStack item = e.getCurrentItem();
        NPC npc = CitizensAPI.getNPCRegistry().getNPC(Targeter.getTargetEntity(p));

        if (e.getCurrentItem() == null && e.getClickedInventory() == null) return;

        if (e.getInventory().getName().equals(ChatUtil.chat("&aBankomat")) || Config.getInst().npc.contains(e.getInventory().getName().replace("§a", ""))) {
            e.setCancelled(true);
            if (ItemUtil.checkItem(e.getCurrentItem(), ItemUtil.BuildItem(Material.WOOL, ChatUtil.chat("&cNapad"), (short) 11))) {

                List<Player> policeList = PlayerUtil.getPlayersWithPermission(Config.getInst().policePermission);
                if (policeList.size() < Config.getInst().policeForHeist) {
                    p.sendMessage(ChatUtil.chat("&4Blad! &cNa serwerze nie ma odpowiedniej ilosci policji!" + Config.getInst().policeForHeist));
                    return;
                }
                if (Config.getInst().heistItems.contains(p.getInventory().getItemInMainHand())) {
                    for (Player police : policeList)
                        police.sendMessage(ChatUtil.chat("&8### &4Uwaga! &cNapad w toku (" + npc.getStoredLocation().getBlockX() + ", " + npc.getStoredLocation().getBlockZ() + ") &###"));

                    p.sendTitle(ChatUtil.chat("&4Napad"), ChatUtil.chat("&cSprzedawca wyjmuje pieniadze z kasy"), 20, 100, 20);
                    p.closeInventory();
                    NpcClickListener.addData(npc, true);
                    NpcClickListener.addNextHeist(npc, DataUtil.parseDateDiff("3h", true));

                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInst(), new Runnable() {
                        @Override
                        public void run() {
                            NpcClickListener.removeData(npc);
                            if (p.getLocation().distance(npc.getStoredLocation()) > 20) {
                                p.sendMessage(ChatUtil.chat("&4Blad! Napad zostal przerwany!"));
                                return;
                            }
                            int money = RandomUtil.getRandInt(10000, 30000);
                            EconomyUtil.addMoney(p, money);
                            NpcClickListener.removeData(npc);
                            p.sendTitle(ChatUtil.chat("&2Napad"), ChatUtil.chat("&aOtrzymales " + money + "$ od sprzedawcy"), 20, 100, 20);
                        }
                    }, Config.getInst().heistTime * 20L);
                }
            } else p.sendMessage(ChatUtil.chat("&4Blad! &cMusisz miec bron!"));

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
                p.sendMessage(ChatUtil.chat("&aWziales pozyczke 2000$"));
                p.closeInventory();
            }
            if (slot == 12) {
                EconomyUtil.addMoney(p, 5000);
                u.setCreditSize(5000);
                u.setCredit(true);
                p.sendMessage(ChatUtil.chat("&aWziales pozyczke 5000$"));
                p.closeInventory();
            }
            if (slot == 14) {
                EconomyUtil.addMoney(p, 10000);
                u.setCreditSize(10000);
                u.setCredit(true);
                p.sendMessage(ChatUtil.chat("&aWziales pozyczke 10000$"));
                p.closeInventory();
            }
            if (slot == 16) {
                EconomyUtil.addMoney(p, 20000);
                u.setCreditSize(20000);
                u.setCredit(true);
                p.sendMessage(ChatUtil.chat("&aWziales pozyczke 20000$"));
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
