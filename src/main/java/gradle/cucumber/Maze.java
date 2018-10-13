package gradle.cucumber;

import java.util.HashMap;

public class Maze {

    private final Integer height;
    private final Integer width;

    private final Location[][] map;

    private final HashMap<Item,Location> itemsLocation;

    /**
     * Constructor para instanciar un {@link Maze} con dimensión inicial fija
     * @param height
     * @param width
     * */
    public Maze(Integer height, Integer width) {
        this.height = height;
        this.width = width;

        this.itemsLocation = new HashMap<>();

        // create and fill maze
        this.map = new Location[height][width];

        for (int i = 0; i<height; i++)
            for (int j = 0; j<width; j++)
                this.map[j][i] = new Ground(i, j);
    }

    public void move(Item item, Direction directionWhereToMove) {
        final Location location = this.findItemLocation(item);
        try {
            Location nextLocation = this.map [location.getXCoordinate() + directionWhereToMove.getxCoord()]
                                            [location.getYCoordinate() + directionWhereToMove.getyCoord()];

            if (nextLocation.canEnter()) {
                location.removeItem(item);
                nextLocation.enter(item);
            }
        }
        catch(ArrayIndexOutOfBoundsException e) { /* do nothing */ }
    }

    public Location findItemLocation(Item item) {
        Location location = this.itemsLocation.get(item);
        if (location == null)
            throw new RuntimeException("El item " + item + " no esta presente en el laberinto");
        return location;
    }

    public void setItemLocation(Item item, int xCoordinate, int yCoordinate) {
        Location location = this.getLocation(xCoordinate, yCoordinate);
        location.enter(item);
        this.
    }

    public Location getLocation(int xCoordinate, int yCoordinate) {
        return this.map[yCoordinate][xCoordinate];
    }
}

