package gradle.cucumber;

public class ProtoMaxJr extends Enemy {

    public ProtoMaxJr(Maze laberinto, Location location) {
        super(laberinto, location);
    }

    @Override
    public void boom(){
        this.alive = false;
        new JumpAnyWallPower(this.maze, this.currentLocation);
    }
}
