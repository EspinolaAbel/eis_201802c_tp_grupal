package gradle.cucumber;

import java.util.HashMap;

public class Maze {

    private final Integer height;
    private final Integer width;

    private final Location[][] map;

    /**
     * Constructor para instanciar un {@link Maze} con dimensión inicial fija
     *
     * @param height
     * @param width
     */
    public Maze(Integer height, Integer width) {
        this.height = height;
        this.width = width;

        // create and fill maze
        this.map = new Location[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                this.map[j][i] = new Ground(i, j); // por defecto, t0do va a ser Ground
    }

    public void move(Item item, Direction directionWhereToMove) {
        final Location location = this.findItemLocation(item);
        try {
            int xCoord = location.getXCoordinate() + directionWhereToMove.getxCoord();
            int yCoord = location.getYCoordinate() + directionWhereToMove.getyCoord();

            Location nextLocation = this.getLocation(xCoord, yCoord);

            if (nextLocation.canEnter())
                this.moveItemToLocation(item, nextLocation);
        } catch (ArrayIndexOutOfBoundsException e) { /* do nothing */ }
    }

    private void moveItemToLocation(Item item, Location nextLocation) {
        Location location = item.getCurrentLocation();
        if (location != null)
            location.removeItem(item);
        nextLocation.enter(item);
        item.setCurrentLocation(nextLocation);
    }

    public Location findItemLocation(Item item) {
        Location aLocation = item.getCurrentLocation();
        if (aLocation == null)
            throw new RuntimeException("El item " + item + " no esta presente en el laberinto");
        return aLocation;
    }

    public void setItemAtLocation(Item item, int xCoordinate, int yCoordinate) {
        Location locationWhereToMove = this.getLocation(xCoordinate, yCoordinate);
        this.moveItemToLocation(item, locationWhereToMove);
    }

    public Location getLocation(int xCoordinate, int yCoordinate) {
        return this.map[yCoordinate][xCoordinate];
    }

    public boolean existLocation(Integer x, Integer y){
        return x <= this.width && x >= 0 && y <= this.height && y >= 0;
    }
}

