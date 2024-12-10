package org.example.main;

import java.util.ArrayList;
import java.util.List;

public class BoardCardReceiver {
    public List<Card> getPlayerOneCards(Board board) {
        return parseStringToCard(board.getPlayerOne());
    }

    public List<Card> getPlayerTwoCards(Board board) {
        return parseStringToCard(board.getPlayerTwo());
    }

    public List<Card> getFlopCards(Board board) {
        return parseStringToCard(board.getFlop());
    }

    public List<Card> getTurnCards(Board board) {
        return parseStringToCard(board.getTurn());
    }

    public List<Card> getRiverCards(Board board) {
        return parseStringToCard(board.getRiver());
    }

    private List<Card> parseStringToCard(String cards) {
        List<Card> parsedCards = new ArrayList<>();
        StringBuilder rank = new StringBuilder();
        Suit suitValue = null;
        Rank rankValue = null;

        for (int i = 0; i < cards.length(); i++) {
            char currentChar = cards.charAt(i);
            if (currentChar != 'H' && currentChar != 'D' && currentChar != 'C' && currentChar != 'S') {
                rank.append(currentChar);
            } else {
                // Match rank
                for (Rank ranks : Rank.values()) {
                    if (ranks.getSymbol().equals(rank.toString())) {
                        rankValue = ranks;
                        break;
                    }
                }
                // Match suit
                for (Suit suit : Suit.values()) {
                    if (suit.getValue().equals(String.valueOf(currentChar))) {
                        suitValue = suit;
                        break;
                    }
                }
                // Add new card to the list
                if (rankValue != null && suitValue != null) {
                    parsedCards.add(new Card(rankValue, suitValue));
                }
                // Reset for next card
                rank = new StringBuilder();
                rankValue = null;
                suitValue = null;
            }
        }
        return parsedCards;
    }
}
