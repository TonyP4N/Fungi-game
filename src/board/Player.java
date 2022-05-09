package board;

import cards.*;

import java.util.ArrayList;

public class Player {

    private final Hand hand;

    private Display display;

    private int score;

    private int handlimit;

    private int sticks;

    public Player() {
        hand = new Hand();
        display = new Display();
        score = 0;
        handlimit = 8;
        sticks = 0;
        addCardtoDisplay(new Pan());
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
        if (cards.getType().equals(CardType.BASKET)) {
            addCardtoDisplay(cards);
            handlimit += 2;
        }
        else {
            hand.add(cards);
        }
    }

    public void addCardtoDisplay(Card cards) {
        display.add(cards);
    }

    public boolean takeCardFromTheForest(int index) {
        if (getHand().size() > getHandLimit()) {
            return false;
        } else if (getHand().size() == getHandLimit()) {
            if (!Board.getForest().getElementAt(8 - index).getType().equals(CardType.BASKET)) {
                return false;
            }
            if (!Board.getForest().getElementAt(8 - index).getType().equals(CardType.STICK)) {
                return false;
            }
        }
        if (index == 1 || index == 2) {
                if (Board.getForest().getElementAt(8 - index).getType().equals(CardType.BASKET)) {
                    addCardtoDisplay(new Basket());
                    handlimit += 2;
                } else if (Board.getForest().getElementAt(8 - index).getType().equals(CardType.STICK)) {
                    addSticks(1);
                } else {
                    addCardtoHand(Board.getForest().getElementAt(8 - index));
                }
                Board.getForest().removeCardAt(index);
            }
        int stickNeed = index - 2;
        if (index != 1 && index != 2) {
            if (sticks < stickNeed) {
                return false;
            }
            if (Board.getForest().getElementAt(8 - index).getType().equals(CardType.BASKET)) {
                addCardtoDisplay(new Basket());
                handlimit += 2;
                Board.getForest().removeCardAt(index);
                removeSticks(stickNeed);
            } else if (Board.getForest().getElementAt(8 - index).getType().equals(CardType.STICK)) {
                addSticks(1);
                Board.getForest().removeCardAt(index);
                removeSticks(stickNeed);
            } else {
                addCardtoHand(Board.getForest().getElementAt(8 - index));
                Board.getForest().removeCardAt(index);
                removeSticks(stickNeed);
            }
        }
        return true;
    }

    public boolean takeFromDecay() {
        int basketN = 0;
        for (Card card : Board.getDecayPile()) {
            if (card.getType().equals(CardType.BASKET)) {
                basketN += 1;
            }
        }

        int stickN = 0;
        for (Card card : Board.getDecayPile()) {
            if (card.getType().equals(CardType.STICK)) {
                stickN += 1;
            }
        }

        if ((getHand().size() + (Board.getDecayPile().size() - basketN - stickN)) <= (basketN * 2 + getHandLimit())) {
            for (Card card : Board.getDecayPile()) {
                addCardtoHand(card);
            }
            Board.getDecayPile().clear();
            return true;
        }
        return false;
    }

    public boolean cookMushrooms(ArrayList<Card> cards) {
        for (int i=0; i<getDisplay().size(); i++) {
            if (!getDisplay().getElementAt(i).getType().equals(CardType.PAN)) {
                return false;
            }
        }
        return false;
    }

    public boolean sellMushrooms(String cardName, int index) {
        return false;
    }

    public boolean putPanDown() {
        for (int i=0; i<getHand().size(); i++) {
            if (getHand().getElementAt(i).getType().equals(CardType.PAN)) {
                addCardtoDisplay(new Pan());
                getHand().removeElement(i);
                return true;
            }
        }
        return false;
    }
}

