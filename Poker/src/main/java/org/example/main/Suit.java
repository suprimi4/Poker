package org.example.main;

public enum Suit {
    HEARTS("♥", "H"),
    DIAMONDS("♦", "D"),
    CLUBS("♣", "C"),
    SPADES("♠", "S");
    private final String symbol;

    private final String value;

    public String getValue() {
        return value;
    }
    public static Suit fromSymbol(String symbol) {
        for (Suit suit : Suit.values()) {
            if (suit.getValue().equals(symbol)) {
                return suit;
            }
        }
        return null;
    }

    Suit(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return value;
    }

    @Override
    public String toString() {
        return symbol;
    }


}
