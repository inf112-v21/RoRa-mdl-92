package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Player {
    public List<Integer> cardsList = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> lockedCards = new ArrayList<Card>();
    public Robot playerRobot;
    public playerInputs cardInputs;
    public int currentFlag = 0;


    // Returns the card set for a phase of the game. (whether chosen or locked in place due to damage)
    public Card getCard(int _phase, Random rand){
        if(cardInputs.shutDown){return null;} // don't play card when shutdown
        if((lockedCards.size()) > 4-_phase){
            return lockedCards.get(4-_phase);
        }else{
            int newInt;
            while(cardInputs.inputs.size()<= _phase){
                newInt = rand.nextInt(hand.size()); // har allerede valgt alle kortene i honden din, går inn i uendelig loop
                if (!(cardInputs.inputs.contains(newInt))){
                    cardInputs.inputs.add(newInt);
                }
            }
        }
        return hand.get(cardInputs.inputs.get(_phase));
    }



    //Checks damage and locks cards in place if needed.
    public void LockCards(){
        if(playerRobot.Health < 6 && lockedCards.size() == 0){
            System.out.println("First Card Locked");
            lockedCards.add(hand.get(cardInputs.inputs.get(4)));
        }
        if(playerRobot.Health < 5 && lockedCards.size() == 1){
            System.out.println("Second Card Locked");
            lockedCards.add(hand.get(cardInputs.inputs.get(3)));
        }
        if(playerRobot.Health < 4 && lockedCards.size() == 2){
            System.out.println("Third Card Locked");
            lockedCards.add(hand.get(cardInputs.inputs.get(2)));
        }
        if(playerRobot.Health < 3 && lockedCards.size() == 3){
            System.out.println("Fourth Card Locked");
            lockedCards.add(hand.get(cardInputs.inputs.get(1)));
        }
        if(playerRobot.Health < 2 && lockedCards.size() == 4){
            System.out.println("Last Card Locked");
            lockedCards.add(hand.get(cardInputs.inputs.get(0)));
        }
        for (Card c : lockedCards){
            hand.remove(c);
        }
    }


    public boolean canPlayCard(){
        return cardInputs.inputs.size() != 0;
    }

    public Player(Robot robot){
        cardInputs = new playerInputs();
        playerRobot = robot;

    }

    public void drawHand(SpriteBatch s,BitmapFont font, ShapeRenderer shapeRenderer){
        int yPos = 0;
        int xPos = 1332;
        s.end();
        font.setColor(Color.WHITE);
        font.getData().setScale(1);
        for(Card c : hand){
            s.begin();
            c.draw(s,xPos,yPos);
            font.draw(s,Integer.toString(c.priority),xPos+45,yPos+125);
            s.end();
            if (Gdx.input.getX() > xPos  && Gdx.input.getX() < xPos+100 &&
                    1000-  Gdx.input.getY() < yPos + 140 &&
                    1000-  Gdx.input.getY() > yPos) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.rect(xPos, yPos, 100, 140, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD);
                shapeRenderer.end();
            }
            yPos += 140;
            if(yPos > 900){
                xPos += 100;
                yPos = 0;
            }
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if(Gdx.input.getX() > 1432  && Gdx.input.getX() < 1532 &&
                1000-  Gdx.input.getY() < 980 &&
                1000-  Gdx.input.getY() > 880){

            shapeRenderer.rect(1432, 880, 100, 100, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD);

        }
        else{
            shapeRenderer.rect(1432, 880, 100, 100, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        }
        if((Gdx.input.getX() > 1432  && Gdx.input.getX() < 1532 &&
                1000-  Gdx.input.getY() < 670 &&
                1000-  Gdx.input.getY() > 570)){
            shapeRenderer.rect(1432, 570, 100, 100, Color.GOLD, Color.GOLD, Color.GOLD, Color.GOLD);
        }
        else{
            shapeRenderer.rect(1432, 570, 100, 100, Color.FIREBRICK, Color.FIREBRICK, Color.FIREBRICK, Color.FIREBRICK);
        }

        shapeRenderer.end();
        s.begin();
        font.setColor(Color.BLACK);
        font.draw(s,"Shut Down",1442,950);
        font.draw(s,"PLAY TURN",1442,640);
        if(cardInputs.shutDown){
            font.setColor(Color.GREEN);
            font.getData().setScale(1.5f);
            font.draw(s,"X",1432,970);
            font.getData().setScale(1f);
        }
        s.end();
        for(Card c : lockedCards){
            s.begin();
            c.draw(s,xPos,yPos);
            font.draw(s,Integer.toString(c.priority),xPos+45,yPos+125);
            font.setColor(Color.GREEN);
            font.draw(s,"LOCKED",xPos+20,yPos+50);
            font.setColor(Color.WHITE);
            s.end();
            yPos += 140;
            if(yPos > 900){
                xPos += 100;
                yPos = 0;
            }
        }
        s.begin();
        font.setColor(Color.GREEN);
        font.getData().setScale(2);
        for(int i = 0;  i < cardInputs.inputs.size(); i++){
            int x = 1342;
            int c = cardInputs.inputs.get(i);
            if(c > 6){
                x += 100;
            }
            int y = (cardInputs.inputs.get(i)%7*140 + 120);
            font.draw(s,Integer.toString(i+1),x,y);
        }
    }


    public boolean touchUp() {
        if(Gdx.input.getX() > 1332  && Gdx.input.getX() < 1532 && Gdx.input.getY() > 20){
            int selectedCard = 0;
            if(Gdx.input.getX() > 1432){
                selectedCard = 7;
            }
            selectedCard += MathUtils.floor((1000 -  Gdx.input.getY())/140);
            if(selectedCard < hand.size()){
                //hand.get(selectedCard).DoAction(playerRobot);
                if(cardInputs.inputs.contains(selectedCard)){
                    cardInputs.inputs.remove(cardInputs.inputs.indexOf(selectedCard));
                }else{
                    if(cardInputs.inputs.size() >= 5-lockedCards.size()){
                        cardInputs.inputs.remove(cardInputs.inputs.size()-1);
                    }
                    cardInputs.inputs.add(selectedCard);
                    cardInputs.shutDown = false;
                }
            }
        }
        if (Gdx.input.getX() > 1432  && Gdx.input.getX() < 1532 &&
                1000-  Gdx.input.getY() < 980 &&
                1000-  Gdx.input.getY() > 880){
            if(cardInputs.shutDown){
                cardInputs.shutDown = false;
            }
            else{
                cardInputs.inputs.clear();
                cardInputs.shutDown = true;
            }
        }
        return (Gdx.input.getX() > 1432  && Gdx.input.getX() < 1532 &&
                1000-  Gdx.input.getY() < 670 &&
                1000-  Gdx.input.getY() > 570);
    }
}