package gradle.cucumber;

import java.util.List;

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
    protected void interactsWith(Item otherItem) {
        // no pasa nada cuando otro item interactua con bomberman
    }

}
