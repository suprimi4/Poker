package org.example.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();
    private int currentIndex = 0;

    public Deck() {
        initDeck();
        shuffle();
    }

    public void initDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add (new Card(rank,suit));
            }
        }
       currentIndex = 0;
    }

    public void shuffle() {
        Collections.shuffle(cards);
        currentIndex = 0;
    }

    public Card nextCards() {
        if (currentIndex >= cards.size()) {
            throw new InvalidPokerBoardException("В колоде больше нет карт.");
        }
        return cards.get(currentIndex++);
    }

}
