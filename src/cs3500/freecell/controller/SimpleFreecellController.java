package cs3500.freecell.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

/**
 * SimpleFreecellController is the class that controls the FreecellModel.
 * This class implements the FreecellController interface.
 */
public class SimpleFreecellController implements FreecellController<Card> {
  private FreecellModel<Card> model;
  private Readable rd;
  private Appendable ap;

  /**
   * This is the constructor class for SimpleFreecellController.
   *
   * @param model This is the SimpleFreecellModel that is used by the controller.
   * @param rd    This is the readable input that accepts the user's moves.
   * @param ap    This is the appendable output that displays the game state.
   */
  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap) {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("Argument is null");
    }
    this.model = model;
    this.rd = rd;
    this.ap = ap;
  }


  @Override
  public void playGame(List<Card> deck, int numCascades, int numOpens, boolean shuffle) {

    if (deck == null) {
      throw new IllegalArgumentException("Deck is Null");
    }
    FreecellTextView view = new FreecellTextView(model, ap);

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      safeRenderMessage("Could not start game.", view);
      return;
    }
    safeRenderBoard(view);
    Scanner scan = new Scanner(rd);
    while (!(model.isGameOver())) {
      safeInput(scan);
      boolean validPlay = false;
      while (!validPlay) {
        String source = null;
        boolean validSourcePile = false;
        boolean validSourceIndex = false;
        while (!validSourcePile) {
          safeInput(scan);
          source = scan.next();
          if (hasGameQuit(source)) {
            safeRenderMessage("Game quit prematurely.", view);
            return;
          }
          try {
            derivePileType(source);
            validSourcePile = true;
          } catch (IllegalArgumentException e) {
            safeRenderMessage("Invalid Source Pile. Try again", view);
            //scan = new Scanner(rd);
            safeInput(scan);
            source = scan.next();
          }
        }
        while (!validSourceIndex) {
          if (hasGameQuit(source)) {
            safeRenderMessage("Game quit prematurely.", view);
            return;
          }
          try {
            derivePileIndex(source);
            validSourceIndex = true;
          } catch (IllegalArgumentException e) {
            safeRenderMessage("Invalid Source Index. Try again", view);
            safeInput(scan);
            source = scan.next();
          }
        }
        String cardIndexString;
        int cardIndex = -1;
        boolean validCardIndex = false;
        while (!validCardIndex) {
          safeInput(scan);
          cardIndexString = scan.next();
          if (hasGameQuit(cardIndexString)) {
            safeRenderMessage("Game quit prematurely.", view);
            return;
          }
          if (integerCardIndex(cardIndexString)) {
            cardIndex = Integer.parseInt(cardIndexString);
            validCardIndex = true;
          } else {
            safeRenderMessage("Invalid Card Index. Try again: " + source, view);
          }
        }
        String destination = null;
        boolean validDestinationPile = false;

        while (!validDestinationPile) {
          safeInput(scan);
          destination = scan.next();
          if (hasGameQuit(destination)) {
            safeRenderMessage("Game quit prematurely.", view);
            return;
          }
          try {
            derivePileType(destination);
            validDestinationPile = true;
          } catch (IllegalArgumentException e) {
            safeRenderMessage("Invalid Destination Pile. Try again: " + source + " " + cardIndex,
                    view);
          }
        }
        try {
          model.move(derivePileType(source), derivePileIndex(source) - 1,
                  cardIndex - 1,
                  derivePileType(destination),
                  derivePileIndex(destination) - 1);
          safeRenderBoard(view);
          validPlay = true;
        } catch (IllegalArgumentException e) {
          safeRenderMessage("Invalid Move. Try again", view);
        }
      }
    }
    safeRenderMessage("Game over.", view);

  }

  private PileType derivePileType(String s) {
    char pileChar = s.charAt(0);
    switch (pileChar) {
      case 'C':
        return PileType.CASCADE;
      case 'F':
        return PileType.FOUNDATION;
      case 'O':
        return PileType.OPEN;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
  }

  private boolean integerCardIndex(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private int derivePileIndex(String s) {
    String s2 = s.substring(1);
    try {
      Integer.parseInt(s2);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid Pile Call");
    }
    return Integer.parseInt(s2);
  }

  private boolean hasGameQuit(String s) {
    return (s.length() == 1 && (s.charAt(0) == 'Q' || s.charAt(0) == 'q'));
  }

  private void safeRenderMessage(String s, FreecellView view) throws IllegalStateException {
    try {
      view.renderMessage(s);
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to Append");
    }
  }

  private void safeRenderBoard(FreecellView view) {
    try {
      view.renderBoard();
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to Append");
    }
  }

  private void safeInput(Scanner scan) {
    if (!scan.hasNext() && !model.isGameOver()) {
      throw new IllegalStateException("Input failed.");
    }
  }
}

