package gradle.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

public class BombermanStepdefs {

    private Bomberman bomberman;
    private Maze laberinto;
    private TicksController ticksController;


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



    @When("^intenta desplazarse a la celda ocupada por una pared en x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void intenta_desplazarse_a_la_celda_ocupada_por_una_pared_en_x_y(String x, String y) {
        Location location = new Location(Integer.parseInt(x), Integer.parseInt(y));
        new Wall(this.laberinto, location);

        this.bomberman.move(Direction.RIGHT);
    }

    @Then("^Bomberman no se mueve de lugar$")
    public void bombermanNoSeMueveDeLugar() throws Throwable {
        Location celdaContigua = this.laberinto.getLocation(1, 0);
        assert(celdaContigua.hasItem(this.bomberman));
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


    /* ----------- BOMBAS ------------ */


    @Given("^Un controlador de ticks$")
    public void un_controlador_de_ticks() {
        this.ticksController = new TicksController();
    }

    @Given("^Una pared de melamina en x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void una_pared_de_melamina_en_x_y(String x, String y) {
        Location location = this.laberinto.getLocation(Integer.parseInt(x), Integer.parseInt(y));
        new MelamineWall(this.laberinto, location);
    }

    @When("^Bomberman pone una Bomba en la celda donde esta ubicado con \"([^\"]*)\" ticks$")
    public void bomberman_pone_una_Bomba_en_la_celda_donde_esta_ubicado_con_ticks(String ticks) {
        this.bomberman.dropBomb(Integer.parseInt(ticks), ticksController);

        Location bombLocation = this.bomberman.getCurrentLocation();

        assert(bombLocation.existBomb());
    }

    @Then("^La bomba explota despues de \"([^\"]*)\" ticks de bomberman y tiene onda expansiva con radio de 3 celdas")
    public void la_bomba_explota_despues_de_ticks_de_bomberman_y_tiene_onda_expansiva_de_radio(String ticks) {
        Location bombLocation = this.bomberman.getCurrentLocation();

        for (int i = 0; i < 3; i++) {
            this.ticksController.tick();
        }

        assert(!bombLocation.existBomb());
    }

    @Then("^La pared de melamina en x:\"([^\"]*)\" y:\"([^\"]*)\" desaparece por la onda expansiva$")
    public void la_pared_de_melamina_en_x_y_desaparece_por_la_onda_expansiva(String x, String y) {
        Location location = new Location(Integer.parseInt(x), Integer.parseInt(y));
        assert(!location.existWall());
    }

}
