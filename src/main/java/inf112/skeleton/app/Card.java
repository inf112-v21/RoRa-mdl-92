package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

//creates cards that can be manually chosen and executed in wanted order
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

//card that moves 1 step forward
class Move1Card extends Card{

    public Move1Card(int _priority){
        priority = _priority;
    }
    @Override
    void DoAction(Robot me, Board board) {
            me.moveForward(board);
    }
}
//card that moves 2 steps forward
class Move2Card extends Card{
    public Move2Card(int _priority){
        cardSprite = 1;
        priority = _priority;
    }
    @Override
    void DoAction(Robot me, Board board) {
        me.moveForward(board);
        me.moveForward(board);
    }
}
//card that moves 3 steps forward
class Move3Card extends Card{
    public Move3Card(int _priority){
        priority = _priority;
        cardSprite = 2;
    }
    @Override
    void DoAction(Robot me, Board board) {
        me.moveForward(board);
        me.moveForward(board);
        me.moveForward(board);
    }
}
//card that moves 1 step backwards
class MoveBackCard extends Card{
    public MoveBackCard(int _priority){
        priority = _priority;
        cardSprite = 6;
    }
    @Override
    void DoAction(Robot me, Board board) {
            me.moveBackwards(board);
    }
}
//card that turns right
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
//card that turns left
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
//card that makes a U-turn
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
