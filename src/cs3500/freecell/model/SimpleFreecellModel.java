package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the model for a simple game of freecell.
 * There are no constructors for this class, but there are variables for each of the PileTypes.
 *
 * @param <K> K represents an arbitrary card object.
 */
public class SimpleFreecellModel<K> implements FreecellModel<Card> {
  protected ArrayList<Pile> cascadePiles;
  protected ArrayList<Pile> openPiles;
  protected ArrayList<Pile> foundationPiles;
  protected boolean hasGameStarted = false;


  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>(52);
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        if (j < 2) {
          deck.add(4 * i + j, new Card(intToRank(i), intToSuit(j), false));
        } else {
          deck.add(4 * i + j, new Card(intToRank(i), intToSuit(j), true));
        }
      }
    }
    return deck;
  }

  protected Rank intToRank(int i) {
    Rank[] ranks = Rank.values();
    return ranks[i];
  }

  protected Suit intToSuit(int i) {
    Suit[] suits = Suit.values();
    return suits[i];
  }


  /*
  Credit: JavaRevisited
  https://javarevisited.blogspot.com/2015/06/3-ways-to-find-duplicate-elements-in-array-java.html#axzz7KPy3kinS
   */
  protected boolean anyDupes(List<Card> cards) {
    for (int i = 0; i < cards.size(); i++) {
      for (int j = i + 1; j < cards.size(); j++) {
        if (cards.get(i).getValue() == cards.get(j).getValue() && cards.get(i)
                .getSuitValue() == cards.get(j).getSuitValue()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    if (deck.size() != 52 || deck == null) {
      throw new IllegalArgumentException("Deck does not contain 52 cards");
    }
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("Requires at least 4 cascade piles");
    }
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("Requires at least 1 open pile");
    }
    if (anyDupes(deck)) {
      throw new IllegalArgumentException("Deck contains duplicates");
    }
    hasGameStarted = true;
    if (shuffle) {
      Collections.shuffle(deck);
    }
    cascadePiles = new ArrayList<Pile>(numCascadePiles);
    openPiles = new ArrayList<Pile>(numOpenPiles);
    foundationPiles = new ArrayList<Pile>(4);

    //creates piles in cascadePiles ArrayList
    for (int i = 0; i < numCascadePiles; i++) {
      cascadePiles.add(new Pile(new ArrayList<Card>(), PileType.CASCADE));
    }

    //creates piles in openPiles ArrayList
    for (int i = 0; i < numOpenPiles; i++) {
      openPiles.add(new Pile(new ArrayList<Card>(1), PileType.OPEN));
    }

    //creates piles in foundationPiles ArrayList
    for (int i = 0; i < 4; i++) {
      foundationPiles.add(new Pile(new ArrayList<Card>(1), PileType.FOUNDATION));
    }

    for (int i = 0; i < deck.size() / numCascadePiles + 1; i++) {
      for (int j = 0; j < numCascadePiles; j++) {
        if (numCascadePiles * i + j < 52) {
          cascadePiles.get(j).addCard(deck.get(numCascadePiles * i + j));
        } else {
          break;
        }
      }
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    if (!hasGameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (validMove(source, pileNumber, cardIndex, destination, destPileNumber)) {
      getPile(destination, destPileNumber).addCard(calledCard(source, pileNumber, cardIndex));
      getPile(source, pileNumber).removeCard(cardIndex);
    } else {
      throw new IllegalArgumentException("Invalid Move");
    }
  }

  /**
   * This method is a boolean that determines if it's okay to move a card.
   *
   * @param source         the PileType of the pile the card is being moved from.
   * @param pileNumber     the pileIndex of the pile the card is being moved from.
   * @param cardIndex      the card index of the card being moved.
   * @param destination    the PileType of the pile the card is being moved to.
   * @param destPileNumber the pileIndex of the pile the card is being moved to.
   * @return a boolean response based on if the move is valid.
   */
  protected boolean validMove(PileType source, int pileNumber, int cardIndex, PileType destination,
                              int destPileNumber) {
    if (!(validPileNumber(source, pileNumber))) {
      return false;
    }
    if (cardIndex == getPile(source, pileNumber).pileSize() - 1 && source != PileType.FOUNDATION) {
      switch (destination) {
        case OPEN:
          return (getNumCardsInOpenPile(destPileNumber) == 0);
        case CASCADE:
          if (getNumCardsInCascadePile(destPileNumber) == 0) {
            return true;
          } else {
            return getPile(source, pileNumber).getCard(cardIndex)
                    .validCascadeStack(topCard(destination, destPileNumber));
          }
        case FOUNDATION:
          if (getNumCardsInFoundationPile(destPileNumber) == 0) {
            return getPile(source, pileNumber).getCard(cardIndex).getValue() == 0;
          } else {
            return getPile(source, pileNumber).getCard(cardIndex)
                    .validFoundationStack(topCard(destination, destPileNumber));
          }
        default:
          return false;
      }
    } else {
      return false;
    }
  }

  protected boolean validPileNumber(PileType type, int pileNumber) {
    return (pileNumber < getNumPiles(type) && pileNumber >= 0);
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < 4; i++) {
      if (getNumCardsInFoundationPile(i) < 13) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getNumCardsInFoundationPile(int index) {
    if (!hasGameStarted) {
      throw new IllegalStateException("Game has not Started");
    } else {
      if (index > 3 || index < 0) {
        throw new IllegalArgumentException("Invalid Pile Index");
      }
      return foundationPiles.get(index).pileSize();
    }
  }

  protected int getNumPiles(PileType type) {
    switch (type) {
      case OPEN:
        return getNumOpenPiles();
      case CASCADE:
        return getNumCascadePiles();
      case FOUNDATION:
        return 4;
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
  }

  @Override
  public int getNumCascadePiles() {
    if (hasGameStarted) {
      return cascadePiles.size();
    } else {
      return -1;
    }
  }

  @Override
  public int getNumCardsInCascadePile(int index) {
    if (!(hasGameStarted)) {
      throw new IllegalStateException("Game has not started");
    } else if (index > getNumCascadePiles() - 1 || index < 0) {
      throw new IllegalArgumentException("Index out of bounds");
    } else {
      return cascadePiles.get(index).pileSize();
    }
  }

  //should return 0 or 1 only
  @Override
  public int getNumCardsInOpenPile(int index) {
    if (!(hasGameStarted)) {
      throw new IllegalStateException("Game has not started");
    }
    if (index > getNumOpenPiles() - 1 || index < 0) {
      throw new IllegalArgumentException("Index out of bounds");
    } else {
      return openPiles.get(index).pileSize();
    }
  }

  @Override
  public int getNumOpenPiles() {
    if (hasGameStarted) {
      return openPiles.size();
    } else {
      return -1;
    }
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex) {
    if (!(hasGameStarted)) {
      throw new IllegalStateException("");
    }
    if (getNumCardsInFoundationPile(pileIndex) - 1 < cardIndex) {
      throw new IllegalArgumentException("No card exists at given index");
    } else {
      return foundationPiles.get(pileIndex).getCard(cardIndex);
    }

  }

  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex) {
    if (!(hasGameStarted)) {
      throw new IllegalStateException("");
    }
    if (getNumCardsInCascadePile(pileIndex) - 1 < cardIndex
            || pileIndex > getNumCascadePiles() - 1) {
      throw new IllegalArgumentException("No card exists at given index");
    } else {
      return cascadePiles.get(pileIndex).getCard(cardIndex);
    }
  }

  @Override
  public Card getOpenCardAt(int pileIndex) {
    if (!(hasGameStarted)) {
      throw new IllegalStateException("");
    }

    if (pileIndex > getNumOpenPiles() - 1) {
      throw new IllegalArgumentException("No card exists at given index");
    } else {
      return openPiles.get(pileIndex).getCard(0);
    }
  }


  protected Pile getPile(PileType type, int pileIndex) {
    switch (type) {
      case OPEN:
        return openPiles.get(pileIndex);
      case CASCADE:
        return cascadePiles.get(pileIndex);
      case FOUNDATION:
        return foundationPiles.get(pileIndex);
      default:
        throw new IllegalArgumentException("Invalid PileType");
    }
  }

  protected Card topCard(PileType type, int pileIndex) {
    return getPile(type, pileIndex).getTopCard();
  }

  protected Card calledCard(PileType type, int pileIndex, int cardIndex) {
    return getPile(type, pileIndex).getCard(cardIndex);
  }
}
