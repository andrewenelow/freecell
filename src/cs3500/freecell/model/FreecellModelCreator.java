package cs3500.freecell.model;

import cs3500.freecell.model.multimove.AdvancedFreecellModel;

/**
 * This class creates a freecell game model.
 * The model can either create a single move model or a multimove model;
 */
public class FreecellModelCreator {
  /**
   * This enum represents the types of game models: single move or multi move.
   */
  public enum GameType { SINGLEMOVE, MULTIMOVE }

  /**
   * This creates a new freecell model based on the gametype.
   * @param type The type is a GameType enum that determines which freecell model is created.
   * @return A new freecell model.
   */
  public static FreecellModel create(GameType type) {
    switch (type) {
      case SINGLEMOVE: return new SimpleFreecellModel();
      case MULTIMOVE: return new AdvancedFreecellModel();
      default: throw new IllegalArgumentException("Invalid GameType.");
    }
  }
}
