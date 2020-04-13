package me.b1vth420.marsNapady.Data;

import me.b1vth420.marsApi.Utils.ItemUtil;
import me.b1vth420.marsNapady.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Config {

    private static Config inst;
    private FileConfiguration cfg = Main.getInst().getConfig();

    private Config() {
        inst = this;
    }

    public Set<String> npc = new HashSet<>();
    public Set<Location> bankomat = new HashSet<>();
    public int heistTime;
    public String policePermission;
    public List<ItemStack> heistItems;
    public int policeForHeist;
    public String oneHeistPer;

    public void load() {
        for (String s : cfg.getStringList("npc")) {
            if (s != null && !s.isEmpty()) npc.add(s);
        }
        for (String s : cfg.getStringList("bank")) {
            String ss[] = s.split(" ");
            if (s != null && !s.isEmpty())
                bankomat.add(new Location(Bukkit.getWorld(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]), Integer.parseInt(ss[3])));
        }
        this.heistTime = cfg.getInt("heistTime");
        this.policePermission = cfg.getString("policePermission");
        this.heistItems = ItemUtil.parseItems(cfg.getStringList("heistItems"));
        this.policeForHeist = cfg.getInt("policeForHeist");
        this.oneHeistPer = cfg.getString("oneHeistPer");
    }

    public void save() {
        File f = new File(Main.getInst().getDataFolder(), "config.yml");
        Set<String> bankomatString = new HashSet<>();
        cfg.set("heistTime", this.heistTime);
        cfg.set("policePermission", this.policePermission);
        cfg.set("npc", npc.toArray());
        ;
        for (Location s : bankomat) {
            bankomatString.add(s.getWorld().getName() + " " + s.getBlockX() + " " + s.getBlockY() + " " + s.getBlockZ());
        }
        cfg.set("bank", bankomatString.toArray());
        List<String> heistItems = new ArrayList<>();
        for (ItemStack is : this.heistItems) {
            if (is.getItemMeta().hasDisplayName())
                heistItems.add(is.getType().toString() + " " + is.getAmount() + " " + is.getDurability() + " " + is.getItemMeta().getDisplayName().replace("§", "&").replace(" ", "_"));
            else heistItems.add(is.getType().toString() + " " + is.getAmount() + " " + is.getDurability() + " BRAK");
        }
        cfg.set("heistItems", heistItems);
        cfg.set("policeForHeist", policeForHeist);
        cfg.set("oneHeistPer", oneHeistPer);
        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Config getInst() {
        if (inst == null) {
            return new Config();
        }
        return inst;
    }
}
