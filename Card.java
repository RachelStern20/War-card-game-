// Card class represents a playing card
public class Card 
{
    private final String face; //face of card ("Ace", "Deuce", ...)
    private final String suit;//suit of the card ("Hearts", "Diamonds", ...)
    
    // Two argument constructor initializes card's face and suit
    public Card(String cardFace, String cardSuit)
    {
        this.face = cardFace;
        this.suit = cardSuit;
    }

    // Return String representation of Card
    public String toString()
    {
        return face + " of " + suit;
    }

    // Return the value of a card in int
    public int getCardValue() {
        String[] faces = { "Ace", "Deuce", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
        
        for(int i = 0; i < faces.length; i++) 
        {
            if(face.equals(faces[i]))
                return i + 1;
        }
        return 0;
    }
}
