package cs3500.freecell.view;

import java.io.IOException;

import cs3500.freecell.model.FreecellModelState;

/**
 * This class represents the text view for the freecell model state.
 */
public class FreecellTextView implements FreecellView {
  private final FreecellModelState<?> model;
  private final Appendable ap;


  /**
   * This method is the constructor for FreecellTextView.
   * @param model The model is the freecell model state that is used to create the text view.
   */
  public FreecellTextView(FreecellModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    this.ap = System.out;

  }

  /**
   * This method is the constructor for FreecellTextView.
   * @param model The model is the freecell model state that is used to create the text view.
   * @param ap The appendable is the output of the view.
   */
  public FreecellTextView(FreecellModelState model, Appendable ap) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    this.ap = ap;
  }

  @Override
  public String toString() {
    if (model.getNumCascadePiles() < 0) {
      return "";
    } else {
      StringBuilder output = new StringBuilder();
      for (int i = 0; i < 4; i++) {
        output.append("F" + (i + 1) + ":");
        if (model.getNumCardsInFoundationPile(i) >= 1) {
          for (int j = 0; j < model.getNumCardsInFoundationPile(i); j++) {
            output.append(" ");
            output.append(model.getFoundationCardAt(i, j));
            if (j < model.getNumCardsInFoundationPile(i) - 1) {
              output.append(",");
            }
          }
        }
        output.append("\n");
      }

      for (int i = 0; i < model.getNumOpenPiles(); i++) {
        output.append("O" + (i + 1) + ":");
        if (model.getNumCardsInOpenPile(i) != 0) {
          output.append(" ");
          output.append(model.getOpenCardAt(i));
        }
        output.append("\n");
      }

      for (int i = 0; i < model.getNumCascadePiles(); i++) {
        output.append("C" + (i + 1) + ":");
        for (int j = 0; j < model.getNumCardsInCascadePile(i); j++) {
          output.append(" ");
          output.append(model.getCascadeCardAt(i, j));
          if (j < model.getNumCardsInCascadePile(i) - 1) {
            output.append(",");
          }
        }
        if (i < model.getNumCascadePiles() - 1) {
          output.append("\n");
        }
      }
      return output.toString();
    }


  }

  @Override
  public void renderBoard() throws IOException {
    char[] charBoard = this.toString().toCharArray();
    for (char c: charBoard) {
      this.ap.append(c);
    }
    char newLine = '\n';
    this.ap.append(newLine);
    this.ap.append(newLine);

  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (message.length() != 0) {
      char[] charMessage = message.toCharArray();
      for (char c : charMessage) {
        this.ap.append(c);
      }
      if (!(message.equals("Could not start game."))) {
        this.ap.append('\n');
      }
    }
  }
}
