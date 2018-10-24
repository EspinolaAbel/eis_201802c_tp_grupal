package gradle.cucumber;

public class JumpOrMultiBombPower extends Power {
    public JumpOrMultiBombPower(Maze maze, Location location){
        super(maze, location);
    }

    @Override
    public boolean jumpAnyWallPower() {
        return true;
    }

    public boolean multiBombPower(){
        return true;
    }

}
