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
    private ShapeRenderer shapeRenderer;

    // Returns the card set for a phase of the game. (whether chosen or locked in place due to damage)
    public Card getCard(int _phase, Random rand){
        if((lockedCards.size()) > 4-_phase){
            return lockedCards.get(4-_phase);
        }else{
            int newInt;
            while(cardInputs.inputs.size()-1<= _phase){
                newInt = rand.nextInt(hand.size());
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

    /*
    public void playCard(){
        hand.get(cardInputs.inputs.get(0)).DoAction(playerRobot);
        cardInputs.inputs.remove(0);
    }
    */

    public boolean canPlayCard(){
        return cardInputs.inputs.size() != 0;
    }

    public Player(Robot robot){
        cardInputs = new playerInputs();
        playerRobot = robot;
        shapeRenderer = new ShapeRenderer();
    }

    public void drawHand(SpriteBatch s,BitmapFont font){
        int yPos = 0;
        int xPos = 1000;
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
        s.begin();
        font.setColor(Color.GREEN);
        font.getData().setScale(2);
        for(int i = 0;  i < cardInputs.inputs.size(); i++){
            int x = 1010;
            int c = cardInputs.inputs.get(i);
            if(c > 6){
                x += 100;
            }
            int y = (cardInputs.inputs.get(i)%7*140 + 120);
            font.draw(s,Integer.toString(i+1),x,y);
        }
    }

    public void doAiTurn(Random rand){
        /*
        ArrayList<Integer> intList = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
        while(cardInputs.inputs.size() < 5){
            int r = rand.nextInt(intList.size());
            cardInputs.inputs.add(intList.get(r));
            intList.remove(r);
        }
        */
    }

    public boolean touchUp() {
        if(Gdx.input.getX() > 1000  && Gdx.input.getX() < 1200 && Gdx.input.getY() > 20){
            int selectedCard = 0;
            if(Gdx.input.getX() > 1100){
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
                }
            }
        }
        return true;
    }
}