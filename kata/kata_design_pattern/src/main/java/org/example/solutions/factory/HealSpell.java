package org.example.solutions.factory;


import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;

public class HealSpell implements Spell {
    @Override
    public CombatResult cast(GameContext context, CombatResult result) {
        int healing = 50 + (context.getPlayerLevel() * 2);
        result.setHealthChange(healing);
        result.setManaChange(-20);
        result.addMessage("HEAL!");
        return result;
    }
}
