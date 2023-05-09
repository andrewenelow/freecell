package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a pile of cards.
 * The class is constructed with an ArrayList of cards and a PileType.
 */
public class Pile {
  private final ArrayList<Card> cards;
  private final PileType type;

  Pile(ArrayList<Card> cards, PileType type) {
    this.cards = cards;
    this.type = type;
  }

  /**
   * Returns a card from a pile at the given index.
   * @param index the index of the card in the pile.
   * @return returns the card at the given index.
   */
  public Card getCard(int index) {
    if (cards.size() == 0) {
      return null;
    }
    else if (cards.size() - 1 < index) {
      throw new IllegalArgumentException("Invalid card");
    }
    else {
      return cards.get(index);
    }
  }

  /**
   * Returns the top card from a pile.
   * @return returns the card at the top of the pile.
   */
  public Card getTopCard() {
    return cards.get(cards.size() - 1);
  }

  /**
   * Returns the number of cards in a pile.
   * @return returns the number of cards in a pile.
   */
  public int pileSize() {
    return cards.size();
  }

  /**
   * Adds a card to the constructed pile.
   * @param c the card being added.
   */
  public void addCard(Card c) {
    cards.add(c);
  }

  /**
   * Adds a list of cards to the constructed pile.
   * @param build the list of cards being added.
   */
  public void addCards(List<Card> build) {
    cards.addAll(build);
  }

  /**
   * Removes a card from the constructed pile.
   * @param index This is the index of the card that is removed.
   */
  public void removeCard(int index) {
    cards.remove(index);
  }

  /**
   * Removes a list of cards from the constructed pile.
   * @param build the list of cards being removed.
   */
  public void removeCards(List<Card> build) {
    cards.removeAll(build);
  }

  public int cardsInBuild(int cardIndex) {
    return cards.size() - cardIndex;
  }

  /**
   * Determines if a list of cards is in alternating descending order.
   * @param cardIndex The card index where the list starts.
   * @return Returns true if the build is in alternating descending order.
   */
  public boolean validBuild(int cardIndex) {
    for (int i = 0; i < cards.size() - cardIndex - 1; i++) {
      if (!(cards.get(cards.size() - i - 1).validCascadeStack(cards.get(cards.size() - i - 2)))) {
        return false;
      }
    }
    return true;
  }

}
