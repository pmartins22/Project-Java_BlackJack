package domain;

import java.awt.*;

public class Card {
    private final Rank rank;
    private final Integer value;
    private final Suit suit;
    private final Color color;

    public Card(Rank rank, int value, Suit suit, Color color) {
        this.rank = rank;
        this.value = value;
        this.suit = suit;
        this.color = color;
    }

    public void logCard() {
        System.out.print(" | " + rank.getSymbol() + " " + suit.getSymbol() + " | ");
    }
    public Rank getRank() {
        return rank;
    }
    public Integer getValue() {
        return value;
    }
    public Suit getSuit() {
        return suit;
    }
    public Color getColor() {
        return color;
    }
}
