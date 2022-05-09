package board;

import cards.Card;

import java.util.ArrayList;

public class Hand implements Displayable{

    private final ArrayList<Card> handList = new ArrayList<>();

    @Override
    public void add(Card cards) {
        handList.add(cards);
    }

    @Override
    public int size() {
        return handList.size();
    }

    @Override
    public Card getElementAt(int index) {
        return handList.get(index);
    }

    @Override
    public Card removeElement(int index) {
        return handList.remove(index);
    }
}
