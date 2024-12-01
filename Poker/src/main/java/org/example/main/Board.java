package org.example.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Покерный стол
 */
public class Board {
    /**
     * Две карты первого игрока
     */
    private final List<Card> playerOne;
    /**
     * Две карты второго игрока
     */
    private final List<Card> playerTwo;
    /**
     * Три карты, открываются после раздачи карт игрокам
     */
    private final List<Card> flop;
    /**
     * Одна карта, открывается после flop
     */
    private final List<Card> turn;
    /**
     * Одна карта, открывается после turn
     */
    private final List<Card> river;

    public List<Card> getPlayerOne() {
        return Collections.unmodifiableList(playerOne);
    }

    public List<Card> getPlayerTwo() {
        return Collections.unmodifiableList(playerTwo);
    }

    public List<Card> getFlop() {
        return Collections.unmodifiableList(flop);
    }

    public List<Card> getTurn() {
        return Collections.unmodifiableList(turn);
    }

    public List<Card> getRiver() {
        return Collections.unmodifiableList(river);
    }

    public Board(List<Card> playerOne, List<Card> playerTwo, List<Card> flop, List<Card> turn, List<Card> river) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.flop = flop;
        this.turn = turn;
        this.river = river;
    }

    public Board(String playerOne, String playerTwo, String flop, String turn, String river) {

        this.playerOne = parseStringToCard(playerOne);
        this.playerTwo = parseStringToCard(playerTwo);
        this.flop = parseStringToCard(flop);
        this.turn = parseStringToCard(turn);
        this.river = parseStringToCard(river);
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


    @Override
    public String toString() {
        return "main.Board{" +
                "playerOne='" + playerOne + '\'' +
                ", playerTwo='" + playerTwo + '\'' +
                ", flop='" + flop + '\'' +
                ", turn='" + turn + '\'' +
                ", river='" + river + '\'' +
                '}';
    }
}