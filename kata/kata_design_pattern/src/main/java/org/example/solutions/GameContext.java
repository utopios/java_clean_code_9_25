package org.example.solutions;

import java.util.ArrayList;
import  java.util.List;
public class GameContext {
    private final String environment;
    private final String weather;
    private final int playerLevel;
    private final int targetLevel;
    private final String targetType;
    private final List<String> playerItems;

    public GameContext(String environment, String weather, int playerLevel,
                       int targetLevel, String targetType, List<String> playerItems) {
        this.environment = environment;
        this.weather = weather;
        this.playerLevel = playerLevel;
        this.targetLevel = targetLevel;
        this.targetType = targetType;
        this.playerItems = new ArrayList<>(playerItems);
    }

    public String getEnvironment() { return environment; }
    public String getWeather() { return weather; }
    public int getPlayerLevel() { return playerLevel; }
    public int getTargetLevel() { return targetLevel; }
    public String getTargetType() { return targetType; }
    public List<String> getPlayerItems() { return playerItems; }
}
