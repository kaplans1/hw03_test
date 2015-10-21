package cs3500.hw03;

import java.util.ArrayList;

/**
 * You don't need to implement this class or any concrete subclasses for pset03.
 */
public final class StrictCoinGameModel implements CoinGameModel {
  // (Exercise 2) Declare the fields needed to support the methods in
  // the interface you’ve designed:

  //(2)
  //Representing collection of players as a single number
  //Not final - players can be added mid-game
  protected ArrayList<Player> players;
  //(3)
  //Represents the player ID of the next player
  protected int nextplayer;
  //(4)
  //Represents player ID of the winner
  protected int winner;
  //(1)
  //Representing board as ArrayList of booleans
  private ArrayList<Boolean> board;

  // (Exercise 3) Describe, as precisely as you can, your
  // representation’s class invariants:

  //(1)
  //Board will only be created if input string is valid.
  //Board does not contain nulls.
  //Input string valid only if it contains '0' s and '-' s

  //(2)
  //Number of players is >= 0.

  //(3)
  //nextplayer is >=0, but less than or equal to the total number of players

  //(4)
  //winner is >=0, but less than or equal to the total number of players


  // (Exercise 4) Describe your constructor API here by filling in
  // whatever arguments you need and writing good Javadoc. (You may
  // declare any combination of constructors and static factory
  // methods that you like, but you need not get fancy.)

  //Not sure what formatting to go for. Writing javadoc for all constructors to be used

  /**
   * Gets information passed StrictCoinGameModel from user.
   * Constructor checks:
   * @param board - board is valid string containing '0' s and '-' s
   * @param players - builds players using private constructor PlayerBuild from the number of
   *                players given
   * @throws IllegalArgumentException if passed a poor string for board
   */

  /**
   * Gets information passed StrictCoinGameModel from user (same as above, but creates a game
   * with no players)
   * Constructor checks:
   * @param board - board is valid string containing '0' s and '-'
   * @throws IllegalArgumentException if passed a poor string for board
   */

  /**
   * The
   * @param nextplayer
   * and
   * @param winner
   *
   * Will be initialized as 0 and null (respectively) at the start of the game. This will be done
   * in the StrictCoinGameModel constructor, but it is not getting those two values as parameters.
   * Wasn't quite sure how to represent that information.
   */

  /**
   * PlayerBuild -> Private!
   * Gets information passed StrictCoinGameModel from user to build ArrayList<Player>. Starts at
   * player ID 0. Checks through all players, adds 1 to max player ID found.
   * If 0 passed, returns empty arraylist.
   * @param players - integer >=0
   * @throws IllegalArgumentException if invalid number of players
   */

  //Javadoc for new methods for handling players

  /**
   * void addPlayer
   * Adds a new player to ArrayList<Player>
   * No restrictions - game can be over, but players can still be added
   * Max number of players is (2^32)-1
   * Adding a player before the last player moves results in the new player having to take a
   * turn in that round (see nextPlayerUpdate).
   */

  /**
   * getPlayers
   * returns ArrayList<Player> from game
   */

  /**
   * makePlayer -> Makes a new player by accessing the ArrayList<Player>, getting the max
   * player value, which will be the size of the array-1, since count is started at 0.
   * In short: returns new player with ID of the size of the ArrayList<Player>
   */

  /**
   * void nextPlayerUpdate
   * updates nextplayer every time move is called - AFTER move is called
   * Increments up to ArrayList<Player>.size-1, then starts at 0 again
   * Returns -1 if game is over, or if there are no players
   * Adding a player before the last player moves results in the new player having to take a
   * turn in that round.
   * If the last player has moved, and a new player is added, this new player will go a subsequent
   * round
   */

  /**
   * void updateWinner isGameOver is checked every time a move is about to be made, if the game is
   * over, then updateWinner is set to nextplayer-1, because the previous player must have made the
   * last move, and nextPlayerUpdate updates nextplayer after a move is finished
   */

  public StrictCoinGameModel(String board, int players) {
    // You don't need to implement this constructor.
    //My old tests are throwing errors so I'm putting some code to make it stop throwing errors.
    //actual constructor will be different
    throw new UnsupportedOperationException("no need to implement this");
  }

  //code in place just so it compiles
  public StrictCoinGameModel(String board) {
    // You don't need to implement this constructor.
    //My old tests are throwing errors so I'm putting some code to make it stop throwing errors.
    //actual constructor will be different
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
  public void move(int coinIndex, int newPosition) {

  }

  @Override
  public void addPlayer() {

  }

  @Override
  public ArrayList<Player> getPlayers() {
    return players;
  }

  @Override
  public int getNextPlayer() {
    return 0;
  }

  @Override
  public int getWinner() {
    return 0;
  }

  // You don't need to implement any methods or constructors. However,
  // if you want to make sure your code compiles, you could have your
  // IDE generate stubs for all the missing methods. This would also
  // allow you to make sure that your tests in StrictCoinGameModelTest
  // actually type check and compile against this class (though you
  // don’t need to make them pass, because you don’t need to implement
  // StrictCoinGameModel’s methods).
}
