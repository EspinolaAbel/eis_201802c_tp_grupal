package gradle.cucumber;

public class Enemy extends Item {

    public Enemy(Maze maze) {
        super(maze);
    }

    public Enemy(Maze maze, Location location){
        super(maze, location);
    }

    @Override
    public void boom(){
        this.alive = false;
    }

    @Override
    protected void interactsWith(Item otherItem) {
        Class<? extends Item> aClass = otherItem.getClass();

        if (aClass.equals(Bomberman.class))
            otherItem.kill();
    }

}
