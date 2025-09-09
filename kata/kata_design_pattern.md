## Kata : Refactoring d'un Moteur de Jeu Vidéo

**Mission :** Refactorer ce code d'un moteur de jeu RPG en appliquant les design patterns.**Objectifs de refactoring :**

### **Niveau 1 - Patterns essentiels**
1. **Strategy Pattern** : Systèmes de combat par classe (Warrior, Mage, Rogue)
2. **Factory Pattern** : Création des sorts, objets, et effets
3. **Command Pattern** : Actions du joueur (Attack, CastSpell, UseItem)

### **Niveau 2 - Système de jeu**
4. **Observer Pattern** : Événements de combat (critique, niveau up, effets)
5. **Chain of Responsibility** : Calcul des dégâts (arme → critique → environnement → résistance)
6. **State Pattern** : États du joueur (Normal, Rage, Furtif, Empoisonné)

### **Niveau 3 - Architecture avancée**
7. **Visitor Pattern** : Effets d'environnement sur différents types d'actions
8. **Composite Pattern** : Objets composés (sorts combinés, armes enchantées)
9. **Memento Pattern** : Sauvegarde/restauration d'état de combat