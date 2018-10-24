package gradle.cucumber;

public class JumpAnyWallPower extends Power {

    public JumpAnyWallPower(Maze maze, Location location) {
        super(maze, location);
    }

    @Override
    public boolean jumpAnyWallPower() {
        return true;
    }
}
