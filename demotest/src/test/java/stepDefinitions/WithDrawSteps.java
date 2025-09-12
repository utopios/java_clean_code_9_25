package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.example.entity.Compte;
import com.example.entity.Distributeur;
import com.example.entity.RetraitResultat;

import static org.junit.jupiter.api.Assertions.*;
public class WithDrawSteps {

    private Compte compte;
    private Distributeur distributeur;
    private RetraitResultat resultat;

    @Given("mon compte a un solde de {int} euros")
    public void monCompteAUnSoldeDe(Integer euros) {
        compte = new Compte(euros);
    }

    @When("je demande à retirer {int} euros")
    public void jeDemandeARetirer(Integer montant) {
        this.distributeur = new Distributeur();
        this.resultat = distributeur.retirer(compte, montant);
    }

    @Then("je devrais recevoir {int} euros")
    public void jeDevraisRecevoir(Integer montant) {
        assertEquals(montant, resultat.getMontant());
    }

    @Then("mon solde devrait être de {int} euros")
    public void monSoldeDevraitEtreDe(Integer solde) {
        assertEquals(solde, compte.getSolde());
    }
}
