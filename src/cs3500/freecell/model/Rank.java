package cs3500.freecell.model;

/**
 * This enum represents the rank of a card.
 */
public enum Rank {
  ACE,
  TWO,
  THREE,
  FOUR,
  FIVE,
  SIX,
  SEVEN,
  EIGHT,
  NINE,
  TEN,
  JACK,
  QUEEN,
  KING;

  public int rankValue() {
    return this.ordinal();
  }

}
