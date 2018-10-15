package gradle.cucumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TicksController {
    private HashSet<Bomb> bombs;
    private HashSet<Bomb> deleteBombs;

    public TicksController(){
        this.bombs = new HashSet<>();
        this.deleteBombs = new HashSet<>();
    }

    public void tick() {
        this.bombs.forEach(b -> b.tick());
        this.bombs.removeAll(this.deleteBombs);
        this.deleteBombs = new HashSet<>();
    }

    public void addBomb(Bomb bomb){
        this.bombs.add(bomb);
    }

    public void removeBomb(Bomb bomb){
        this.deleteBombs.add(bomb);
    }
}
