package me.b1vth420.marsNapady.Listeners;

import me.b1vth420.marsApi.Utils.ChatUtil;
import me.b1vth420.marsNapady.Data.Config;
import me.b1vth420.marsNapady.Gui.NpcGui;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class NpcClickListener implements Listener {

    private static HashMap<NPC, Boolean> data = new HashMap<>();
    private static HashMap<NPC, Long> nextHeist = new HashMap<>();

    @EventHandler
    public void onClick(NPCRightClickEvent e) {
        NPC npc = e.getNPC();
        if (data.containsKey(npc) && data.get(npc)) {
            e.getClicker().sendMessage(ChatUtil.chat("&4Blad! &cTrwa napad"));
            return;
        }
        if (Config.getInst().npc.contains(npc.getName())) {
            NpcGui.npcGui(e.getClicker(), npc.getName());
        }
    }

    public static void addData(NPC npc, Boolean b) {
        data.put(npc, b);
    }

    public static void removeData(NPC npc) {
        data.remove(npc);
    }

    public static HashMap<NPC, Boolean> getData() {
        return new HashMap<>(data);
    }

    public static HashMap<NPC, Long> getNextHeist() {
        return new HashMap<>(nextHeist);
    }

    public static void addNextHeist(NPC npc, Long b) {
        nextHeist.put(npc, b);
    }

    public static void removeNextHeist(NPC npc) {
        nextHeist.remove(npc);
    }
}
