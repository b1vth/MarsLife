package me.b1vth420.marsChoroby.Utils;

import me.b1vth420.marsChoroby.Main;
import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class DiseasesLoader {

    public static void loadDiseases() {
        FileConfiguration yml = Main.getInst().getConfig();

        ConfigurationSection cs = yml.getConfigurationSection("diseases");
        for(String s : cs.getKeys(false)) {
            List<PotionEffect> effectList = new ArrayList<>();
            ConfigurationSection cs1 = cs.getConfigurationSection(s);
            String name = s;
            String visibleName = cs1.getString("name");
            double chance = cs1.getDouble("chance");
            String cause = cs1.getString("cause");
            boolean isContagious = cs1.getBoolean("contagious.isContagious");
            double contagiousChance = cs1.getDouble("contagious.chance");
            int contagiousMinDistance = cs1.getInt("contagious.minDistance");
            String message = cs1.getString("message");
            boolean isBreak = cs1.getBoolean("break.isBreak");
            int fallDistance = cs1.getInt("break.fallDistance");
            for(String sx : cs1.getStringList("effects")) {
                String[] ss = sx.split(" ");
                effectList.add(new PotionEffect(PotionEffectType.getByName(ss[0]), Integer.MAX_VALUE, Integer.parseInt(ss[1])-1));
            }
            String cureName = cs1.getString("cure.name");
            Material cureMaterial = Material.getMaterial(cs1.getString("cure.material"));
            List<String> cureLore = cs1.getStringList("cure.lore");

            new Disease(name, visibleName, chance, cause, isContagious, contagiousChance, contagiousMinDistance, message, isBreak, fallDistance, effectList, cureName, cureMaterial, cureLore);
        }
    }
}
