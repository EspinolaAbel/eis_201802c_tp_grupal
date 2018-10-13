package gradle.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class BombermanStepdefs {

    private Bomberman bomberman;
    private Maze laberinto;

    @Given("^Bomberman y un laberinto de \"([^\"]*)\" por \"([^\"]*)\"$")
    public void bomberman_y_un_laberinto_de_por(String alto, String ancho) {
        this.laberinto = new Maze(Integer.parseInt(alto), Integer.parseInt(ancho));
        this.bomberman = new Bomberman(this.laberinto);

        this.laberinto.setItemLocation(0,0);
    }

    @When("^intenta desplazarse a la celda contigua vacia$")
    public void intenta_desplazarse_a_la_celda_contigua_vacia() {
        this.bomberman.move(Direction.LEFT);
    }

    @Then("^Bomberman se desplaza a la celda$")
    public void bomberman_se_desplaza_a_la_celda() {
        this.laberinto.
    }


}
