package gradle.cucumber;

public class MelamineWall extends Wall {
    public MelamineWall(Maze maze, Location location) {
        super(maze, location);
    }

    @Override
    public void boom(){
        this.getCurrentLocation().removeItem(this);
    }

}
