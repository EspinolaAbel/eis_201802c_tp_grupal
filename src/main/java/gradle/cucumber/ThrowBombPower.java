package gradle.cucumber;

public class ThrowBombPower extends Power {
    public ThrowBombPower(Maze maze, Location location){
        super(maze, location);
    }

    @Override
    public boolean throwBombPower(){
        return true;
    }
}
