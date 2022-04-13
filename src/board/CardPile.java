package board;

import cards.Card;

import java.util.Collections;
import java.util.Stack;

public class CardPile {

    Stack<Card> cPile;

    public CardPile() {
        cPile = new Stack<Card>();
    }

    public void addCard(Card cards) {
        cPile.add(cards);
    }

    public Card drawCard() {
        return cPile.pop();
    }

    public void shufflePile() {
        Collections.shuffle(cPile);
    }

    public int pileSize() {
        return cPile.size();
    }

    public boolean isEmpty() {
        return cPile.empty();
    }
}
