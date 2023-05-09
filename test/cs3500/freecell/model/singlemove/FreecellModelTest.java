package cs3500.freecell.model.singlemove;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for the simple freecell model.
 */
public class FreecellModelTest {
  SimpleFreecellModel<Card> model = new SimpleFreecellModel<Card>();
  ArrayList<Card> deck = new ArrayList<>();


  @Test
  public void getDeck() {
    assertEquals(52,model.getDeck().size());
  }

  @Test
  public void toStringBeforeGame() {
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("", view.toString());
  }

  @Test
  public void startGame() {
    model.startGame(model.getDeck(),8,4,false);
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C2: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C3: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
            "C4: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n" +
            "C5: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n" +
            "C6: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C7: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥", view.toString());
  }

  @Test
  public void startGameInGame() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 4, 5,PileType.CASCADE,3);
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C2: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C3: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
            "C4: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥, Q♣\n" +
            "C5: 2♣, 4♣, 6♣, 8♣, 10♣\n" +
            "C6: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C7: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥",view.toString());
    model.startGame(model.getDeck(),8,4,false);
    FreecellTextView view2 = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C2: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C3: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
            "C4: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n" +
            "C5: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n" +
            "C6: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C7: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥",view2.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameIllegalCascade() {
    model.startGame(model.getDeck(),3,4,false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameIllegalOpen() {
    model.startGame(model.getDeck(),8,0,false);
  }

  /*
  @Test
  public void startGameWithShuffle() {
    model.startGame(model.getDeck(),8,4,true);
  }

   */

  @Test
  public void moveCascadeToCascade() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 4, 5,PileType.CASCADE,3);
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C2: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C3: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
            "C4: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥, Q♣\n" +
            "C5: 2♣, 4♣, 6♣, 8♣, 10♣\n" +
            "C6: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C7: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥",view.toString());

  }

  @Test
  public void moveCascadeToOpen() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 4, 5,PileType.OPEN,3);
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4: Q♣\n" +
            "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C2: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C3: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
            "C4: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n" +
            "C5: 2♣, 4♣, 6♣, 8♣, 10♣\n" +
            "C6: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C7: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥",view.toString());
  }

  @Test
  public void moveOpenToCascade() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 4, 5,PileType.CASCADE,3);
    model.move(PileType.CASCADE, 2, 6,PileType.OPEN,3);
    model.move(PileType.CASCADE, 2, 5,PileType.OPEN,2);
    model.move(PileType.OPEN, 2, 0,PileType.CASCADE,3);
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4: K♦\n" +
            "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C2: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C3: A♦, 3♦, 5♦, 7♦, 9♦\n" +
            "C4: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥, Q♣, J♦\n" +
            "C5: 2♣, 4♣, 6♣, 8♣, 10♣\n" +
            "C6: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C7: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥",view.toString());
  }

  @Test
  public void moveCascadeToFoundation() {
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    model.startGame(reverseDeck,8,4,false);
    model.move(PileType.CASCADE, 0, 6,PileType.FOUNDATION,0);
    model.move(PileType.CASCADE, 4, 5,PileType.FOUNDATION,0);
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("F1: A♥, 2♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣",view.toString());
  }

  @Test
  public void moveOpenToFoundation() {
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    model.startGame(reverseDeck,8,4,false);
    model.move(PileType.CASCADE, 0, 6,PileType.OPEN,0);
    model.move(PileType.OPEN, 0, 0,PileType.FOUNDATION,0);
    FreecellTextView view = new FreecellTextView(model);
    assertEquals("F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣",view.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveFoundationToCascade() {
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    model.startGame(reverseDeck,8,4,false);
    model.move(PileType.CASCADE, 0, 6,PileType.OPEN,0);
    model.move(PileType.OPEN, 0, 0,PileType.FOUNDATION,0);
    model.move(PileType.FOUNDATION, 0, 0,PileType.CASCADE,6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveFoundationToOpen() {
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    model.startGame(reverseDeck,8,4,false);
    model.move(PileType.CASCADE, 0, 6,PileType.FOUNDATION,0);
    model.move(PileType.FOUNDATION, 0, 0,PileType.OPEN,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToCascade() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 4, 5,PileType.CASCADE,1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToOpen() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 0, 6,PileType.OPEN,0);
    model.move(PileType.CASCADE, 1, 6,PileType.OPEN,1);
    model.move(PileType.CASCADE, 2, 6,PileType.OPEN,2);
    model.move(PileType.CASCADE, 3, 6,PileType.OPEN,3);
    model.move(PileType.CASCADE, 4, 5,PileType.OPEN,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToFoundation() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 0, 6,PileType.FOUNDATION,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOpenToFoundation() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 4, 5,PileType.OPEN,3);
    model.move(PileType.OPEN, 3, 0,PileType.FOUNDATION,0);
  }

  @Test
  public void isGameOver() {
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    model.startGame(reverseDeck,8,4,false);
    assertEquals(false,model.isGameOver());

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 4; j++) {
        model.move(PileType.CASCADE, j, 6 - i,PileType.FOUNDATION,j);
      }
      if (i < 6) {
        for (int k = 0; k < 4; k++) {
          model.move(PileType.CASCADE, 4 + k, 5 - i,PileType.FOUNDATION,k);
        }
      }
    }

    assertEquals(true,model.isGameOver());
  }

  @Test
  public void getNumCardsInFoundationPile() {
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    model.startGame(reverseDeck,8,4,false);
    assertEquals(0,model.getNumCardsInFoundationPile(0));
    model.move(PileType.CASCADE, 0, 6,PileType.FOUNDATION,0);
    assertEquals(1,model.getNumCardsInFoundationPile(0));
  }

  @Test
  public void getNumCascadePiles() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals(8,model.getNumCascadePiles());
  }

  @Test
  public void getNumCardsInCascadePile() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals(7, model.getNumCardsInCascadePile(3));
    assertEquals(6, model.getNumCardsInCascadePile(4));
    model.move(PileType.CASCADE, 4, 5,PileType.CASCADE,3);
    assertEquals(8, model.getNumCardsInCascadePile(3));
    assertEquals(5, model.getNumCardsInCascadePile(4));
  }

  @Test
  public void getNumCardsInOpenPile() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals(0,model.getNumCardsInOpenPile(0));
    model.move(PileType.CASCADE, 4, 5,PileType.OPEN,0);
    assertEquals(1,model.getNumCardsInOpenPile(0));
  }

  @Test
  public void getNumOpenPiles() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals(4,model.getNumOpenPiles());
  }

  @Test
  public void getFoundationCardAt() {
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    model.startGame(reverseDeck,8,4,false);
    model.move(PileType.CASCADE, 0, 6,PileType.FOUNDATION,0);
    assertEquals("A♥",model.getFoundationCardAt(0,0).toString());
  }

  @Test
  public void getCascadeCardAt() {
    model.startGame(model.getDeck(),8,4,false);
    assertEquals("8♣",model.getCascadeCardAt(4,3).toString());
  }

  @Test
  public void getOpenCardAt() {
    model.startGame(model.getDeck(),8,4,false);
    model.move(PileType.CASCADE, 4, 5,PileType.OPEN,0);
    assertEquals("Q♣",model.getOpenCardAt(0).toString());
  }

  @Test
  public void getNumCascadePilesBeforeGame() {
    assertEquals(-1, model.getNumCascadePiles());
  }

  @Test
  public void getNumOpenPilesBeforeGame() {
    assertEquals(-1, model.getNumOpenPiles());
  }

}
