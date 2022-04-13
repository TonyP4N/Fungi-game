package board;

import cards.Card;

public interface Displayable {

    void add(Card cards);

    int size();

    Card getElementAt(int index);

    Card removeElement(int index);

}
