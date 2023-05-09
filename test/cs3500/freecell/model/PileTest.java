package cs3500.freecell.model;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.freecell.model.SimpleFreecellModel;

import static org.junit.Assert.assertEquals;

/**
 * This class tests methods in the Pile class.
 */
public class PileTest {
  SimpleFreecellModel<Card> model = new SimpleFreecellModel<Card>();
  ArrayList<Card> deck = new ArrayList<>();

  @Test
  public void getCard() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals("A♣", model.cascadePiles.get(0).getCard(0).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGetCard() {
    model.startGame(model.getDeck(),8,4,false);
    model.cascadePiles.get(0).getCard(20);
  }

  @Test
  public void getTopCard() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals("K♣", model.cascadePiles.get(0).getTopCard().toString());
  }

  @Test
  public void pileSize() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals(7, model.cascadePiles.get(0).pileSize());
  }

  @Test
  public void addCard() {
    Card aceSpade = new Card(Rank.ACE,Suit.SPADES,false);
    model.startGame(model.getDeck(),8,4,false);
    model.cascadePiles.get(0).addCard(aceSpade);
    assertEquals(8, model.cascadePiles.get(0).pileSize());
    assertEquals("A♠", model.cascadePiles.get(0).getTopCard().toString());

  }

  @Test
  public void removeCard() {
    model.startGame(model.getDeck(),8,4,false);
    model.cascadePiles.get(0).removeCard(model.getNumCardsInCascadePile(0) - 1);
    assertEquals(6, model.cascadePiles.get(0).pileSize());
    assertEquals("J♣", model.cascadePiles.get(0).getTopCard().toString());
  }
}