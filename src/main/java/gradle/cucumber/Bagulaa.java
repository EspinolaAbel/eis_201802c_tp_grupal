package gradle.cucumber;

public class Bagulaa extends Enemy {
    public Bagulaa(Maze laberinto, Location location) {
        super(laberinto, location);
    }

    @Override
    public void boom(){
        this.alive = false;
        new ThrowBombPower(this.maze, this.currentLocation);
    }
}
