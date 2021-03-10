package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        }
    }
    public Card(int _priority){
        priority = _priority;
        if(cardSprites.size() == 0) {
            cardSprites.add(new Sprite(new Texture("src/assets/move1.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/move2.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/move3.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/rotate_left.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/rotate_right.png")));
            cardSprites.add(new Sprite(new Texture("src/assets/u_turn.png")));
        }
    }
    public void draw(SpriteBatch s,int x, int y){
        s.draw(cardSprites.get(cardSprite).getTexture(),x,y);
    }
    abstract void DoAction(Robot me);
}

class Move1Card extends Card{
    @Override
    void DoAction(Robot me) {
        me.moveForward();
    }
}

class Move2Card extends Card{
    public Move2Card(){
        cardSprite = 1;
    }
    @Override
    void DoAction(Robot me) {
        me.moveForward();
        me.moveForward();
    }
}
class Move3Card extends Card{
    public Move3Card(){
        cardSprite = 2;
    }
    @Override
    void DoAction(Robot me) {
        me.moveForward();
        me.moveForward();
        me.moveForward();
    }
}

class TurnRightCard extends Card{
    public TurnRightCard(){
        cardSprite = 4;
    }
    @Override
    void DoAction(Robot me) {
        me.turnRight();
    }
}
class TurnLeftCard extends Card{
    public TurnLeftCard(){
        cardSprite = 3;
    }
    @Override
    void DoAction(Robot me) {
        me.turnLeft();
    }
}
class UTurnCard extends Card{
    public UTurnCard(){
        cardSprite = 5;
    }
    @Override
    void DoAction(Robot me) {
        me.turnRight();
        me.turnRight();
    }
}
