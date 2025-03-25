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

    /** Returns a random card and remove it from the deck. */
    public Card takeRandomCard() {
        int randomNumber = (int) (Math.random() * deck.size()); // Get a random number between 0 and the size of the deck - 1.
        Card card = deck.get(randomNumber); // Get the card in the random index.
        deck.remove(card); // Remove the card taken from the deck.

        // Deck and card taken log in terminal for debug.
        System.out.println("Deck: \n-----------------------------------------------------------------------------------");
        logDeck();
        System.out.println("Card taken: \n-----------------------------------------------------------------------------------");
        card.logCard();
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");


        return card; // Return the card taken.
    }

    public void logDeck() {
        Suit actualSuit = Suit.HEARTS;
        for (Card card : deck) {
            if (!card.getSuit().equals(actualSuit)) {
                actualSuit = card.getSuit();
                System.out.println();
            }
            card.logCard();
        }
        System.out.println();
    }


}
