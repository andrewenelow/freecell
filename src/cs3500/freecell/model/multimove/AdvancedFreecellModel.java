package cs3500.freecell.model.multimove;

import java.util.ArrayList;
import java.util.List;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.SimpleFreecellModel;

/**
 * This class represents the model for an advanced game of freecell.
 * In an advanced game, it is possible to move multiple cards as long as it would also be
 * possible to move them in a simple freecell game.
 * This class extends the SimpleFreecellModel class.
 *
 * @param <K> K represents an arbitrary card object.
 */
public class AdvancedFreecellModel<K> extends SimpleFreecellModel<Card> {

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    if (!hasGameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (cardIndex + 1 == getPile(source, pileNumber).pileSize()) {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    } else if (cardIndex + 1 < getPile(source, pileNumber).pileSize()) {
      multiMove(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }

  private void multiMove(PileType source, int pileNumber, int cardIndex, PileType destination,
                         int destPileNumber) throws IllegalArgumentException {
    List<cs3500.freecell.model.Card> build = new ArrayList<>();
    if (!hasGameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (validMultimove(source, pileNumber, cardIndex, destination, destPileNumber)) {
      for (int i = 0; i < getPile(source, pileNumber).pileSize() - cardIndex; i++) {
        build.add(calledCard(source, pileNumber, cardIndex + i));
      }
      getPile(destination, destPileNumber).addCards(build);
      getPile(source, pileNumber).removeCards(build);
    } else {
      throw new IllegalArgumentException("Invalid Move.");
    }
  }

  private boolean validMultimove(PileType source, int pileNumber, int cardIndex,
                                 PileType destination,
                                 int destPileNumber) {
    if (!(validPileNumber(source, pileNumber))) {
      return false;
    }
    switch (destination) {
      case FOUNDATION:
        return false;
      case OPEN:
        return false;
      case CASCADE:
        if (getNumCardsInCascadePile(destPileNumber) == 0) {
          return getPile(source, pileNumber).validBuild(cardIndex)
                  && getNumCardsInBuild(source, pileNumber, cardIndex) <= moveableCardsInBuild();
        }
        else {
          return ((getPile(source, pileNumber).getCard(cardIndex)
                  .validCascadeStack(topCard(destination, destPileNumber)))
                  && getPile(source, pileNumber).validBuild(cardIndex)
                  && getNumCardsInBuild(source, pileNumber, cardIndex) <= moveableCardsInBuild());
        }
      default:
        return false;
    }
  }

  private int moveableCardsInBuild() {
    return (int) ((getNumEmptyOpenPiles() + 1) * Math.pow(2, getNumEmptyCascadePiles()));
  }

  private int getNumCardsInBuild(PileType type, int pileNumber, int cardIndex) {
    return getPile(type, pileNumber).cardsInBuild(cardIndex);
  }

  private int getNumEmptyOpenPiles() {
    int piles = 0;
    for (int i = 0; i < getNumOpenPiles(); i++) {
      if (getNumCardsInOpenPile(i) < 1) {
        piles++;
      }
    }
    return piles;
  }

  private int getNumEmptyCascadePiles() {
    int piles = 0;
    for (int i = 0; i < getNumCascadePiles(); i++) {
      if (getNumCardsInCascadePile(i) < 1) {
        piles++;
      }
    }
    return piles;
  }

}
