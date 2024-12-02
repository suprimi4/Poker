package org.example.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Покерный стол
 */
public class Board {
    /**
     * Две карты первого игрока
     */
    private final String playerOne;
    /**
     * Две карты второго игрока
     */
    private final String playerTwo;
    /**
     * Три карты, открываются после раздачи карт игрокам
     */
    private final String flop;
    /**
     * Одна карта, открывается после flop
     */
    private final String turn;
    /**
     * Одна карта, открывается после turn
     */
    private final String river;

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public String getFlop() {
        return flop;
    }

    public String getTurn() {
        return turn;
    }

    public String getRiver() {
        return river;
    }

    public Board(String playerOne, String playerTwo, String flop, String turn, String river) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.flop = flop;
        this.turn = turn;
        this.river = river;
    }

    @Override
    public String toString() {
        return "Board{" +
                "playerOne='" + playerOne + '\'' +
                ", playerTwo='" + playerTwo + '\'' +
                ", flop='" + flop + '\'' +
                ", turn='" + turn + '\'' +
                ", river='" + river + '\'' +
                '}';
    }

    private List<Card> parseStringToCard(String cards) {
        List<Card> parseHand = new ArrayList<>();
        StringBuilder rank = new StringBuilder();
        Suit suitValue = null;
        Rank rankValue = null;

        for (int i = 0; i < cards.length(); i++) {
            char currentChar = cards.charAt(i);
            if (currentChar != 'H' && currentChar != 'D' && currentChar != 'C' && currentChar != 'S') {
                rank.append(currentChar);
            } else {
                for (Rank ranks : Rank.values()) {
                    if (ranks.getSymbol().equals(String.valueOf(rank))) {
                        rankValue = ranks;
                        break;
                    }
                }
                for (Suit suit : Suit.values()) {
                    if (suit.getValue().equals(String.valueOf(currentChar))){
                        suitValue = suit;
                        break;
                    }
                }
                parseHand.add(new Card(rankValue, suitValue));
                suitValue = null;
                rankValue = null;
                rank = new StringBuilder();
            }

        }
        return parseHand;
    }

    public List<Card> getPlayerOneCards() {
        return parseStringToCard(playerOne);
    }

    public List<Card> getPlayerTwoCards() {
        return parseStringToCard(playerTwo);
    }

    public List<Card> getFlopCards() {
        return parseStringToCard(flop);
    }

    public List<Card> getTurnCards() {
        return parseStringToCard(turn);
    }

    public List<Card> getRiverCards() {
        return parseStringToCard(river);
    }

}