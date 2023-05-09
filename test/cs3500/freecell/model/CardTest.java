package cs3500.freecell.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests methods in the Card class.
 */
public class CardTest {

  @Test
  public void testToString() {
    Card aceSpade = new Card(Rank.ACE,Suit.SPADES,false);
    assertEquals("A♠", aceSpade.toString());
  }

  @Test
  public void validCascadeStack() {
    Card nineDiamonds = new Card(Rank.NINE,Suit.DIAMONDS,false);
    Card eightHearts = new Card(Rank.EIGHT,Suit.HEARTS,true);
    Card threeClubs = new Card(Rank.THREE,Suit.CLUBS,false);
    assertEquals(true,eightHearts.validCascadeStack(nineDiamonds));
    assertEquals(false,eightHearts.validCascadeStack(threeClubs));
  }

  @Test
  public void validFoundationStack() {
    Card nineDiamonds = new Card(Rank.NINE,Suit.DIAMONDS,false);
    Card eightDiamonds = new Card(Rank.EIGHT,Suit.DIAMONDS,false);
    Card threeClubs = new Card(Rank.THREE,Suit.CLUBS,false);
    assertEquals(true, nineDiamonds.validFoundationStack(eightDiamonds));
    assertEquals(false, nineDiamonds.validFoundationStack(threeClubs));
  }

  @Test
  public void getValue() {
    Card nineDiamonds = new Card(Rank.NINE,Suit.DIAMONDS,false);
    assertEquals(8,nineDiamonds.getValue());
  }
}