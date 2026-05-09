package org.example.cardgames.war;

public class warLogic {
    /*
    https://www.flickr.com/photos/167981955@N07/albums/72157719137109124/
        TODO:
         1. Shuffle the deck
         2. Split evenly between two players (26 cards each)
         3. Each player flips top card
         4. Higher card wins both — winner takes cards to bottom of their deck
         5. On a tie (WAR):
            - Each player places 3 cards face down, then flips a 4th
            - Higher 4th card takes all 8 cards
            - If a player has fewer than 4 cards during war, they lose
         6. After every round, check if either player has 0 cards
         7. Player with all 52 cards wins
     */
    Deck deck = new Deck();
    int deckSize = deck.size();
}