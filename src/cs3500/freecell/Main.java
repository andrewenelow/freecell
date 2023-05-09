package cs3500.freecell;

import java.io.InputStreamReader;

import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;

/**
 * Main class that is used to run the controller.
 */
public class Main {

  /**
   * This is the main method that calls playgame.
   * @param args The arguments that may used for the controller.
   */
  public static void main(String[] args) {
    FreecellModelCreator creator = new FreecellModelCreator();
    FreecellModel model = creator.create(FreecellModelCreator.GameType.MULTIMOVE);
    SimpleFreecellController ctrl = new SimpleFreecellController(model,
            new InputStreamReader(System.in), System.out);

    try {
      ctrl.playGame(model.getDeck(), 8, 4, true);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Something went wrong");
    }


  }
}
