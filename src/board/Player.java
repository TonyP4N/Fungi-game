package board;

import cards.*;

import java.util.ArrayList;

public class Player {

    private final Hand hand;

    private final Display display;

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
        int panCount = 0;
        int dayMushCount = 0;
        int nightMushCount = 0;
        int butterCount = 0;
        int ciderCount = 0;
        int cardsNeed;
        int butterScore;
        int ciderScore;
        int mushScore;
        ArrayList<String> mushName = new ArrayList<>();
        for (Card card : cards) {
            if (card.getType().equals(CardType.DAYMUSHROOM) || card.getType().equals(CardType.NIGHTMUSHROOM)) {
                mushName.add(card.getName());
            }
            if (card.getType().equals(CardType.DAYMUSHROOM)) {
                dayMushCount++;
            }
            if (card.getType().equals(CardType.NIGHTMUSHROOM)) {
                nightMushCount++;
            }
            if (card.getType().equals(CardType.PAN)) {
                panCount++;
            }
            if (card.getType().equals(CardType.BUTTER)) {
                butterCount ++;
            }
            if (card.getType().equals(CardType.CIDER)) {
                ciderCount ++;
            }
        }
        if ((dayMushCount + nightMushCount * 2) < 3) {
            return false;
        }

        for (int i=0; i<getDisplay().size(); i++) {
            if (getDisplay().getElementAt(i).getType().equals(CardType.PAN)) {
                panCount ++;
            }
        }
        if (panCount <= 0) {
            return false;
        }
        for (int j=0; j<mushName.size()-1; j++) {
            if (!mushName.get(j).equals(mushName.get(j + 1))) {
                return false;
            }
        }
        for (Card card : cards) {
            if (card.getType().equals(CardType.BASKET)) {
                return false;
            }
        }
        cardsNeed = butterCount * 4 + ciderCount * 5;
        if (dayMushCount + nightMushCount * 2 < cardsNeed) {
            return false;
        }
        butterScore = butterCount * 3;
        ciderScore = ciderCount * 5;
        mushScore = new EdibleItem(CardType.DAYMUSHROOM, mushName.get(1)).getFlavourPoints();
        mushScore = mushScore * dayMushCount + mushScore * (2 * nightMushCount);
        score += butterScore + ciderScore + mushScore;
        return true;
    }

    public boolean sellMushrooms(String cardName, int index) {
        int dayMushCount = 0;
        int nightMushCount = 0;
        cardName = cardName.replaceAll("[^A-Za-z]", "").toLowerCase();
        for (int i=0; i<getHand().size(); i++) {
            if (getHand().getElementAt(i).getName().equals(cardName)) {
                if (getHand().getElementAt(i).getType().equals(CardType.DAYMUSHROOM)) {
                    dayMushCount ++;
                }
                if (getHand().getElementAt(i).getType().equals(CardType.NIGHTMUSHROOM)) {
                    nightMushCount ++;
                }
            }
        }
        if ((nightMushCount * 2 + dayMushCount) < index || index < 2) {
            return false;
        }
        int mushSticks = new Mushroom(CardType.DAYMUSHROOM, cardName).getSticksPerMushroom();
        if (nightMushCount == 1 && index == 2) {
            addSticks(mushSticks * index);
            for (int j = 0; j < getHand().size(); j++) {
                if (getHand().getElementAt(j).getName().equals(cardName)) {
                    if (getHand().getElementAt(j).getType().equals(CardType.NIGHTMUSHROOM)) {
                        getHand().removeElement(j);
                    }
                }
            }
        }
        if (nightMushCount == 1 && index > 2) {
            addSticks(mushSticks * 2);
            for (int j = 0; j < getHand().size(); j++) {
                if (getHand().getElementAt(j).getName().equals(cardName)) {
                    if (getHand().getElementAt(j).getType().equals(CardType.NIGHTMUSHROOM)) {
                        getHand().removeElement(j);
                    }
                }
            }
            addSticks(mushSticks * (index - 2));
            for (int k = 0; k < getHand().size(); k++) {
                if (getHand().getElementAt(k).getName().equals(cardName)) {
                    if (getHand().getElementAt(k).getType().equals(CardType.DAYMUSHROOM)) {
                        getHand().removeElement(k);
                    }
                }
            }
        }
        if (nightMushCount == 0) {
            addSticks(mushSticks * index);
            for (int l=0; l<getHand().size(); l++) {
                if (getHand().getElementAt(l).getName().equals(cardName)) {
                    if (getHand().getElementAt(l).getType().equals(CardType.DAYMUSHROOM)) {
                        getHand().removeElement(l);
                        l -= 1;
                    }
                }
            }
        }
        return true;
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

