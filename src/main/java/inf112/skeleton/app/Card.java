package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;


abstract class Card {
    public int priority = 0;
    int cardSprite = 0;
    static ArrayList<Sprite> cardSprites = new ArrayList<Sprite>(); // holds all the card sprites for the cards to use
    public Card(){
        if(cardSprites.size() == 0) {
            cardSprites.add(new Sprite(new Texture("src/assets/move1.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/move2.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/move3.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/rotate_left.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/rotate_right.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/u_turn.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/backup.png")));
        }
    }
    public Card(int _priority){
        priority = _priority;
    }
    public void draw(SpriteBatch s,int x, int y){
        s.draw(cardSprites.get(cardSprite).getTexture(),x,y);
    }
    abstract  void DoAction(Robot me, Board board);
}

class Move1Card extends Card{

    public Move1Card(int _priority){
        priority = _priority;
    }
    @Override
    void DoAction(Robot me, Board board) {
        if (board.CanGo(me.d, new Coordinate(me.posX, me.posY))) {
            me.moveForward();
        }
    }
}

class Move2Card extends Card{
    public Move2Card(int _priority){
        cardSprite = 1;
        priority = _priority;
    }
    @Override
    void DoAction(Robot me, Board board) {
        if (board.CanGo(me.d, new Coordinate(me.posX, me.posY))) {
            me.moveForward();
            if (board.CanGo(me.d, new Coordinate(me.posX, me.posY))) {
                me.moveForward();
            }
        }
    }
}
class Move3Card extends Card{
    public Move3Card(int _priority){
        priority = _priority;
        cardSprite = 2;
    }
    @Override
    void DoAction(Robot me, Board board) {
        if (board.CanGo(me.d, new Coordinate(me.posX, me.posY))) {
            me.moveForward();
            if (board.CanGo(me.d, new Coordinate(me.posX, me.posY))) {
                me.moveForward();
                if (board.CanGo(me.d, new Coordinate(me.posX, me.posY))) {
                    me.moveForward();
                }
            }
        }
    }
}

class MoveBackCard extends Card{
    public MoveBackCard(int _priority){
        priority = _priority;
        cardSprite = 6;
    }
    @Override
    void DoAction(Robot me, Board board) {
        Direction reverse = me.d;
        switch (me.d){
            case UP: reverse = Direction.DOWN; break;
            case DOWN: reverse = Direction.UP; break;
            case LEFT: reverse = Direction.RIGHT; break;
            case RIGHT: reverse = Direction.LEFT; break;
        }
        if (board.CanGo(reverse, new Coordinate(me.posX, me.posY))) {
            me.moveBackwards();
        }
    }
}

class TurnRightCard extends Card{
    public TurnRightCard(int _priority){
        priority = _priority;
        cardSprite = 4;
    }
    @Override
    void DoAction(Robot me, Board board) {
        me.turnRight();
    }
}
class TurnLeftCard extends Card{
    public TurnLeftCard(int _priority){
        priority = _priority;
        cardSprite = 3;
    }
    @Override
    void DoAction(Robot me, Board board) {
        me.turnLeft();
    }
}
class UTurnCard extends Card{
    public UTurnCard(int _priority){
        priority = _priority;
        cardSprite = 5;
    }
    @Override
    void DoAction(Robot me, Board board) {
        me.turnRight();
        me.turnRight();
    }
}
