package org.example.solutions.factory;

import org.example.solutions.strategy.MageStrategy;
import org.example.solutions.strategy.PlayerClassStrategy;
import org.example.solutions.strategy.RogueStrategy;
import org.example.solutions.strategy.WarriorStrategy;

public class PlayerClassFactory {
    public PlayerClassStrategy createPlayerClass(String className) {
        switch (className.toUpperCase()) {
            case "WARRIOR": return new WarriorStrategy();
            case "MAGE": return new MageStrategy();
            case "ROGUE": return new RogueStrategy();
            default: throw new IllegalArgumentException("Unknown class: " + className);
        }
    }
}
