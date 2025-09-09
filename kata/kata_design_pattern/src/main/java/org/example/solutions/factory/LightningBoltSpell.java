package org.example.solutions.factory;

import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;

public class LightningBoltSpell implements Spell {
    @Override
    public CombatResult cast(GameContext context, CombatResult result) {
        int damage = 45 + (context.getPlayerLevel() * 4);
        result.setManaChange(-35);
        result.addMessage("LIGHTNING BOLT!");

        if ("STORM".equals(context.getWeather())) {
            damage = (int)(damage * 1.5);
            result.addMessage("Storm amplifies lightning magic!");
        }

        if ("WATER_ELEMENTAL".equals(context.getTargetType()) ||
                "METAL_GOLEM".equals(context.getTargetType())) {
            damage = (int)(damage * 1.8);
            result.addMessage("Lightning is super effective!");
        }

        result.setDamage(damage);
        return result;
    }
}