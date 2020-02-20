package me.b1vth420.marsNapady.Data;

import me.b1vth420.marsApi.Api;
import me.b1vth420.marsNapady.Managers.UserManager;
import me.b1vth420.marsNapady.Objects.User;

import java.util.Map;
import java.util.UUID;

public class MySQL {

    public static void createTable() {
        Api.getInst().getSQLManager().createTable("CREATE TABLE IF NOT EXISTS marsUsers(UUID varchar(36) not null, name VARCHAR(16) not null, bankMoney text not null, isCredit text not null, creditSize text not null, primary key(uuid))");
    }

    public static void saveUsers() {
        for (User u : UserManager.getUsers().values()) {
            Api.getInst().getSQLManager().saveData("INSERT INTO marsUsers(uuid, name, bankMoney, isCredit, creditSize) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?, bankMoney=?, isCredit=?, creditSize=?",
                    new String[]{
                            u.getUuid().toString(),
                            u.getName(),
                            String.valueOf(u.getBankMoney()),
                            String.valueOf(u.isCredit()),
                            String.valueOf(u.getCreditSize()),
                            u.getName(),
                            String.valueOf(u.getBankMoney()),
                            String.valueOf(u.isCredit()),
                            String.valueOf(u.getCreditSize())
                    });
        }
    }

    public static void loadUsers() {
        for (Map result : Api.getInst().getSQLManager().loadData("SELECT * FROM marsUsers")) {
            new User((String) result.get("name"), UUID.fromString(((String) result.get("UUID"))), Double.parseDouble((String) result.get("bankMoney")), Boolean.parseBoolean((String) result.get("isCredit")), Integer.parseInt((String) result.get("creditSize")));
        }
    }
}
