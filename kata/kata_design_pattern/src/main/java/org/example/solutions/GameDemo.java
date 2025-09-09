package org.example.solutions;


import java.util.Arrays;
import java.util.List;
class GameDemo {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();

        List<String> mageItems = Arrays.asList("STAFF_OF_FIRE", "FIREBALL_SCROLL", "LIGHTNING_SCROLL");

        System.out.println("=== COMBAT MAGE VS DRAGON ===");
        engine.executePlayerAction("MAGE", 15, "CAST_SPELL", "DRAGON", 20,
                "MOUNTAIN", mageItems, "STORM");

        System.out.println("\n" + "=".repeat(60) + "\n");

        List<String> warriorItems = Arrays.asList("LEGENDARY_SWORD", "RAGE_POTION");

        System.out.println("=== COMBAT WARRIOR VS SKELETON ===");
        engine.executePlayerAction("WARRIOR", 12, "ATTACK", "SKELETON", 10,
                "DUNGEON", warriorItems, "CLEAR");

        System.out.println("\n" + "=".repeat(60) + "\n");

        List<String> rogueItems = Arrays.asList("SHADOW_BLADES", "SMOKE_BOMB");

        System.out.println("=== COMBAT ROGUE VS GHOST ===");
        engine.executePlayerAction("ROGUE", 10, "ATTACK", "GHOST", 8,
                "CAVE", rogueItems, "CLEAR");
    }
}
