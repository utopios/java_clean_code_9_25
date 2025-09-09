package org.example.solutions.strategy;


import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;

public interface PlayerClassStrategy {
    CombatResult executeAction(String actionType, GameContext context);
}
