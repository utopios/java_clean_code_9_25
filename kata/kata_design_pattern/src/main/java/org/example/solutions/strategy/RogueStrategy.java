package org.example.solutions.strategy;

import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;

public class RogueStrategy implements PlayerClassStrategy {
    @Override
    public CombatResult executeAction(String actionType, GameContext context) {
        CombatResult result = new CombatResult();

        if ("ATTACK".equals(actionType)) {
            int damage = 35 + (context.getPlayerLevel() * 2);

            if (Math.random() > 0.6) {
                damage = (int)(damage * 1.8);
                result.addMessage("Sneak attack from shadows!");
            }

            if (context.getPlayerItems().contains("POISON_DAGGERS")) {
                damage += 12;
                result.addMessage("Poison courses through enemy veins!");
            } else if (context.getPlayerItems().contains("SHADOW_BLADES")) {
                damage += 18;
                if ("CAVE".equals(context.getEnvironment()) || "DUNGEON".equals(context.getEnvironment())) {
                    damage += 10;
                    result.addMessage("Darkness empowers shadow blades!");
                }
            }

            result.setDamage(damage);
        } else if ("CAST_SPELL".equals(actionType)) {
            if (context.getPlayerItems().contains("SMOKE_BOMB")) {
                result.addMessage("Smoke bomb creates diversion!");
                if (Math.random() > 0.5) {
                    int damage = (35 + (context.getPlayerLevel() * 2)) * 2;
                    result.setDamage(damage);
                    result.addMessage("Surprise attack through smoke!");
                } else {
                    result.addMessage("Attack missed in the smoke!");
                }
            } else {
                result.addMessage("Rogues need special items to use magic!");
            }
        }

        return result;
    }
}
