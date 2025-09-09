package org.example.solutions.strategy;

import org.example.solutions.CombatResult;
import org.example.solutions.GameContext;
import org.example.solutions.factory.Spell;
import org.example.solutions.factory.SpellFactory;

public class MageStrategy implements PlayerClassStrategy {
    private final SpellFactory spellFactory;

    public MageStrategy() {
        this.spellFactory = new SpellFactory();
    }

    @Override
    public CombatResult executeAction(String actionType, GameContext context) {
        CombatResult result = new CombatResult();

        if ("ATTACK".equals(actionType)) {
            int damage = 30 + (context.getPlayerLevel() * 4);
            result.setManaChange(-25);

            if (context.getPlayerItems().contains("STAFF_OF_FIRE")) {
                damage += 20;
                result.addMessage("Flames engulf the enemy!");
                if ("FOREST".equals(context.getEnvironment())) {
                    damage += 10;
                    result.addMessage("Forest catches fire - extra damage!");
                }
            } else if (context.getPlayerItems().contains("ICE_WAND")) {
                damage += 15;
                result.addMessage("Ice crystals form around target!");
                if ("SNOW".equals(context.getWeather())) {
                    damage += 8;
                    result.addMessage("Blizzard amplifies ice magic!");
                }
            }

            if (result.getManaChange() < 0 && damage == 30 + (context.getPlayerLevel() * 4)) {
                result.addMessage("Not enough mana!");
                return result;
            }

            result.setDamage(damage);
        } else if ("CAST_SPELL".equals(actionType)) {
            Spell spell = spellFactory.createSpell(context.getPlayerItems());

            if (spell != null) {
                return spell.cast(context, result);
            } else {
                result.addMessage("No suitable spell found!");
            }
        }

        return result;
    }
}

