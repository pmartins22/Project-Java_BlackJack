package domain;

import java.awt.*;
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
            Card card = new Card(Rank.values()[i-1], i, Suit.HEARTS, Color.red);
            deck.add(card);
        }

        //DIAMONDS
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(Rank.values()[i-1], i, Suit.DIAMONDS, Color.red);
            deck.add(card);
        }

        //CLUBS
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(Rank.values()[i-1], i, Suit.CLUBS, Color.BLACK);
            deck.add(card);
        }

        //SPADES
        for (int i = 1; i <= 13; i++) {
            Card card = new Card(Rank.values()[i-1], i, Suit.SPADES, Color.BLACK);
            deck.add(card);
        }
    }

    public Card takeRandomCard() {
        int randomNumber = (int) (Math.random() * deck.size());
        Card card = deck.get(randomNumber);
        deck.remove(card);
        return card;
    }


}
