package gradle.cucumber;

import java.util.HashSet;

public class Bomberman extends Item {

    private HashSet<Power> powers;

    /**
     * Constructor para crear un nuevo {@link Bomberman}.
     * Este recibe como parámetro un {@link Maze} con el cual interactuar
     * @param maze
     * */
    public Bomberman(Maze maze) {
        super(maze);
        this.powers = new HashSet<>();
    }

    @Override
    public void boom() {

    }

    @Override
    protected void interactsWith(Item otherItem) {

    }

    public void dropBomb(Integer ticks, TicksController ticksController) {
        Bomb bomb = new Bomb(this.maze, this.getCurrentLocation(), ticks);
        bomb.setTicksController(ticksController);
        ticksController.addBomb(bomb);
    }

    public void throwBomb(Integer ticks, TicksController ticksController, Integer cant, Direction direction){
        Bomb bomb = new Bomb(this.maze, this.getCurrentLocation(), ticks);
        bomb.setTicksController(ticksController);
        ticksController.addBomb(bomb);
        bomb.moveXToDir(cant, direction);
    }

    public void addPower(Power power){
        this.powers.add(power);
    }

    public boolean hasThrowBombPower(){
        return this.powers.stream().anyMatch(p -> p instanceof ThrowBombPower);
    }
}
