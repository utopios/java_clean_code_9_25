package org.example.solutions.factory;

import java.util.List;
public class SpellFactory {
    public Spell createSpell(List<String> items) {
        if (items.contains("FIREBALL_SCROLL")) {
            return new FireballSpell();
        } else if (items.contains("LIGHTNING_SCROLL")) {
            return new LightningBoltSpell();
        } else if (items.contains("HEAL_SCROLL")) {
            return new HealSpell();
        }
        return null;
    }
}
