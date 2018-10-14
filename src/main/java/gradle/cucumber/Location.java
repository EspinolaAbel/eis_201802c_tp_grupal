package gradle.cucumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Location {

    private final int yCoord;
    private final int xCoord;
    private List<Item> items;

    public Location(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.items = new ArrayList<>();
    }

    public int getXCoordinate() {
        return this.xCoord;
    }

    public int getYCoordinate() {
        return this.yCoord;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Location location = (Location) o;
//        return yCoord == location.yCoord &&
//                xCoord == location.xCoord;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(yCoord, xCoord);
//    }

    public abstract boolean canEnter();

    /**
     * The given object is removed from the current {@link Location}
     * @param item - an {@link Item} object
     * */
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /**
     * The given object is added in the current {@link Location}
     * @param item - an {@link Item} object
     * */
    public void enter(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
