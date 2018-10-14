package gradle.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertTrue;

public class BombermanStepdefs {

    private Bomberman bomberman;
    private Maze laberinto;


    @Given("^Bomberman y un laberinto de \"([^\"]*)\" por \"([^\"]*)\"$")
    public void bomberman_y_un_laberinto_de_por(String alto, String ancho) {
        this.laberinto = new Maze(Integer.parseInt(alto), Integer.parseInt(ancho));
        this.bomberman = new Bomberman(this.laberinto);

        this.laberinto.setItemAtLocation(this.bomberman, 0,0);
    }

    @When("^intenta desplazarse a la celda contigua vacia$")
    public void intenta_desplazarse_a_la_celda_contigua_vacia() {
        this.bomberman.move(Direction.RIGHT);
    }

    @Then("^Bomberman se desplaza a la celda$")
    public void bomberman_se_desplaza_a_la_celda() {
        Location celdaContigua = this.laberinto.getLocation(1, 0);
        assertThat(celdaContigua.getItems(), hasItem(this.bomberman));
    }



    @When("^intenta desplazarse a la celda ocupada por una pared$")
    public void intentaDesplazarseALaCeldaOcupadaPorUnaPared() throws Throwable {
        Wall pared = new Wall(1,0);

        this.laberinto.setLocationAt(pared, pared.getXCoordinate(), pared.getYCoordinate());

        this.bomberman.move(Direction.RIGHT);
    }

    @Then("^Bomberman no se mueve de lugar$")
    public void bombermanNoSeMueveDeLugar() throws Throwable {
        Location celdaContigua = this.laberinto.getLocation(1, 0);
        assertThat(celdaContigua.getItems(), not(hasItem(this.bomberman)));
    }


    @Given("^Bomberman y un laberinto de \"([^\"]*)\" por \"([^\"]*)\" con un enemigo en una la celda contigua$")
    public void bombermanYUnLaberintoDePorConUnEnemigoEnUnaLaCeldaContigua(String alto, String ancho) throws Throwable {
        this.laberinto = new Maze(Integer.parseInt(alto), Integer.parseInt(ancho));
        this.bomberman = new Bomberman(this.laberinto);

        this.laberinto.setItemAtLocation(this.bomberman, 0,0);

        Enemy unEnemigo = new Enemy(this.laberinto);
        this.laberinto.setItemAtLocation(unEnemigo, 1, 0);
    }

    @When("^intenta desplazarse a la celda ocupada por un enemigo$")
    public void intentaDesplazarseALaCeldaOcupadaPorUnEnemigo() throws Throwable {
        this.bomberman.move(Direction.RIGHT);
    }

    @Then("^Bomberman muere$")
    public void bombermanMuere() throws Throwable {
        assertFalse(this.bomberman.isAlive());
    }

}
