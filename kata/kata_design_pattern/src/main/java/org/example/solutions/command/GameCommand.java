package org.example.solutions.command;

import org.example.solutions.CombatResult;

public interface GameCommand {
    CombatResult execute();
}