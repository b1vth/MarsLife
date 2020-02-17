package me.b1vth420.marsNapady.Managers;

import me.b1vth420.marsNapady.Objects.User;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    private static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<String, User>();

    public static ConcurrentHashMap<String, User> getUsers() {
        return new ConcurrentHashMap<String, User>(users);
    }

    public static void addUser(User u) {
        if (!users.containsValue(u)) users.put(u.getName(), u);
    }

    public static void removeUser(User u) {
        if (users.containsValue(u)) users.remove(u);
    }

    public static User getUser(Player p) {
        for (User u : users.values()) {
            if (u.getUuid().equals(p.getUniqueId())) return u;
        }
        return new User(p.getName(), p.getUniqueId(), 0, false, 0);
    }
}
