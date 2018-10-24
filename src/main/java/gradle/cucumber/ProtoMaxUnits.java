package gradle.cucumber;

public class ProtoMaxUnits extends Enemy {
    public ProtoMaxUnits(Maze laberinto, Location location) {
        super(laberinto, location);
    }

    @Override
    public void boom(){
        super.boom();
        new JumpOrMultiBombPower(this.maze, this.currentLocation);
    }
}