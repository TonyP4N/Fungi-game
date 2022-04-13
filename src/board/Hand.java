package board;

import cards.Card;

import java.util.ArrayList;

public class Hand implements Displayable{

    ArrayList<Card> handList;

    public void add(Card cards) {
        handList.add(cards);
    }

    public int size() {
        return handList.size();
    }

    public Card getElementAt(int index) {
        return handList.get(index);
    }

    public Card removeElement(int index) {
        return handList.remove(index);
    }
}
