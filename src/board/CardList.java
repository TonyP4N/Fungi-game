package board;

import cards.Card;

import java.util.ArrayList;

public class CardList {

    private final ArrayList<Card> cList;

    public CardList() {
        cList = new ArrayList<>();
    }

    public void add(Card cards) {
        cList.add(cards);
    }

    public int size() {
        return cList.size();
    }

    public Card getElementAt(int index) {
        return cList.get(index);
    }

    public Card removeCardAt(int index) {
        return cList.remove(index);
    }
}
