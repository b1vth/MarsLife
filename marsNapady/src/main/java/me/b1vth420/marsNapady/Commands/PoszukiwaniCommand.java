package me.b1vth420.marsNapady.Commands;

import me.b1vth420.marsApi.Commands.Command;
import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class PoszukiwaniCommand extends Command {
    public PoszukiwaniCommand() {
        super("poszukiwani", "Lista poszukiwanych", "marsNapady.poszukiwani", "/poszukiwani", true, 0, new String[0]);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatUtil.chat("&1Dlugi: "));
        UserManager.getUsersWithDebt().forEach(u -> sender.sendMessage("&9" + u.getName() + " " + u.getCreditSize()));
    }
}
