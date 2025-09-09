package org.example.solutions;


import org.example.solutions.command.GameCommand;
import org.example.solutions.factory.CommandFactory;
import org.example.solutions.factory.PlayerClassFactory;
import org.example.solutions.strategy.PlayerClassStrategy;
import java.util.List;
class GameEngine {
    private final PlayerClassFactory classFactory;
    private final CommandFactory commandFactory;

    public GameEngine() {
        this.classFactory = new PlayerClassFactory();
        this.commandFactory = new CommandFactory();
    }

    public void executePlayerAction(String playerClassName, int playerLevel, String actionType,
                                    String targetType, int targetLevel, String environment,
                                    List<String> playerItems, String weather) {

        GameContext context = new GameContext(environment, weather, playerLevel,
                targetLevel, targetType, playerItems);

        PlayerClassStrategy strategy = classFactory.createPlayerClass(playerClassName);
        GameCommand command = commandFactory.createCommand(actionType, strategy, context);

        CombatResult result = command.execute();

        displayResults(result, context, playerClassName);
    }

    private void displayResults(CombatResult result, GameContext context, String playerClassName) {
        System.out.println("\n=== COMBAT RESULT ===");
        System.out.println("Player: " + playerClassName + " (Level " + context.getPlayerLevel() + ")");
        System.out.println("Target: " + context.getTargetType() + " (Level " + context.getTargetLevel() + ")");
        System.out.println("Environment: " + context.getEnvironment() + " (" + context.getWeather() + ")");
        System.out.println("Damage dealt: " + result.getDamage());
        System.out.println("Health change: " + result.getHealthChange());
        System.out.println("Mana change: " + result.getManaChange());
        System.out.println("Critical hit: " + result.isCriticalHit());

        System.out.println("\nCombat Messages:");
        for (String message : result.getMessages()) {
            System.out.println("- " + message);
        }

        System.out.println("Combat round completed!");
    }
}
