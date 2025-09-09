package org.example.solutions.factory;


import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;

public class FireballSpell implements Spell {
    @Override
    public CombatResult cast(GameContext context, CombatResult result) {
        int damage = 40 + (context.getPlayerLevel() * 5);
        result.setManaChange(-30);
        result.addMessage("FIREBALL!");

        if ("PLANT_MONSTER".equals(context.getTargetType()) ||
                "TREE_SPIRIT".equals(context.getTargetType())) {
            damage *= 2;
            result.addMessage("Fire is super effective against plant creatures!");
        }

        result.setDamage(damage);
        return result;
    }
}
