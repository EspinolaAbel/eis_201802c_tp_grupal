package gradle.cucumber;

import java.util.ArrayList;
import java.util.List;

public class TicksController {
    private List<Bomb> bombs;

    public TicksController(){
        this.bombs = new ArrayList<>();
    }

    public void tick() {
        this.bombs.forEach(b -> b.tick());
    }

    public void addBomb(Bomb bomb){
        this.bombs.add(bomb);
    }

    public void removeBomb(Bomb bomb){
        this.bombs.remove(bomb);
    }
}
