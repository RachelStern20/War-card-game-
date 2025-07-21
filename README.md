# War Card Game

A Java implementation of the classic **War** card game using Object-Oriented Programming (OOP) and `ArrayList`.

## Game Rules
- The deck is evenly split between two players.
- In each round, both players reveal the top card of their decks.
- The player with the higher card wins both cards and places them at the bottom of their deck.
- In case of a tie (a "war"), each player places three face-down cards and a fourth card face-up.
  - The player with the higher fourth card wins all the cards in play.
  - If the fourth cards tie again, the war continues until one player wins.
- The game ends when one player collects all the cards or when a maximum number of rounds is reached.

## Project Structure
- **Card.java** – Represents a single card (suit and rank).
- **DeckOfCards.java** – Represents and manages the deck of cards.
- **Game.java** – Main class containing the `main` method and game logic.

## How to Run
1. Compile all Java files:
   ```bash
   javac Game.java Card.java DeckOfCards.java
   ```
2. Run the game:
   ```bash
   java Game
   ```

## Features
- Complete implementation of the "War" card game.
- Tie (war) scenarios are handled recursively until a winner emerges.
- Clean, structured output after each round.

## Technologies
- **Language:** Java  
- **Data Structures:** `ArrayList`  
- **Paradigm:** Object-Oriented Programming (OOP)
