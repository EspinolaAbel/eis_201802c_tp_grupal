package gradle.cucumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Location {

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

    public boolean canEnter(){
        boolean hasItem = this.items.stream().anyMatch(i -> i instanceof Wall);
        return hasItem;
    }

    public boolean hasItem(Item item){
        return this.items.contains(item);
    }

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

    public boolean existBomb() {
        return this.items.stream().anyMatch(i -> i instanceof Bomb);
    }

    public boolean existWall(){
        return this.items.stream().anyMatch(i -> i instanceof Wall);
    }

    public void boom(){
        this.items.forEach(i -> i.boom());
    }
}
