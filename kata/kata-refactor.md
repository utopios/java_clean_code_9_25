# Sujet du kata — « ParcMeter »

Contexte : une ville veut facturer ses stationnements. La logique tarifaire a poussé à la croissance d’un code difficile à maintenir. Votre mission : écrire des **tests de caractérisation** qui figent le comportement actuel, puis **refactorer** pour supprimer les smells sans modifier le résultat.

Règles métier (réelles, à respecter) :

* Gratuité des **15 premières minutes**.
* Facturation **à l’heure entamée** après la gratuité.
* Tarifs horaires : `CAR=3.00€`, `MOTORBIKE=2.00€`, `TRUCK=6.00€`.
* **Plafond journalier** : `18.00€`.
* **Remise week-end** (samedi/dimanche) : `-10%`.
* **Code promo** `PROMO10` : `-10%` supplémentaires (après la remise week-end).
* Arrondi au centime (deux décimales, arrondi classique).

---

# Code de départ

> Placez ces fichiers dans un même package (ex. `com.kata.parcmeter`). Le code compile, mais contient volontairement des *smells*.

## 1) `Session.java`

```java
package com.kata.parcmeter;

import java.time.LocalDateTime;

public class Session {
    public String vehicleType;      
    public String zone;             
    public LocalDateTime in;
    public LocalDateTime out;
    public String promo;            
    public boolean wknd;           

}
```

## 2) `PriceUtils.java`

```java
package com.kata.parcmeter;

public class PriceUtils {
    public static double rnd(double v) { 
        return Math.round(v * 100.0) / 100.0;
    }
    public static double roundToCents(double value) { 
        return Math.round(value * 100.0) / 100.0;
    }
}
```

## 3) `ParkingService.java` 

```java
package com.kata.parcmeter;

import java.time.Duration;

public class ParkingService {

    public double calculate(Session s, boolean applyPromo) {
        if (s == null || s.in == null || s.out == null) return 0.0; 

        long minutes = Duration.between(s.in, s.out).toMinutes();
        if (minutes <= 15) {
            return 0.0; 
        }

        double rate;
        if ("CAR".equals(s.vehicleType)) {
            rate = 3.0;
        } else if ("MOTORBIKE".equals(s.vehicleType)) {
            rate = 2.0;
        } else if ("TRUCK".equals(s.vehicleType)) {
            rate = 6.0;
        } else {
            rate = 3.0;
        }

        double hoursCharged = Math.ceil((minutes - 15) / 60.0);

        double total = rate * hoursCharged;

        if (s.wknd) {
            total = total * 0.9; // -10%
        }

        if (applyPromo && s.promo != null && "PROMO10".equalsIgnoreCase(s.promo)) {
            total = total * 0.9; // -10% supplémentaire
        }

        if (total > 18.0) {
            total = 18.0;
        }

        total = PriceUtils.rnd(total);
        total = PriceUtils.roundToCents(total);

        if (s.zone != null && s.zone.startsWith("X")) { 
            total = total + 0.0;
        }

        return total;
    }

    
    public double legacy(Session s) {
        return 42.0;
    }
}
```

## 4) `InvoicePrinter.java` 

```java
package com.kata.parcmeter;

public class InvoicePrinter {

    public String print(Session s, ParkingService svc) {
        
        double price = svc.calculate(s, /*applyPromo*/ true);
        String v = s.vehicleType; 
        String wk = s.wknd ? "WEEKEND" : "WEEKDAY";
        String pr = (s.promo != null) ? s.promo : "NO_PROMO";

        return "INVOICE[" + v + ";" + wk + ";" + pr + "]=" + price + "€";
    }

    public double getPrice(Session s, ParkingService p) {
        return p.calculate(s, true);
    }
}
```



---

# Cas d’acceptation 

Vous devez garantir que, **après refactor**, ces cas produisent exactement ces montants :

1. Gratuité :

   * Entrée 10:00, sortie 10:10, `CAR`, jour ouvré, sans promo → **0.00**.

2. Heure entamée simple :

   * 10:00 → 10:40, `MOTORBIKE`, ouvré, sans promo → minutes=40, payant=25, heures facturées=1 → **2.00**.

3. Deux heures entamées :

   * 10:00 → 11:20, `TRUCK`, ouvré → minutes=80, payant=65, heures facturées=2 → 2×6= **12.00**.

4. Trois heures entamées :

   * 10:00 → 13:10, `CAR`, ouvré → minutes=190, payant=175, heures facturées=3 → 3×3= **9.00**.

5. Remises cumulées (week-end + promo) :

   * Samedi 10:00 → 12:01, `CAR`, `PROMO10` → minutes=121, payant=106, heures=2, brut=6.00 → week-end -10% = 5.40 → promo -10% = **4.86**.

6. Plafond journalier :

   * 9h moins 15 min payantes sur `TRUCK` → 9×6=54.00 → plafond = **18.00**.



---

# Squelette JUnit de caractérisation

> Adapter les dates pour forcer samedi/dimanche lorsque requis.

```java
package com.kata.parcmeter;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

public class ParkingServiceTest {

    private final ParkingService svc = new ParkingService();

    private Session s(String type, String promo, boolean wknd,
                      int y, int m, int d, int h1, int min1, int h2, int min2) {
        Session s = new Session();
        s.vehicleType = type;
        s.promo = promo;
        s.wknd = wknd;
        s.in = LocalDateTime.of(y, m, d, h1, min1);
        s.out = LocalDateTime.of(y, m, d, h2, min2);
        return s;
    }

    @Test
    void free_0_10_car_weekday() {
        var s = s("CAR", null, false, 2025, 1, 7, 10, 0, 10, 10);
        assertThat(svc.calculate(s, true)).isEqualTo(0.00);
    }

    @Test
    void motorbike_40min_one_hour_charged() {
        var s = s("MOTORBIKE", null, false, 2025, 1, 7, 10, 0, 10, 40);
        assertThat(svc.calculate(s, true)).isEqualTo(2.00);
    }

    @Test
    void truck_80min_two_hours() {
        var s = s("TRUCK", null, false, 2025, 1, 7, 10, 0, 11, 20);
        assertThat(svc.calculate(s, true)).isEqualTo(12.00);
    }

    @Test
    void car_190min_three_hours() {
        var s = s("CAR", null, false, 2025, 1, 7, 10, 0, 13, 10);
        assertThat(svc.calculate(s, true)).isEqualTo(9.00);
    }

    @Test
    void weekend_plus_promo() {
        var s = s("CAR", "PROMO10", true, 2025, 1, 4, 10, 0, 12, 1); // samedi
        assertThat(svc.calculate(s, true)).isEqualTo(4.86);
    }

    @Test
    void daily_cap_truck() {
        var s = s("TRUCK", null, false, 2025, 1, 7, 8, 0, 17, 45); // ~9h45
        assertThat(svc.calculate(s, true)).isEqualTo(18.00);
    }
}
```


### Etapes:

1. Primitive Obsession
2. Extraction des ValuesObjects
3. Remplacement des Magic Numbers
4. Strategy Pattern pour les remises
5. Avoir une SRP pour les classes
6. Code Mort
7. Améliorer  le flux de calcule. 

