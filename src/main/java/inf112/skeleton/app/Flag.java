package inf112.skeleton.app;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Flag {
    public Texture texture;
    public static Texture texNext = new Texture(Gdx.files.internal("src/assets/flag_next.png"));;
    public static Texture texIdle = new Texture(Gdx.files.internal("src/assets/flag_idle.png"));;
    public static Texture texReached = new Texture(Gdx.files.internal("src/assets/flag_reached.png"));;
    public int posX;
    public int posY;
    public Flag(int _x, int _y){
        posX = _x;
        posY = _y;
        texture = texIdle;
    }

    public boolean IsAt(Coordinate pos){
        if(posX ==  pos.x && posY == pos.y){
            return true;
        }
        return false;
    }
}