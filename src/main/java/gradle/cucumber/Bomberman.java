package gradle.cucumber;

public class Bomberman extends Item {


    /**
     * Constructor para crear un nuevo {@link Bomberman}.
     * Este recibe como parámetro un {@link Maze} con el cual interactuar
     * @param maze
     * */
    public Bomberman(Maze maze) {
        super(maze);
    }

    @Override
    public void boom() {

    }

    @Override
    protected void interactsWith(Item otherItem) {

    }

    public void dropBomb(Integer ticks, TicksController ticksController) {
        Bomb bomb = new Bomb(this.maze, this.getCurrentLocation(), ticks);
        ticksController.addBomb(bomb);
    }
}
