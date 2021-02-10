package inf112.skeleton.app;

/** Maybe we need an iterable for the board elements? **/
public class Board {

    // private Player player;
    private boolean gameOver = false;

    // constants
    static final int WIDTH = 0;
    static final int HEIGHT = 0;

    // initial
    public static final int initialXPosition = 0;
    public static final int initialYPosition = 0;

    public void Board(){
        /** The grid is made, somehow. And the players and
         * board elements are initialized.
         */

       /*
       player = new Player(initialPosition);
       currentPosition = initialPosition;
       gameOver = false;


        //Make the grid with the tiles
         for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                this.grid[i][j] = new Tile();
            }
        }
        */
    }


    /**
     * A few methods that could make sense to implement here.
     * Feel free to remove and/or add more.
     * @return
     */
    public boolean isGameOver(){
        return false;
    }

    public boolean isWithinBounds(){
        return false;
    }

    public boolean isLegalMove(){
        return false;
    }

    public void makeMove(){
    }

    public boolean adjacentTiles(){
        return false;
    }


    /**
     * Getters
     */



}
