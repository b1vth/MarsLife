package me.b1vth420.marsNapady.Commands;

import me.b1vth420.marsApi.Commands.Command;
import me.b1vth420.marsApi.Utils.Targeter;
import me.b1vth420.marsNapady.Data.Config;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.npc.skin.SkinnableEntity;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class BankCommand extends Command {
    public BankCommand() {
        super("bank", "/bank", "marsNapady.bank", "/bank <npc/bankomat> <create/remove> <nazwa>", true, 3, new String[0]);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("npc")) {
            if (args[1].equalsIgnoreCase("create")) {
                NPCRegistry registry = CitizensAPI.getNPCRegistry();
                NPC npc = registry.createNPC(EntityType.PLAYER, args[2]);
                npc.spawn(p.getLocation());
                Config.getInst().npc.add(args[2]); //TODO poprawic
            }
            if (args[1].equalsIgnoreCase("remove")) {
                NPCRegistry registry = CitizensAPI.getNPCRegistry();
                NPC npc = registry.getNPC(Targeter.getTargetEntity(p));
                Config.getInst().npc.remove(npc.getName());
                npc.destroy();
            }
            if (args[1].equalsIgnoreCase("skin")) {
                NPCRegistry registry = CitizensAPI.getNPCRegistry();
                NPC npc = registry.getNPC(Targeter.getTargetEntity(p));
                String skinName;
                npc.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, args[2]);
                npc.data().setPersistent(NPC.PLAYER_SKIN_USE_LATEST, false);
                skinName = args[2];
                if (npc.isSpawned()) {
                    SkinnableEntity skinnable = npc.getEntity() instanceof SkinnableEntity ? (SkinnableEntity) npc.getEntity()
                            : null;
                    if (skinnable != null) {
                        skinnable.setSkinName(skinName, true);
                    }
                }
            }
        }


        if (args[0].equalsIgnoreCase("bankomat")) {
            if (args[1].equalsIgnoreCase("create")) {
                Block b = p.getTargetBlock(null, 100);

            }
            if (args[1].equalsIgnoreCase("remove")) {

            }
        }
    }
}
