package gradle.cucumber;

public class Enemy extends Item {

    public Enemy(Maze maze) {
        super(maze);
    }

    @Override
    protected void interactsWith(Item otherItem) {
        Class<? extends Item> aClass = otherItem.getClass();

        if (aClass.equals(Bomberman.class))
            otherItem.kill();
    }

}
