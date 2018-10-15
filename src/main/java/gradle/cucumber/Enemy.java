package gradle.cucumber;

public class Enemy extends Item {

    public Enemy(Maze maze) {
        super(maze);
    }

    public void boom(){

    }

    @Override
    protected void interactsWith(Item otherItem) {
        Class<? extends Item> aClass = otherItem.getClass();

        if (aClass.equals(Bomberman.class))
            otherItem.kill();
    }

}
