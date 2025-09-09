package org.example.solutions.factory;


import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;

public interface Spell {
    CombatResult cast(GameContext context, CombatResult result);
}
