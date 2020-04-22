package me.b1vth420.marsChoroby.Managers;

import me.b1vth420.marsChoroby.Objects.Disease;

import java.util.concurrent.ConcurrentHashMap;

public class DiseaseManager {

    private static ConcurrentHashMap<String, Disease> diseases = new ConcurrentHashMap();

    public static ConcurrentHashMap<String, Disease> getDiseases() {
        return new ConcurrentHashMap<>(diseases);
    }

    public static void addDisease(String name,Disease d) {
        if (!diseases.contains(d)) diseases.put(name, d);
    }

    public static void removeDisease(String d) {
        if (diseases.containsKey(d)) diseases.remove(d);
    }
}
