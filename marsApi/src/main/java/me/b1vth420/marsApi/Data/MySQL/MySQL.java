package me.b1vth420.marsApi.Data.MySQL;

import me.b1vth420.marsApi.Api;
import me.b1vth420.marsApi.Managers.UserManager;
import me.b1vth420.marsApi.Objects.MarsUser;
import me.b1vth420.marsChoroby.Managers.DiseaseManager;
import me.b1vth420.marsChoroby.Objects.Disease;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MySQL {
    public static void saveUsers() {
        for (MarsUser u : UserManager.getUsers().values()) {

            StringBuilder builder = new StringBuilder();
            for(Map.Entry<Disease, Long> disease : u.getDiseasesMap().entrySet()) {
                builder.append(disease.getKey().getName() +  "-" + disease.getValue() + ";");
            }

            Api.getInst().getSQLManager().saveData("INSERT INTO marsUsers(uuid, name, bankMoney, isCredit, creditSize, creditTime, diseases) VALUES (?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?, bankMoney=?, isCredit=?, creditSize=?, creditTime=?, diseases=?",
                    new String[]{
                            u.getUuid().toString(),
                            u.getName(),
                            String.valueOf(u.getBankMoney()),
                            String.valueOf(u.isCredit()),
                            String.valueOf(u.getCreditSize()),
                            String.valueOf(u.getCreditTime()),
                            builder.toString(),
                            u.getName(),
                            String.valueOf(u.getBankMoney()),
                            String.valueOf(u.isCredit()),
                            String.valueOf(u.getCreditSize()),
                            String.valueOf(u.getCreditTime()),
                            builder.toString()
                    });
        }
    }

    public static void loadUsers() {
        for (Map result : Api.getInst().getSQLManager().loadData("SELECT * FROM marsUsers")) {
            HashMap<Disease, Long> disease = new HashMap<>();
            for(String ss : ((String) result.get("diseases")).split(";")) {
                disease.put(DiseaseManager.getDisease(ss.split("-")[0]), Long.parseLong(ss.split("-")[1]));
            }
            new MarsUser((String) result.get("name"), UUID.fromString(((String) result.get("UUID"))), Double.parseDouble((String) result.get("bankMoney")), Boolean.parseBoolean((String) result.get("isCredit")), Integer.parseInt((String) result.get("creditSize")), Long.parseLong((String) result.get("creditTime")), disease);
        }
    }
}
