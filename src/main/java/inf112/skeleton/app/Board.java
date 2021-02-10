package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** Maybe we need an iterable for the board elements? **/
public class Board {
    // textures and spritebatch
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture boardImage;
    private Texture robot1Image;


    // private Player player;
    private boolean gameOver = false;

    // constants
    static final int WIDTH = 0;
    static final int HEIGHT = 0;

    // initial
    public static final int initialXPosition = 0;
    public static final int initialYPosition = 0;

    public Board(){
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
        boardImage = new Texture(Gdx.files.internal("src/assets/Board_01.png"));
        robot1Image = new Texture(Gdx.files.internal("src/assets/robot1.png"));
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
    }

    public void Dispose(){
        batch.dispose();
        font.dispose();
    }

    public void Render(){
        batch.begin();
        batch.draw(boardImage,0,0);
        batch.draw(robot1Image, 2*83, 4*83);
        font.draw(batch, "Hello World", 200, 200);
        batch.end();
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
