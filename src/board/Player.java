package board;

import cards.Card;
import cards.CardType;
import cards.Stick;

import java.util.ArrayList;

public class Player {

    private Hand hand;

    private Display display;

    private int score;

    private int handlimit;

    private int sticks;

    public Player() {

    }

    public int getScore() {
        return score;
    }

    public int getHandLimit() {
        return handlimit;
    }

    public int getStickNumber() {
        return sticks;
    }

    public void addSticks(int index) {
        for (int i=0; i<index; i++)  {
            sticks += 1;
            addCardtoDisplay(new Stick());
        }
    }

    public void removeSticks(int index) {
        for (int i=0; i<index; i++) {
            for(int k=0; k<getDisplay().size(); k++) {
                if (getDisplay().getElementAt(k).getType()== CardType.STICK) {
                    sticks -= 1;
                    getDisplay().removeElement(k);
                    break;
                }
            }
        }
    }

    public Hand getHand() {
        return hand;
    }

    public Display getDisplay() {
        return display;
    }

    public void addCardtoHand(Card cards) {
        hand.add(cards);
    }

    public void addCardtoDisplay(Card cards) {
        display.add(cards);
    }

    public boolean takeCardFromTheForest(int index) {
        return false;
    }

    public boolean takeFromDecay() {
        return false;
    }

    public boolean cookMushrooms(ArrayList<Card> cards) {
        return false;
    }

    public boolean sellMushrooms(String cardName, int index) {
        return false;
    }

    public boolean putPanDown() {
        return false;
    }




}

