package org.example;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();

        List<String> items = List.of("LEGENDARY_SWORD", "FIREBALL_SCROLL", "RAGE_POTION");

        // Combat épique !
        engine.executePlayerAction("MAGE", 15, "CAST_SPELL", "DRAGON", 20,
                "MOUNTAIN", items, "STORM");

        System.out.println("\n" + "=".repeat(60) + "\n");

        // Autre combat
        engine.executePlayerAction("ROGUE", 12, "ATTACK", "GHOST", 8,
                "DUNGEON", List.of("SHADOW_BLADES", "SMOKE_BOMB"), "CLEAR");
    }
}