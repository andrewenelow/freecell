package cs3500.freecell.model;

/**
 * This class represents a single card.
 */
public class Card {
  private final Rank value;
  private final Suit suit;
  boolean color; //red = true, black = false

  /**
   * This is the constructor for the card class.
   * @param value This represents the value of a card.
   * @param suit This represents the suit of a card.
   * @param color This represents the color of a card.
   */
  public Card(Rank value, Suit suit, boolean color) {
    this.value = value;
    this.suit = suit; // 0 = Clubs, 1 = spades, 2 = diamonds, 3 = hearts
    this.color = color; //true = red, false = black
  }

  @Override
  public String toString() {
    String[] suits = {"♣", "♠", "♦", "♥"};
    String[] cardValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    return cardValues[getValue()] + suits[getSuitValue()];
  }

  /**
   * This method determines if the constructed card can be stacked on the given card in a cascade
   * pile.
   * @param c the card the gets the constructed card stacked on it.
   * @return a true if the card can be stacked.
   */
  public boolean validCascadeStack(Card c) {
    return (getValue() == (c.getValue() - 1) && color != c.color);
  }

  /**
   * This method determines if the constructed card can be stacked on the given card in a foundation
   * pile.
   * @param c the card the gets the constructed card stacked on it.
   * @return a true if the card can be stacked.
   */
  public boolean validFoundationStack(Card c) {
    return ((getValue() - 1) == c.getValue() && getSuitValue() == c.getSuitValue());
  }

  public int getSuitValue() {
    return suit.suitValue();
  }

  public int getValue() {
    return value.rankValue();
  }

}

