package gradle.cucumber;

public class Bomberman implements Item {

    private final Maze maze;

    /**
     * Constructor para crear un nuevo {@link Bomberman}.
     * Este recibe como parámetro un {@link Maze} con el cual interactuar
     * @param maze
     * */
    public Bomberman(Maze maze) {
        this.maze = maze;
    }

    public void move(Direction direction) {
        this.maze.move(this, direction);
    }

}
