package me.b1vth420.marsChoroby.Managers;

import me.b1vth420.marsChoroby.Objects.Disease;

import java.util.ArrayList;
import java.util.List;

public class DiseaseManager {

    private static List<Disease> diseases = new ArrayList();

    public static List<Disease> getDiseases() {
        return new ArrayList<>(diseases);
    }

    public static void addDisease(Disease d) {
        if (!diseases.contains(d)) diseases.add(d);
    }

    public static void removeDisease(Disease d) {
        if (diseases.contains(d)) diseases.remove(d);
    }
}
