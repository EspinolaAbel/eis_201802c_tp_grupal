package gradle.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.Normalizer;

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
        Location location = this.laberinto.getLocation(Integer.parseInt(x), Integer.parseInt(y));
        new Wall(this.laberinto, location);

        this.bomberman.move(Direction.RIGHT);
    }

    @Then("^Bomberman no se mueve de lugar$")
    public void bombermanNoSeMueveDeLugar() throws Throwable {
        Location celdaContigua = this.laberinto.getLocation(1, 0);
        assertTrue(celdaContigua.hasItem(this.bomberman));
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
        Location location = this.getLocationFromStrings(x, y);
        new MelamineWall(this.laberinto, location);
    }

    @When("^Bomberman pone una Bomba en la celda donde esta ubicado con \"([^\"]*)\" ticks$")
    public void bomberman_pone_una_Bomba_en_la_celda_donde_esta_ubicado_con_ticks(String ticks) {
        this.bomberman.dropBomb(Integer.parseInt(ticks), ticksController);

        Location bombLocation = this.bomberman.getCurrentLocation();

        assertTrue(bombLocation.existBomb());
    }

    @Then("^La bomba explota despues de \"([^\"]*)\" ticks de bomberman y tiene onda expansiva con radio de 3 celdas")
    public void la_bomba_explota_despues_de_ticks_de_bomberman_y_tiene_onda_expansiva_de_radio(String ticks) {
        Location bombLocation = this.bomberman.getCurrentLocation();

        for (int i = 0; i < Integer.parseInt(ticks); i++) {
            this.ticksController.tick();
        }

        assertTrue(!bombLocation.existBomb());
    }

    @Then("^La pared de melamina en x:\"([^\"]*)\" y:\"([^\"]*)\" desaparece por la onda expansiva$")
    public void la_pared_de_melamina_en_x_y_desaparece_por_la_onda_expansiva(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);
        assertFalse(location.existWall());
    }

    @Given("^Un enemigo en posicion x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void un_enemigo_en_posicion_x_y(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);
        new Enemy(this.laberinto, location);
    }

    @Then("^El enemigo de la posicion x:\"([^\"]*)\" y:\"([^\"]*)\" muere por la onda expansiva$")
    public void el_enemigo_de_la_posicion_x_y_muere_por_la_onda_expansiva(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);

        assertFalse(location.existEnemy());
    }

    @Given("^Una pared de acero en x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void una_pared_de_acero_en_x_y(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);
        new SteelWall(this.laberinto, location);
    }

    @Then("^La pared de acero en x:\"([^\"]*)\" y:\"([^\"]*)\" no desaparece por la onda expansiva$")
    public void la_pared_de_acero_en_x_y_no_desaparece_por_la_onda_expansiva(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);
        assertTrue(location.existWall());
    }

    /*------------ BAGULAA ---------------*/

    @Given("^Un enemigo de tipo Bagulaa en la posicion x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void un_enemigo_de_tipo_Bagulaa_en_la_posicion_x_y(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);
        new Bagulaa(this.laberinto, location);

        assertTrue(location.existEnemy());
    }

    @And("^La bomba mata al enemigo en la posicion x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void la_bomba_mata_al_enemigo_en_la_posicion_x_y(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);

        assertTrue(!location.existEnemy());
    }

    @And("^El enemigo suelta un poder en la posicion x:\"([^\"]*)\" y:\"([^\"]*)\" donde muere$")
    public void el_enemigo_de_tipo_Bagulaa_suelta_el_poder_Lanzar_Bombas_en_la_posicion_donde_muere(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);

        assertTrue(location.existPower());
    }

    @Given("^Un Power de tipo Lanzar Bombas en la posicion x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void un_Power_de_tipo_Lanzar_Bombas_en_la_posicion_x_y(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);

        new ThrowBombPower(this.laberinto, location);
    }

    @When("^Bomberman se desplaza a la celda x:\"([^\"]*)\" y:\"([^\"]*)\"$")
    public void bomberman_se_desplaza_a_la_celda_x_y(String x, String y) {
        Location location = this.getLocationFromStrings(x, y);

        this.bomberman.setCurrentLocation(location);
    }

    @Then("^Bomberman tiene el Power de tipo Lanzar Bombas$")
    public void bomberman_tiene_el_Power_de_tipo_Lanzar_Bombas() {
        assertTrue(this.bomberman.hasThrowBombPower());
    }

    @Given("^Bomberman con Power de tipo Lanzar Bombas$")
    public void bomberman_con_Power_de_tipo_Lanzar_Bombas() {
        this.bomberman.addPower(new ThrowBombPower(this.laberinto, this.bomberman.getCurrentLocation()));
    }

    @When("^Bomberman Lanza bomba con \"([^\"]*)\" ticks y \"([^\"]*)\" celdas hacia \"([^\"]*)\"$")
    public void bomberman_Lanza_bomba_con_ticks_y_celdas_hacia(String ticks, String celdas, String dir) {
        Direction direction = this.getDirection(dir.toLowerCase());
        this.bomberman.throwBomb(Integer.parseInt(ticks), this.ticksController, Integer.parseInt(celdas), direction);
    }

    @Then("^La bomba se mueve \"([^\"]*)\" celdas mas al \"([^\"]*)\" que bomberman$")
    public void la_bomba_se_mueve_celdas_mas_al_que_bomberman(String cant, String dir) {
        Direction direction = this.getDirection(dir.toLowerCase());
        this.bomberman.moveXToDir(Integer.parseInt(cant), direction);
        Location location = this.bomberman.getCurrentLocation();

        assertTrue(location.existBomb());
    }

    private Location getLocationFromStrings(String x, String y){
        return this.laberinto.getLocation(Integer.parseInt(x), Integer.parseInt(y));
    }

    private Direction getDirection(String dir){
        Direction direction = null;

        if(dir.equals("norte")){
            direction = Direction.UP;
        } else if (dir.equals("sur")){
            direction = Direction.DOWN;
        } else if (dir.equals("este")){
            direction = Direction.RIGHT;
        } else if (dir.equals("oeste")){
            direction = Direction.LEFT;
        }

        return direction;
    }
}
