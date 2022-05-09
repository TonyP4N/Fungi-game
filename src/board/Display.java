package board;

import cards.Card;

import java.util.ArrayList;

public class Display implements Displayable{

    private final ArrayList<Card> displayList = new ArrayList<>();

    public void add(Card cards) {
        displayList.add(cards);
    }

    public int size() {
        return displayList.size();
    }

    public Card getElementAt(int index) {
        return displayList.get(index);
    }

    public Card removeElement(int index) {
        return displayList.remove(index);
    }
}
