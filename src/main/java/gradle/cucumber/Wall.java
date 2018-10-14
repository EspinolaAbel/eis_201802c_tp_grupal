package gradle.cucumber;

public class Wall extends Location {

    public Wall(int xCoord, int yCoord) {
        super(xCoord, yCoord);
    }

    @Override
    public boolean canEnter() {
        return false;
    }

}
