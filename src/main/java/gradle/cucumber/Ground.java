package gradle.cucumber;

/**
 * Es un tipo de {@link Location} donde no hay nada. Es una ubicación default.
 * Puede contener {@link Item}s dentro suyo.
 */
public class Ground extends Location {

    public Ground(int x, int y) {
        super(x,y);
    }

    @Override
    public boolean canEnter() {
        return true;
    }

}
