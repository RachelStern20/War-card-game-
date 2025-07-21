import javax.swing.*;
/**
 * The Game class simulates a full game of "War" between two players using a standard 52-card deck.
 * It handles card distribution, turn-based logic, wars, and displays the outcome of each round.
 */
public class Game {

    /**
     * Entry point of the program. Initializes the deck, splits it between two players, and manages the game loop,
     * including regular rounds, wars, shuffling, and game termination.
     */
    public static void main(String[] args)
    {
       final int NUM_OF_CARDS_TO_WAR = 3;
       final int NUMBER_OF_CARDS = 52;

       int turnCount = 0;
       boolean isWar = false;
       String cards;
       Card player1Card = null;
       Card player2Card = null;
        // Temporary deck to collect cards during war
       DeckOfCards warCards  = new DeckOfCards();
       warCards.clearDeck();
       // Starting a new game - Create and shuffle a new Deck of cards.
      DeckOfCards fullDeck  = new DeckOfCards();
      fullDeck.shuffle();
       // Create empty decks for both players
      DeckOfCards player1  = new DeckOfCards();
      DeckOfCards player2  = new DeckOfCards();
      player1.clearDeck();
      player2.clearDeck();

      // Distribute 26 cards to each player
      for (int i = 0; i < NUMBER_OF_CARDS; i++) {
          if (i % 2 == 0)
              player1.addCard(fullDeck.dealCard());
          else
              player2.addCard(fullDeck.dealCard());
        }

      // Start the game loop
      while (!player1.isEmpty() && !player2.isEmpty())
      {
          turnCount++;

          // is_war will be true if in the last round ended in a tie.
          if (isWar)
          {
              // Check if players have enough cards to continue war
              if (player1.sizeOfDeck() < NUM_OF_CARDS_TO_WAR && player2.sizeOfDeck() < NUM_OF_CARDS_TO_WAR)
              {
                  // Both can't continue – declare tie
                  showEndMessage("0", turnCount);
                  break;
              }
              else if (player1.sizeOfDeck() < NUM_OF_CARDS_TO_WAR)
              {
                  // Player 1 can't continue
                  showEndMessage("2", turnCount);
                  break;
              } else if (player2.sizeOfDeck() < NUM_OF_CARDS_TO_WAR)
              {
                  // Player 2 can't continue
                  showEndMessage("1", turnCount);
                  break;
              }
              else
              {
                  // Both players place 3 cards for the war
                  Card player1Card1 = player1.dealCard();
                  Card player2Card1 = player2.dealCard();
                  Card player1Card2 = player1.dealCard();
                  Card player2Card2 = player2.dealCard();
                  Card player1Card3 = player1.dealCard();
                  Card player2Card3 = player2.dealCard();

                  // Compare the last cards played
                  int lastVal1 = player1Card3.getCardValue();
                  int lastVal2 = player2Card3.getCardValue();


                  // Add all 8 cards to war pile (including initial tie cards)
                  warCards.addCard(player1Card);
                  warCards.addCard(player2Card);
                  warCards.addCard(player1Card1);
                  warCards.addCard(player2Card1);
                  warCards.addCard(player1Card2);
                  warCards.addCard(player2Card2);
                  warCards.addCard(player1Card3);
                  warCards.addCard(player2Card3);

                  warCards.shuffle(); // Shuffle war pile before adding to winner

                  // Prepare message for dialog
                  cards = "Player 1's Card's are:\n" + player1Card1.toString() + "\n" + player1Card2.toString() + "\n"
                          + player1Card3.toString() + "\nPlayer 2's Card's are:\n" + player2Card1.toString() + "\n"
                  + player2Card2.toString() + "\n" + player2Card3.toString() + "\n\nPlayer 1 has " + player1.sizeOfDeck()
                          + " cards left.\n" + "Player 2 has " + player2.sizeOfDeck() + " cards left.\n";

                  // Determine winner of the war
                  if (lastVal1 > lastVal2)
                  {
                      player1.addDeck(warCards);
                      showMessageDialog("1", cards, true, turnCount);
                      warCards.clearDeck();
                      isWar = false;
                  }
                  else if (lastVal2 > lastVal1)
                  {
                      player2.addDeck(warCards);
                      showMessageDialog("2", cards, true, turnCount);
                      warCards.clearDeck();
                      isWar = false;
                  }
                  else
                  {
                      // Another tie – continue for another war
                      showMessageDialog("0", cards, true, turnCount);
                  }
              }
          }
          // Regular round
          else
          {
              player1Card = player1.dealCard();
              player2Card = player2.dealCard();

              int val1 = player1Card.getCardValue();
              int val2 = player2Card.getCardValue();

              // Build message for dialog
              cards = "Player 1 played: " + player1Card.toString() + "\nPlayer 2 played: "
                      + player2Card.toString() + "\n\nPlayer 1 has " + player1.sizeOfDeck() + " cards left.\n" +
                      "Player 2 has " + player2.sizeOfDeck() + " cards left.\n";

              // Determine winner
              if (val1 > val2)
              {
                  player1.addCard(player1Card);
                  player1.addCard(player2Card);
                  showMessageDialog("1", cards, isWar, turnCount);
              }
              else if (val2 > val1)
              {
                  player2.addCard(player2Card);
                  player2.addCard(player1Card);
                  showMessageDialog("2", cards, isWar, turnCount);
              }
              else
              {
                  // Tie – trigger war next round
                  isWar = true;
                  showMessageDialog("0", cards, false, turnCount);
              }
          }
      }
      // Check for end of game
      if (player1.isEmpty())
      {
          showEndMessage("2", turnCount);
      }
      // game cant be over with 2 empty
      else if (player2.isEmpty())
      {
         showEndMessage("1", turnCount);
      }
    // Else – we exited with a break and already printed an end message.
    }

    /**
     * Displays a message dialog showing the details of the current round,
     * including the cards played, number of cards left, and the winner or war status.
     *
     * @param won       The winning player ("1", "2", or "0" for tie).
     * @param cards     A string describing the cards played.
     * @param isWar    Whether this round is part of a war sequence.
     * @param turnCount The current turn number.
     */
    public static void showMessageDialog(String won, String cards, boolean isWar, int turnCount)
    {
            String title = "Turn " + turnCount;

            // "0" means tie, war in the next round.
            if (won.equals("0"))
            {
                if (isWar)// If it's a tie, and also it's a war - a tie in war is another war.
                {
                    JOptionPane.showMessageDialog(null, cards + "\nStarting another war.", title + " - WAR"
                            ,  JOptionPane.PLAIN_MESSAGE);

                }
                else
                {
                    JOptionPane.showMessageDialog(null, cards + "\nStarting a war.", title + " - regular"
                            ,  JOptionPane.PLAIN_MESSAGE);
                }

            }
            else
            // Not a tie
            {
                if (isWar)
                {
                    JOptionPane.showMessageDialog(null, "WAR\n" + cards + "\nPlayer " + won + " won.", title + " - WAR"
                            ,  JOptionPane.PLAIN_MESSAGE);
                }
                else // Not war
                {
                    JOptionPane.showMessageDialog(null, cards + "\nPlayer " + won + " won.", title + " - regular"
                            ,  JOptionPane.PLAIN_MESSAGE);
                }

            }

    }

    /**
     * Displays the final game result once the game ends, including the winner or if it was a tie.
     *
     * @param winner    The winning player ("1", "2", or "0" for tie).
     * @param turnCount Total number of turns played.
     */
    public static void showEndMessage(String winner, int turnCount)
    {
        // Game ended in a tie
        if (winner.equals("0"))
        {
            JOptionPane.showMessageDialog(null, "Game over after " + turnCount + " turns. It's a tie!", "End"
                    , JOptionPane.PLAIN_MESSAGE);
        }
        // Game ended with a winner
        else
        {
            JOptionPane.showMessageDialog(null, "Game ended after " + turnCount + " turns, player " + winner + " won.", "End"
                    , JOptionPane.PLAIN_MESSAGE);
        }

    }
}






