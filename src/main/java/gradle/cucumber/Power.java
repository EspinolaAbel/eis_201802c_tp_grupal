package gradle.cucumber;

public class Power extends Item {
    public Power(Maze maze, Location location){
        super(maze, location);
    }

    @Override
    public void boom() {

    }

    @Override
    protected void interactsWith(Item otherItem) {
        if (otherItem instanceof Bomberman){
            Bomberman bomberman = (Bomberman) otherItem;
            bomberman.addPower(this);
        }
    }
}
