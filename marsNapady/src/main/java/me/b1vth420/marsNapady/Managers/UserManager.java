package me.b1vth420.marsNapady.Managers;

import me.b1vth420.marsNapady.Objects.MarsUser;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    private static ConcurrentHashMap<String, MarsUser> users = new ConcurrentHashMap<String, MarsUser>();

    public static ConcurrentHashMap<String, MarsUser> getUsers() {
        return new ConcurrentHashMap<>(users);
    }

    public static void addUser(MarsUser u) {
        if (!users.containsValue(u)) users.put(u.getName(), u);
    }

    public static void removeUser(MarsUser u) {
        if (users.containsValue(u)) users.remove(u);
    }

    public static MarsUser getUser(Player p) {
        for (MarsUser u : users.values()) {
            if (u.getUuid().equals(p.getUniqueId())) return u;
        }
        return new MarsUser(p.getName(), p.getUniqueId(), 0, false, 0, 0L);
    }

    public static List<MarsUser> getUsersWithDebt() {
        List<MarsUser> toReturn = new ArrayList<>();
        for (MarsUser u : users.values()) {
            if (u.isCredit()) toReturn.add(u);
        }
        return toReturn;
    }
}
