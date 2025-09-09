package org.example.solutions.strategy;


import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;

public class WarriorStrategy implements PlayerClassStrategy {
    @Override
    public CombatResult executeAction(String actionType, GameContext context) {
        CombatResult result = new CombatResult();

        if ("ATTACK".equals(actionType)) {
            int damage = 50 + (context.getPlayerLevel() * 3);

            if (context.getPlayerItems().contains("LEGENDARY_SWORD")) {
                damage += 25;
                result.addMessage("Legendary sword glows with power!");
            } else if (context.getPlayerItems().contains("MAGIC_AXE")) {
                damage += 15;
                if (Math.random() > 0.7) {
                    damage = (int)(damage * 1.5);
                    result.addMessage("Magic axe unleashes lightning!");
                }
            } else if (context.getPlayerItems().contains("RUSTY_BLADE")) {
                damage += 5;
                if (Math.random() > 0.9) {
                    result.addMessage("Rusty blade breaks apart!");
                    damage = 1;
                }
            }

            if (Math.random() > 0.8) {
                damage *= 2;
                result.setCriticalHit(true);
                result.addMessage("CRITICAL HIT!");
            }

            result.setDamage(damage);
        } else if ("CAST_SPELL".equals(actionType)) {
            if (context.getPlayerItems().contains("RAGE_POTION")) {
                int damage = (50 + (context.getPlayerLevel() * 3)) * 2;
                result.setDamage(damage);
                result.setHealthChange(-10);
                result.addMessage("BERSERKER RAGE!");
            } else {
                result.addMessage("Warriors cannot cast spells without potions!");
            }
        }

        return result;
    }
}
