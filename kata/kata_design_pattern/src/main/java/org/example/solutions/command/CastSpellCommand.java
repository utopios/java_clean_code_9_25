package org.example.solutions.command;

import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;
import org.example.solutions.strategy.PlayerClassStrategy;

public class CastSpellCommand implements GameCommand {
    private final PlayerClassStrategy playerStrategy;
    private final GameContext context;

    public CastSpellCommand(PlayerClassStrategy playerStrategy, GameContext context) {
        this.playerStrategy = playerStrategy;
        this.context = context;
    }

    @Override
    public CombatResult execute() {
        return playerStrategy.executeAction("CAST_SPELL", context);
    }
}
