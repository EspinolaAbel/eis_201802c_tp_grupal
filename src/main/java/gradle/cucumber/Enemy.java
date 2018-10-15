package gradle.cucumber;

public class Enemy extends Item {

    public Enemy(Maze maze) {
        super(maze);
    }

    public Enemy(Maze maze, Location location){
        super(maze, location);
    }

    public void boom(){
        this.getCurrentLocation().removeItem(this);
    }

    @Override
    protected void interactsWith(Item otherItem) {
        Class<? extends Item> aClass = otherItem.getClass();

        if (aClass.equals(Bomberman.class))
            otherItem.kill();
    }

}
