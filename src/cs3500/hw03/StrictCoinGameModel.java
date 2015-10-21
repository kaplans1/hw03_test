package cs3500.hw03;

import java.util.ArrayList;

/**
 * You don't need to implement this class or any concrete subclasses for pset03.
 */
public final class StrictCoinGameModel implements CoinGameModel {
  // (Exercise 2) Declare the fields needed to support the methods in
  // the interface you’ve designed:

  /**
   * An array representing the game board.
   *
   * Each element of the array represents a space on the board
   *
   * If the element is null, it is an empty space
   *
   * An integer in the array represents a coin in that space on the board with an index of that
   * integer
   */
  private final Integer[] board;

  /**
   * An ArrayList of players currently in the game
   *
   * Players are represented by Strings in the ArrayList and are also identifiable by their index in
   * the array Assumption: players cannot leave the game once added
   */
  private final ArrayList<String> players;

  /**
   * The player whose turn it currently is
   */
  private String currentPlayer;

  // (Exercise 3) Describe, as precisely as you can, your
  // representation’s class invariants:

  /*
  INVARIANT 1: all elements of the board Array are either non-negative or null
  INVARIANT 2: all Strings in the ArrayList players are unique
  INVARIANT 3: currentPlayer is either null or non-negative
  INVARIANT 4: the currentPlayer is only null if there are no players in the game
  INVARIANT 5: if there is at least one player in the game, the currentPlayer
               is a valid index of a player (less than the length of players)
  INVARIANT 6: players is not null - at the least it is an empty ArrayList<String>
  INVARIANT 7: the coins in the board array are numbered counting from 0 up to the number of
               coins - 1
  INVARIANT 8: the coins in the board are ordered numerically (e.g. coin0 is always to the left of
               coin1, coin3 is always to the left of coin4 etc.)
   */

  // (Exercise 4) Describe your constructor API here by filling in
  // whatever arguments you need and writing good Javadoc. (You may
  // declare any combination of constructors and static factory
  // methods that you like, but you need not get fancy.)

  /**
   * Constructs a {@code StrictCoinGameModel} with the given board and given players to start
   *
   * @param board   the game board represented as a String with "-" representing an empty space and
   *                "O" representing a coin
   * @param players zero or more Strings representing the players that the game will start with
   * @throws IllegalArgumentException if the board is not a valid configuration (contains characters
   *                                  other than "O" and "-")
   * @throws IllegalArgumentException if the Strings for the players are not unique
   */
  protected StrictCoinGameModel(String board, String... players) {
    // You don't need to implement this constructor.
    throw new UnsupportedOperationException("no need to implement this");
  }

  @Override
  public int boardSize() {
    return 0;
  }

  @Override
  public int coinCount() {
    return 0;
  }

  @Override
  public int getCoinPosition(int coinIndex) {
    return 0;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public void move(String player, int coinIndex, int newPosition) {

  }

  @Override
  public String getCurrentPlayer() {
    return null;
  }

  @Override
  public ArrayList<String> getPlayers() {
    return null;
  }

  @Override
  public void addPlayer(String newPlayer, int playOrderPlace) {

  }

  @Override
  public String getWinner() {
    return null;
  }

  // You don't need to implement any methods or constructors. However,
  // if you want to make sure your code compiles, you could have your
  // IDE generate stubs for all the missing methods. This would also
  // allow you to make sure that your tests in StrictCoinGameModelTest
  // actually type check and compile against this class (though you
  // don’t need to make them pass, because you don’t need to implement
  // StrictCoinGameModel’s methods).
}
