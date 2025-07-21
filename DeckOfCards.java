import java.security.SecureRandom;
import java.util.ArrayList;
/**
 * Represents a standard deck of 52 playing cards.
 * Supports shuffling, dealing, clearing, and adding cards or other decks.
 */
public class DeckOfCards {
    private ArrayList<Card> deck;
    private static final int NUMBER_OF_CARDS = 52;
    private static final SecureRandom randomNumbers = new SecureRandom();

    /**
     * Constructs a full deck of 52 cards (13 ranks × 4 suits).
     * Initializes the deck with all possible card combinations.
     */
    public DeckOfCards() {
        deck = new ArrayList<Card>();
        String[] faces = { "Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
        String[] suits = { "Hearts ♥", "Diamonds ♦", "Clubs ♣", "Spades ♠" };
        // Populate deck with Card objects
        for (int count = 0; count < NUMBER_OF_CARDS; count++)
            deck.add(new Card(faces[count % 13], suits[count / 13]));
    }

    // Clear the deck
    public void clearDeck() {
        deck.clear();
    }

    // Shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        // Next call to method dealCard should start at deck[0] again
        // For each Card, pick another random Card (0-51) and swap them
        for(int first = 0; first < deck.size(); first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(deck.size());
            // swap current Card with randomly selected Card
            Card temp = deck.get(first);
            deck.set(first,deck.get(second));
            deck.set(second,temp);
        }
    }

    // Deal one Card
    public Card dealCard() {
        // Determine whether Cards remain to be dealt
        if (!deck.isEmpty())
            return deck.remove(0); // Return the top card in array
        return null; // Return null to indicate that all Cards were dealt
    }

    // Return the size of the deck
    public int sizeOfDeck () {
        return deck.size();
    }

    // Return if the deck is empty
    public boolean isEmpty () {
        return deck.isEmpty();
    }

    // Add card to the deck
    public void addCard(Card card) {
        deck.add(card);
    }
    // Add entire deck other to the deck
    public void addDeck(DeckOfCards  other) {
        while (!other.isEmpty())
            deck.add(other.dealCard());
    }
}