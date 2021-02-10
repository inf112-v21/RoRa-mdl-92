package inf112.skeleton.app;

public enum Direction {
    /**
     * Nothing exciting to see here...
     *
     */

    UP(0),RIGHT(1),LEFT(2),DOWN(3);

    private int dir;

    private Direction(int dir){
        dir = dir;
    }

    public Direction getNewDirection(int dir){
        return null;
    }

    public static Direction getOppositeDirection(int dir){
        return null;
    }
}
