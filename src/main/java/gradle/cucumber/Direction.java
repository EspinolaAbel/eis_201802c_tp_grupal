package gradle.cucumber;

public enum Direction {
    LEFT(-1,0),
    RIGHT(1,0),
    UP(0,1),
    DOWN(0,-1)
    ;

    private final int yCoord;
    private final int xCoord;


    Direction(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }


    public int getyCoord() {
        return yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }
}
