package org.example;
import java.util.List;
public class GameEngine {

    public void executePlayerAction(String playerClass, int playerLevel, String actionType,
                                    String targetType, int targetLevel, String environment,
                                    List<String> playerItems, String weather) {

        int damage = 0;
        int mana = 100;
        int health = 200;
        boolean criticalHit = false;

        // Traitement selon la classe du joueur
        if (actionType.equals("ATTACK")) {

            if (playerClass.equals("WARRIOR")) {
                damage = 50 + (playerLevel * 3);

                // Effets des armes
                if (playerItems.contains("LEGENDARY_SWORD")) {
                    damage += 25;
                    System.out.println("⚔️ Legendary sword glows with power!");
                } else if (playerItems.contains("MAGIC_AXE")) {
                    damage += 15;
                    if (Math.random() > 0.7) {
                        damage *= 1.5;
                        System.out.println("⚡ Magic axe unleashes lightning!");
                    }
                } else if (playerItems.contains("RUSTY_BLADE")) {
                    damage += 5;
                    if (Math.random() > 0.9) {
                        System.out.println("💥 Rusty blade breaks apart!");
                        damage = 1;
                    }
                }

                // Effets critique
                if (Math.random() > 0.8) {
                    damage *= 2;
                    criticalHit = true;
                    System.out.println("💥 CRITICAL HIT!");
                }

            } else if (playerClass.equals("MAGE")) {
                damage = 30 + (playerLevel * 4);
                mana -= 25;

                // Effets des bâtons magiques
                if (playerItems.contains("STAFF_OF_FIRE")) {
                    damage += 20;
                    System.out.println("🔥 Flames engulf the enemy!");
                    if (environment.equals("FOREST")) {
                        damage += 10; // Bonus feu en forêt
                        System.out.println("🔥 Forest catches fire - extra damage!");
                    }
                } else if (playerItems.contains("ICE_WAND")) {
                    damage += 15;
                    System.out.println("❄️ Ice crystals form around target!");
                    if (weather.equals("SNOW")) {
                        damage += 8;
                        System.out.println("❄️ Blizzard amplifies ice magic!");
                    }
                }

                if (mana < 0) {
                    System.out.println("💙 Not enough mana!");
                    return;
                }

            } else if (playerClass.equals("ROGUE")) {
                damage = 35 + (playerLevel * 2);

                // Attaque furtive
                if (Math.random() > 0.6) {
                    damage *= 1.8;
                    System.out.println("🗡️ Sneak attack from shadows!");
                }

                // Effets des dagues
                if (playerItems.contains("POISON_DAGGERS")) {
                    damage += 12;
                    System.out.println("☠️ Poison courses through enemy veins!");
                } else if (playerItems.contains("SHADOW_BLADES")) {
                    damage += 18;
                    if (environment.equals("CAVE") || environment.equals("DUNGEON")) {
                        damage += 10;
                        System.out.println("🌑 Darkness empowers shadow blades!");
                    }
                }
            }

            // Modificateurs d'environnement
            if (environment.equals("SWAMP")) {
                damage = (int)(damage * 0.8); // Terrain difficile
                System.out.println("🐸 Swamp terrain slows movement - damage reduced");
            } else if (environment.equals("MOUNTAIN")) {
                damage = (int)(damage * 1.1); // Terrain élevé
                System.out.println("⛰️ High ground advantage - damage increased");
            } else if (environment.equals("DESERT")) {
                if (weather.equals("SANDSTORM")) {
                    damage = (int)(damage * 0.6);
                    System.out.println("🌪️ Sandstorm blinds attacker - damage reduced");
                }
            }

        } else if (actionType.equals("CAST_SPELL")) {

            if (playerClass.equals("MAGE")) {

                if (playerItems.contains("FIREBALL_SCROLL")) {
                    damage = 40 + (playerLevel * 5);
                    mana -= 30;
                    System.out.println("🔥 FIREBALL!");

                    if (targetType.equals("PLANT_MONSTER") || targetType.equals("TREE_SPIRIT")) {
                        damage *= 2;
                        System.out.println("🔥 Fire is super effective against plant creatures!");
                    }

                } else if (playerItems.contains("LIGHTNING_SCROLL")) {
                    damage = 45 + (playerLevel * 4);
                    mana -= 35;
                    System.out.println("⚡ LIGHTNING BOLT!");

                    if (weather.equals("STORM")) {
                        damage *= 1.5;
                        System.out.println("⛈️ Storm amplifies lightning magic!");
                    }

                    if (targetType.equals("WATER_ELEMENTAL") || targetType.equals("METAL_GOLEM")) {
                        damage *= 1.8;
                        System.out.println("⚡ Lightning is super effective!");
                    }

                } else if (playerItems.contains("HEAL_SCROLL")) {
                    health += 50 + (playerLevel * 2);
                    mana -= 20;
                    System.out.println("✨ HEAL!");
                    damage = 0; // Pas de dégâts pour un sort de soin
                }

            } else if (playerClass.equals("WARRIOR")) {

                if (playerItems.contains("RAGE_POTION")) {
                    damage = (50 + (playerLevel * 3)) * 2; // Double dégâts
                    health -= 10; // Coût en vie
                    System.out.println("😡 BERSERKER RAGE!");

                } else {
                    System.out.println("Warriors cannot cast spells without potions!");
                    return;
                }

            } else if (playerClass.equals("ROGUE")) {

                if (playerItems.contains("SMOKE_BOMB")) {
                    System.out.println("💨 Smoke bomb creates diversion!");
                    if (Math.random() > 0.5) {
                        damage = (35 + (playerLevel * 2)) * 2; // Attaque surprise
                        System.out.println("🗡️ Surprise attack through smoke!");
                    } else {
                        damage = 0;
                        System.out.println("💨 Attack missed in the smoke!");
                    }
                } else {
                    System.out.println("Rogues need special items to use magic!");
                    return;
                }
            }
        }

        // Application des dégâts selon le type d'ennemi
        if (targetType.equals("DRAGON")) {
            damage = (int)(damage * 0.7); // Dragons résistants
            System.out.println("🐉 Dragon's thick scales reduce damage!");
        } else if (targetType.equals("SKELETON")) {
            if (playerClass.equals("MAGE")) {
                damage = (int)(damage * 0.5); // Résistance magique
                System.out.println("💀 Skeleton resists magic!");
            }
        } else if (targetType.equals("GHOST")) {
            if (!playerClass.equals("MAGE")) {
                damage = (int)(damage * 0.3); // Attaques physiques inefficaces
                System.out.println("👻 Physical attacks pass through ghost!");
            }
        }

        // Calcul final avec niveau de l'ennemi
        if (targetLevel > playerLevel) {
            damage = (int)(damage * 0.9);
            System.out.println("⬆️ Enemy level advantage - damage reduced");
        } else if (playerLevel > targetLevel + 2) {
            damage = (int)(damage * 1.2);
            System.out.println("⬇️ Player level advantage - damage increased");
        }

        // Affichage des résultats
        System.out.println("\n=== COMBAT RESULT ===");
        System.out.println("Player: " + playerClass + " (Level " + playerLevel + ")");
        System.out.println("Target: " + targetType + " (Level " + targetLevel + ")");
        System.out.println("Environment: " + environment + " (" + weather + ")");
        System.out.println("Action: " + actionType);
        System.out.println("Damage dealt: " + damage);
        System.out.println("Critical hit: " + criticalHit);
        System.out.println("Player health: " + health);
        System.out.println("Player mana: " + mana);

        // Effets post-combat
        if (damage > 100) {
            System.out.println("🏆 DEVASTATING BLOW! Bonus XP gained!");
        }

        if (criticalHit && playerClass.equals("ROGUE")) {
            System.out.println("💎 Critical hit grants stealth bonus for next turn!");
        }

        if (mana < 20) {
            System.out.println("💙 Low mana warning - consider using mana potion!");
        }

        System.out.println("Combat round completed!");
    }
}

// Classe de test
class GameDemo {

}
