package domain;

public class Card {
    private final Rank rank;
    private final Integer value;
    private final Suit suit;

    public Card(Rank rank, int value, Suit suit) {
        this.rank = rank;
        this.value = value;
        this.suit = suit;
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
}
