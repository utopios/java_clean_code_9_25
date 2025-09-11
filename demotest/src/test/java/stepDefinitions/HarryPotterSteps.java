package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.entity.BookBasket;
import org.example.entity.PriceCalculator;
import static org.junit.jupiter.api.Assertions.*;

public class HarryPotterSteps {

    private BookBasket bookBasket;
    private PriceCalculator priceCalculator;
    @Given("je veux acheter des livres Harry Potter")
    public void jeVeuxAcheterDesLivresHarryPotter() {
        bookBasket = new BookBasket();
        priceCalculator = new PriceCalculator();
    }

    @When("j'ajoute {int} exemplaire du tome {int} à mon panier")
    public void jAjoute(Integer quantite, Integer tome) {
        for(int i=0; i < quantite; i++) {
            bookBasket.addBook(tome);
        }
    }

    @Then("le prix total devrait être de {double} euros")
    public void lePrixTotalDevraitEtreDe(Double prixAttendu) {
        double prixTotal = priceCalculator.calculatePrice(bookBasket);
        assertEquals(prixAttendu, prixTotal);
    }

    @Then("la remise appliquée est de {int}%")
    public void laRemiseAppliqueeEstDe(int pourcentage) {
        int rate = priceCalculator.getRate(bookBasket);
        assertEquals(pourcentage, rate);
    }
}
