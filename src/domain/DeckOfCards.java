package domain;

import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeckOfCards {
    private List<Card> deck;

    public DeckOfCards() {
        deck = new LinkedList<Card>();

        //add each suit to deck

        //HEARTS
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(Rank.values()[i], i, Suit.HEARTS);
            deck.add(card);
        }

        //DIAMONDS
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(Rank.values()[i], i, Suit.DIAMONDS);
            deck.add(card);
        }

        //CLUBS
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(Rank.values()[i], i, Suit.CLUBS);
            deck.add(card);
        }

        //SPADES
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(Rank.values()[i], i, Suit.SPADES);
            deck.add(card);
        }
    }

    public Card takeRandomCard() {
        int randomNumber = (int) (Math.random() * 53);
        Card card = deck.get(randomNumber);
        deck.remove(card);
        return card;
    }


}
