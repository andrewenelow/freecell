package cs3500.freecell.model;

/**
 * This enum represents the suit of a card.
 */
public enum Suit {
  CLUBS, SPADES, DIAMONDS, HEARTS;


  public int suitValue() {
    return this.ordinal();
  }

}
