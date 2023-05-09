package cs3500.freecell.controller;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods for the freecell controller.
 */
public class SimpleFreecellControllerTest {

  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    SimpleFreecellController ctrl = new SimpleFreecellController(null,
            new InputStreamReader(System.in), System.out);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullReadable() {
    SimpleFreecellController ctrl = new SimpleFreecellController(new SimpleFreecellModel<Card>(),
            null, System.out);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    SimpleFreecellController ctrl = new SimpleFreecellController(new SimpleFreecellModel<Card>(),
            new InputStreamReader(System.in), null);
  }

  @Test (expected = IllegalStateException.class)
  public void testFailedReadable() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable badReadbale = new MockReadable();
    Appendable testAppendable = new StringBuilder("");
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            badReadbale, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
  }

  @Test (expected = IllegalStateException.class)
  public void testFailedAppendable() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("");
    Appendable badAppendable = new MockAppendable();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testReadable, badAppendable);
    ctrl.playGame(deck, 8, 4, false);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullDeck() throws IOException {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("");
    Appendable testAppendable = new StringBuilder("");
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(null, 8, 4, false);
  }

  @Test
  public void testQuitFirstInput() throws IOException {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testQ = new StringReader("Q");
    Readable testLowerQ = new StringReader("q");
    Appendable testAppendable = new StringBuilder();
    Appendable testAppendable2 = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testQ, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable.toString());
    SimpleFreecellController ctrl2 = new SimpleFreecellController(model,
            testLowerQ, testAppendable2);
    ctrl2.playGame(deck, 8, 4, false);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable2.toString());
  }

  @Test
  public void testQuitSecondInput() throws IOException {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testQ = new StringReader(" C8 Q");
    Readable testLowerQ = new StringReader("C8 q");
    Appendable testAppendable = new StringBuilder();
    Appendable testAppendable2 = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testQ, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable.toString());
    SimpleFreecellController ctrl2 = new SimpleFreecellController(model,
            testLowerQ, testAppendable2);
    ctrl2.playGame(deck, 8, 4, false);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable2.toString());
  }

  @Test
  public void testQuitThirdInput() throws IOException {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testQ = new StringReader(" C8 6 Q");
    Readable testLowerQ = new StringReader("C8 6 q");
    Appendable testAppendable = new StringBuilder();
    Appendable testAppendable2 = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testQ, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable.toString());
    SimpleFreecellController ctrl2 = new SimpleFreecellController(model,
            testLowerQ, testAppendable2);
    ctrl2.playGame(deck, 8, 4, false);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable2.toString());
  }

  @Test
  public void testInvalidPileType() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("D5 6 C4 \n q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Invalid Source Pile. Try again\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }

  @Test
  public void testInvalidPileIndex() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C10 7 C4 q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Invalid Move. Try again\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }

  @Test
  public void testInvalidCardIndex() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C5 9 C4 q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Invalid Move. Try again\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }

  @Test
  public void testInvalidDestinationPile() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C5 6 D4 \n q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Invalid Destination Pile. Try again: C5 6\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }

  @Test
  public void testInvalidDestinationIndex() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C5 6 C9 \n q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Invalid Move. Try again\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }



  @Test
  public void testCascadeToCascade() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C5 6 C4 q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    FreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "F1:\n" +
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }

  @Test
  public void testCascadeToOpen() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C5 6 O1 q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    FreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(deck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
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
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: Q♣\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C2: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C3: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
            "C4: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n" +
            "C5: 2♣, 4♣, 6♣, 8♣, 10♣\n" +
            "C6: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C7: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "C8: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "\n" +
            "Game quit prematurely\n", testAppendable.toString());
  }

  @Test
  public void testCascadeToFoundation() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C1 7 F1 q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    FreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(reverseDeck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥\n" +
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
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }

  @Test
  public void testOpenToFoundation() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C1 7 O1 \n O1 1 F1 q");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    FreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(reverseDeck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♥\n" +
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
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥\n" +
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
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "Game quit prematurely.\n", testAppendable.toString());
  }

  @Test
  public void testGameOver() {
    SimpleFreecellModel<Card> model = new SimpleFreecellModel<>();
    Readable testReadable = new StringReader("C1 7 F1 \n " +
            "C2 7 F2 \n" +
            "C3 7 F3 \n" +
            "C4 7 F4 \n" +
            "C5 6 F1 \n" +
            "C6 6 F2 \n" +
            "C7 6 F3 \n" +
            "C8 6 F4 \n" +
            "C1 6 F1 \n " +
            "C2 6 F2 \n" +
            "C3 6 F3 \n" +
            "C4 6 F4 \n" +
            "C5 5 F1 \n" +
            "C6 5 F2 \n" +
            "C7 5 F3 \n" +
            "C8 5 F4 \n" +
            "C1 5 F1 \n " +
            "C2 5 F2 \n" +
            "C3 5 F3 \n" +
            "C4 5 F4 \n" +
            "C5 4 F1 \n" +
            "C6 4 F2 \n" +
            "C7 4 F3 \n" +
            "C8 4 F4 \n" +
            "C1 4 F1 \n " +
            "C2 4 F2 \n" +
            "C3 4 F3 \n" +
            "C4 4 F4 \n" +
            "C5 3 F1 \n" +
            "C6 3 F2 \n" +
            "C7 3 F3 \n" +
            "C8 3 F4 \n" +
            "C1 3 F1 \n " +
            "C2 3 F2 \n" +
            "C3 3 F3 \n" +
            "C4 3 F4 \n" +
            "C5 2 F1 \n" +
            "C6 2 F2 \n" +
            "C7 2 F3 \n" +
            "C8 2 F4 \n" +
            "C1 2 F1 \n " +
            "C2 2 F2 \n" +
            "C3 2 F3 \n" +
            "C4 2 F4 \n" +
            "C5 1 F1 \n" +
            "C6 1 F2 \n" +
            "C7 1 F3 \n" +
            "C8 1 F4 \n" +
            "C1 1 F1 \n " +
            "C2 1 F2 \n" +
            "C3 1 F3 \n" +
            "C4 1 F4 \n");
    Appendable testAppendable = new StringBuilder();
    List<Card> deck = model.getDeck();
    List<Card> reverseDeck = new ArrayList<>(52);
    for (int i = 0; i < 52; i++) {
      reverseDeck.add(deck.get(51 - i));
    }
    FreecellController ctrl = new SimpleFreecellController(model,
            testReadable, testAppendable);
    ctrl.playGame(reverseDeck, 8, 4, false);
    FreecellView view = new FreecellTextView(model);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥\n" +
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
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥\n" +
            "F2: A♦\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥\n" +
            "F2: A♦\n" +
            "F3: A♠\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥\n" +
            "F2: A♦\n" +
            "F3: A♠\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥, 2♥\n" +
            "F2: A♦\n" +
            "F3: A♠\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥, 2♥\n" +
            "F2: A♦, 2♦\n" +
            "F3: A♠\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥, 2♥\n" +
            "F2: A♦, 2♦\n" +
            "F3: A♠, 2♠\n" +
            "F4: A♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "\n" +
            "F1: A♥, 2♥\n" +
            "F2: A♦, 2♦\n" +
            "F3: A♠, 2♠\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥\n" +
            "F2: A♦, 2♦\n" +
            "F3: A♠, 2♠\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥\n" +
            "F2: A♦, 2♦, 3♦\n" +
            "F3: A♠, 2♠\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥\n" +
            "F2: A♦, 2♦, 3♦\n" +
            "F3: A♠, 2♠, 3♠\n" +
            "F4: A♣, 2♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥\n" +
            "F2: A♦, 2♦, 3♦\n" +
            "F3: A♠, 2♠, 3♠\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥, 4♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥\n" +
            "F2: A♦, 2♦, 3♦\n" +
            "F3: A♠, 2♠, 3♠\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦, 4♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦\n" +
            "F3: A♠, 2♠, 3♠\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠, 4♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠\n" +
            "F4: A♣, 2♣, 3♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥, 5♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦, 5♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠, 5♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣, 5♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥, 6♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦, 6♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠, 6♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣, 6♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥, 7♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦, 7♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠, 7♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣, 7♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥, 8♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦, 8♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠, 8♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣, 8♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥, 9♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦, 9♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠, 9♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣, 9♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥, 10♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦, 10♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦\n" +
            "C7: Q♠, 10♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦\n" +
            "C7: Q♠\n" +
            "C8: Q♣, 10♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, J♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦\n" +
            "C7: Q♠\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦, J♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦\n" +
            "C7: Q♠\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦\n" +
            "C3: K♠, J♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦\n" +
            "C7: Q♠\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦\n" +
            "C3: K♠\n" +
            "C4: K♣, J♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦\n" +
            "C7: Q♠\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦\n" +
            "C3: K♠\n" +
            "C4: K♣\n" +
            "C5: Q♥\n" +
            "C6: Q♦\n" +
            "C7: Q♠\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦\n" +
            "C3: K♠\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6: Q♦\n" +
            "C7: Q♠\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦\n" +
            "C3: K♠\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7: Q♠\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦\n" +
            "C3: K♠\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8: Q♣\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥\n" +
            "C2: K♦\n" +
            "C3: K♠\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2: K♦\n" +
            "C3: K♠\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3: K♠\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4: K♣\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "\n" +
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F3: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "\n" +
            "Game over.\n", testAppendable.toString());
  }



}