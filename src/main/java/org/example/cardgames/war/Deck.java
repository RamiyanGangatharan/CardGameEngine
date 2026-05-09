package org.example.cardgames.war;

import java.util.ArrayList;
import java.util.Random;

// https://medium.com/@ankitviddya/designing-deck-of-cards-data-structure-in-java-using-object-oriented-principles-606f1e100d06
public class Deck {
    private ArrayList<Card> cards;

    // TODO: create a hashmap linking a card to its specified image.

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle(cards);
    }

    private void initializeDeck() {
        for  (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    // https://www.geeksforgeeks.org/dsa/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
    private void shuffle(ArrayList<Card> cards) {
        Random random = new Random();
        // start from the last element and swap one by one
        for (int i = cards.size() - 1; i >= 0; i--) {
            // pick a number from 0 -> i
            int j = random.nextInt(i + 1);

            // swap the cards around
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new  IllegalStateException("No cards in Deck");
        }
        return cards.removeLast();
    }

    public int size() {
        return cards.size();
    }
}
