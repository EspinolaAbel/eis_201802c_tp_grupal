package gradle.cucumber;

public class Wall extends Item {

    public Wall(Maze maze, Location location){
        super(maze, location);
    }

    @Override
    public void boom() {

    }

    @Override
    protected void interactsWith(Item otherItem) {

    }
}
