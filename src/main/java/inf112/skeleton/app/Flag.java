package inf112.skeleton.app;
import com.badlogic.gdx.graphics.Texture;

public class Flag {
    public Texture texture;
    public int posX;
    public int posY;
    boolean visitedP1;
    public Flag(int _x, int _y){
        posX = _x;
        posY = _y;
        visitedP1 = false;
    }
}
