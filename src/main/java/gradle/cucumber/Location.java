package gradle.cucumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Location {

    private int yCoord;
    private int xCoord;
    private HashSet<Item> items;

    public Location(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.items = new HashSet<>();
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

    public void addItem(Item item){
        this.items.add(item);
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

    public HashSet<Item> getItems() {
        return items;
    }

    public boolean existBomb() {
        return this.items.stream().anyMatch(i -> i instanceof Bomb);
    }

    public boolean existWall(){
        return this.items.stream().anyMatch(i -> i instanceof Wall);
    }

    public boolean existEnemy(){
        return this.items.stream().anyMatch(i -> i instanceof Enemy);
    }

    public void boom(){
        this.items.forEach(i -> i.boom());
        Set<Item> deleteItems = this.items.stream().filter(i -> !i.isAlive()).collect(Collectors.toSet());
        this.items.removeAll(deleteItems);
    }

    public boolean existPower() {
        return this.items.stream().anyMatch(i -> i instanceof Power);
    }

    public void setyCoord(Integer y){
        this.yCoord = y;
    }

    public void setxCoord(Integer x){
        this.xCoord = x;
    }
}
