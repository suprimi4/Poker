package org.example.main;

public enum Suit {
    HEARTS("♥","H"),
    DIAMONDS("♦", "D"),
    CLUBS("♣", "C"),
    SPADES("♠", "S");
    private final String symbol;

    private final String value;

    public String getValue() {
        return value;
    }

    Suit(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
