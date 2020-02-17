package me.b1vth420.marsNapady.Listeners;

import me.b1vth420.marsNapady.Data.Config;
import me.b1vth420.marsNapady.Gui.NpcGui;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcClickListener implements Listener {
    @EventHandler
    public void onClick(NPCRightClickEvent e) {
        NPC npc = e.getNPC();
        if (Config.getInst().npc.contains(npc.getName())) {
            NpcGui.npcGui(e.getClicker(), npc.getName());
        }
    }
}
