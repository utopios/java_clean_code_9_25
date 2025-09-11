## Kata 1 : Gestion des Commandes

**Description :**

Vous disposez d'une classe `OrderManager` qui gère les commandes clients. Le code actuel est difficile à maintenir en raison de sa complexité et de sa mauvaise organisation.

**Code de Départ :**

```java
public class OrderManager {
    private StockService stockService;
    private TaxService taxService;
    private NotificationService notificationService;

    public OrderManager(StockService stockService, TaxService taxService, NotificationService notificationService) {
        this.stockService = stockService;
        this.taxService = taxService;
        this.notificationService = notificationService;
    }

    public void processOrder(Order order) throws InvalidOrderException, InsufficientStockException {
        double subtotal = 0;
        validateOrder(order);
        /*double total = calculateTotal(order);
        checkStock(order);
        updateStock(order);*/
        for(Item item: order.getItems()) {
            int stock = stockService.getStockForItem(item); 
            if (stock < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuffisant pour l'article : " + item.getName());
            }
            subtotal += item.getPrice() * item.getQuantity();
            double taxes = taxService.calculateTax(subtotal);
            subtotal += taxes;
            stockService.updateStock(item, item.getQuantity());            
        }
        notificationService.sendOrderConfirmation(order, total);
    }

    private void validateOrder(Order order) throws InvalidOrderException  {
            if(order == null || order.getItems() == null || order.getItems().isEmpty()) {
                throw new InvalidOrderException("La commande est invalide");
            }
    }

    private double calculateTotal(Order order) {
        double subtotal = 0;
        for (Item item : order.getItems()) {
            subtotal += item.getPrice() * item.getQuantity();
        }
        double taxes = taxService.calculateTax(subtotal);
        return subtotal + taxes;
    }

    private void checkStock(Order order) throws InsufficientStockException {
        for (Item item : order.getItems()) {
            int stock = stockService.getStockForItem(item);
            if (stock < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuffisant pour l'article : " + item.getName());
            }
        }
    }

    private void updateStock(Order order) {
        for (Item item : order.getItems()) {
            stockService.updateStock(item, item.getQuantity());
        }
    }

}

public interface TaxService {
    double calculateTax(double amount);
}

public interface StockService {
    int getStockForItem(Item item);
    void updateStock(Item item, int quantity);
}

public interface NotificationService {
    void sendOrderConfirmation(Order order, double total);
}

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}

public class InvalidOrderException extends Exception {
    public InvalidOrderException(String message) {
        super(message);
    }
}

```


---

## Kata 3 : Calculateur de Prix

**Description :**

Une application calcule le prix de produits en fonction de leur type et applique des remises spécifiques. Le code actuel utilise de nombreux `if` et `switch` et est difficile à maintenir.

**Code de Départ :**

```java
public class PriceCalculator {
    public double calculatePrice(Product product) {
        double price = product.getBasePrice();
        if (product.getType() == ProductType.ELECTRONICS) {
            price += price * 0.15; // Taxe électronique
            if (product.isOnSale()) {
                price -= price * 0.1; // Remise de 10%
            }
        } else if (product.getType() == ProductType.BOOK) {
            price += price * 0.05; // Taxe livre
        } else if (product.getType() == ProductType.CLOTHING) {
            price += price * 0.2; // Taxe vêtements
            if (product.isImported()) {
                price += price * 0.05; // Taxe d'importation
            }
        }
        // Autres types de produits
        return price;
    }
}
```

```java
public abstract class Product {
    protected double basePrice;

    public Product(double basePrice) {
        this.basePrice = basePrice;
    }

    public abstract double calculatePrice();
}

public class ElectronicsProduct extends Product {
    private boolean onSale;

    public ElectronicsProduct(double basePrice, boolean onSale) {
        super(basePrice);
        this.onSale = onSale;
    }

    @Override
    public double calculatePrice() {
        double price = basePrice + basePrice * 0.15; // Taxe électronique
        if (onSale) {
            price -= price * 0.1; // Remise de 10%
        }
        return price;
    }
}


public class BookProduct extends Product {
    public BookProduct(double basePrice) {
        super(basePrice);
    }

    @Override
    public double calculatePrice() {
        return basePrice + basePrice * 0.05; // Taxe livre
    }
}

public class ClothingProduct extends Product {
    private boolean isImported;

    public ClothingProduct(double basePrice, boolean isImported) {
        super(basePrice);
        this.isImported = isImported;
    }

    @Override
    public double calculatePrice() {
        double price = basePrice + basePrice * 0.2; // Taxe vêtements
        if (isImported) {
            price += basePrice * 0.05; // Taxe d'importation
        }
        return price;
    }
}

```

---

## Kata 4 : Gestion des Employés

**Description :**

Le système de gestion des employés doit calculer les salaires en fonction du type d'employé et de leurs heures travaillées. Le code est complexe et contient des duplications.

**Code de Départ :**

```java
public class Payroll {
    public double calculateSalary(Employee employee) {
        double salary = 0;
        if (employee.getRole().equals("Developer")) {
            salary = employee.getHoursWorked() * 50;
            // Bonus pour les développeurs
            if (employee.getYearsOfExperience() > 5) {
                salary += 1000;
            }
        } else if (employee.getRole().equals("Manager")) {
            salary = employee.getHoursWorked() * 60;
            // Bonus pour les managers
            if (employee.getTeamSize() > 10) {
                salary += 2000;
            }
        } else if (employee.getRole().equals("Intern")) {
            salary = employee.getHoursWorked() * 20;
        }
        // Autres rôles
        return salary;
    }
}
```

```java
public abstract class Employee {
    protected int hoursWorked;

    public Employee(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public abstract double calculateSalary();
}

public class Developer extends Employee {
    private int yearsOfExperience;
    private static final double HOURLY_RATE = 50;
    private static final double BONUS = 1000;

    public Developer(int hoursWorked, int yearsOfExperience) {
        super(hoursWorked);
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public double calculateSalary() {
        double salary = hoursWorked * HOURLY_RATE;
        if (yearsOfExperience > 5) {
            salary += BONUS;
        }
        return salary;
    }
}

public class Manager extends Employee {
    private int teamSize;
    private static final double HOURLY_RATE = 60;
    private static final double BONUS = 2000;

    public Manager(int hoursWorked, int teamSize) {
        super(hoursWorked);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateSalary() {
        double salary = hoursWorked * HOURLY_RATE;
        if (teamSize > 10) {
            salary += BONUS;
        }
        return salary;
    }
}

public class Intern extends Employee {
    private static final double HOURLY_RATE = 20;

    public Intern(int hoursWorked) {
        super(hoursWorked);
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * HOURLY_RATE;
    }
}

public class Payroll {
    public double calculateSalary(Employee employee) {
        return employee.calculateSalary();
    }
}

Payroll payroll = new Payroll();
payroll.calculateSalary(new Developer(150, 10));
payroll.calculateSalary(new Manager(150, 10));
```
---

## Kata 5 : Analyse des Données

**Description :**

Une classe `DataAnalyzer` lit des données à partir de différentes sources et les traite. Le code est rempli de duplications et ne suit pas les principes de conception.

**Code de Départ :**

```java
public class DataAnalyzer {
    public void analyzeData(String sourceType) {
        if (sourceType.equals("database")) {
            // Connexion à la base de données
            // Lecture des données
            // Traitement des données
        } else if (sourceType.equals("file")) {
            // Ouverture du fichier
            // Lecture des données
            // Traitement des données
        } else if (sourceType.equals("api")) {
            // Appel de l'API
            // Lecture des données
            // Traitement des données
        } else {
            System.out.println("Type de source inconnu.");
        }
    }
}
```

---

## Kata 6 : Gestion des Transactions Bancaires

**Description :**

Le système bancaire gère les transactions entre comptes. Le code actuel a des problèmes de couplage et de complexité, ce qui rend difficile l'ajout de nouveaux types de transactions.

**Code de Départ :**

```java
public class TransactionManager {
    public void processTransaction(Transaction transaction) {
        if (transaction.getType() == TransactionType.DEPOSIT) {
            Account account = getAccount(transaction.getAccountId());
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else if (transaction.getType() == TransactionType.WITHDRAWAL) {
            Account account = getAccount(transaction.getAccountId());
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else if (transaction.getType() == TransactionType.TRANSFER) {
            Account sourceAccount = getAccount(transaction.getSourceAccountId());
            Account destinationAccount = getAccount(transaction.getDestinationAccountId());
            sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());
        }
        // Enregistrement de la transaction
        saveTransaction(transaction);
    }
}
```

---

## Kata 7 : Système de Réservation

**Description:**

Un système de réservation de salles doit gérer les réservations pour différents types d'événements. Le code contient des structures conditionnelles complexes et est difficile à maintenir.

**Code de Départ :**

```java
public class BookingSystem {
    public void bookRoom(Event event) {
        if (event.getType() == EventType.CONFERENCE) {
            // Réserver une salle de conférence
            // Configurer l'équipement audiovisuel
            // Organiser le service de restauration
        } else if (event.getType() == EventType.MEETING) {
            // Réserver une salle de réunion
            // Organiser les boissons
        } else if (event.getType() == EventType.WEDDING) {
            // Réserver une salle de banquet
            // Décoration
            // Service traiteur
            // Musique
        }
        // Autres types d'événements
    }
}
```

---

## Kata 8 : Gestion des Utilisateurs

**Description :**

Le système gère les utilisateurs avec différents rôles et permissions. Le code actuel est mal organisé et mélange les responsabilités.

**Code de Départ :**

```java
public class UserManager {
    public void createUser(String username, String password, String role) {
        // Validation des données
        // Création de l'utilisateur dans la base de données
        // Attribution du rôle
        if (role.equals("admin")) {
            // Donner toutes les permissions
        } else if (role.equals("editor")) {
            // Donner les permissions d'édition
        } else if (role.equals("viewer")) {
            // Donner les permissions de visualisation
        } else {
            System.out.println("Rôle inconnu.");
        }
    }
}
```
