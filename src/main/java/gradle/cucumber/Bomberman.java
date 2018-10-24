package gradle.cucumber;

import java.util.HashSet;

public class Bomberman extends Item {

    private HashSet<Power> powers;

    /**
     * Constructor para crear un nuevo {@link Bomberman}.
     * Este recibe como parámetro un {@link Maze} con el cual interactuar
     *
     * @param maze
     */
    public Bomberman(Maze maze) {
        super(maze);
        this.powers = new HashSet<>();
    }

    @Override
    public void boom() {

    }

    @Override
    protected void interactsWith(Item otherItem) {

    }

    public Bomb dropBomb(Integer ticks, TicksController ticksController) {
        Bomb bomb = new Bomb(this.maze, this.getCurrentLocation(), ticks);
        bomb.setTicksController(ticksController);
        ticksController.addBomb(bomb);
        return bomb;
    }


    public void throwBomb(Integer ticks, TicksController ticksController, Integer cant, Direction direction){
        if (this.hasJumpOrMultiBomb()){
            dropBomb(ticks,ticksController).moveXToDir(cant,direction);
            dropBomb(ticks,ticksController).moveXToDir(cant+3,direction);
        } else if (this.hasThrowBombPower()){
            dropBomb(ticks,ticksController).moveXToDir(cant,direction);
        }
    }

    public void addPower(Power power) {
        this.powers.add(power);
    }

    public boolean hasThrowBombPower() {
        return this.powers.stream().anyMatch(p -> p.throwBombPower());
    }

    public boolean hasJumpAnyWallPower() {
        return this.powers.stream().anyMatch(p -> p.jumpAnyWallPower());
    }

    public boolean hasJumpOrMultiBomb(){
        return this.powers.stream().anyMatch(p -> p.multiBombPower());
    }

}
