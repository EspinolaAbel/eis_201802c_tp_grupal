package gradle.cucumber;

public class Ground extends Location {

    public Ground(int x, int y) {
        super(x,y);
    }

    @Override
    public boolean canEnter() {
        return true;
    }

}
