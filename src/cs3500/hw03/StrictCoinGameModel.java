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
    return this.board.length;
  }

  @Override
  public int coinCount() {
    int count = 0;
    for (Integer i : this.board) {
      if (i != null) {
        count += 1;
      }
    }
    return count;
  }

  @Override
  public int getCoinPosition(int coinIndex) {
    for (int i = 0; i < this.board.length; i++) {
      if (this.board[i] != null && this.board[i] == coinIndex) {
        return i;
      }
    }
    throw new IllegalArgumentException("No coin with the requested index");
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < coinCount(); i++) {
      if (this.getCoinPosition(i) != i) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void move(String player, int coinIndex, int newPosition) {
    if (this.validMove(player, coinIndex, newPosition)) {
      this.board[this.getCoinPosition(coinIndex)] = null;
      this.board[newPosition] = coinIndex;
      this.currentPlayer = this.players.get(
          (this.players.indexOf(player) + 1) % this.players.size());
    } else {
      throw new IllegalMoveException("Illegal move");
    }
  }

  /**
   * Determines whether moving the given coin to the given position is a valid move according to the
   * rules of this game
   *
   * A move is valid if: <ul> <li>coinIndex is a valid coin</li> <li>newPosition is a valid open
   * position on the board</li> <li>moving the given coin does not result in "jumping" over
   * another</li></ul>
   *
   * @param coinIndex   which coin to move (numbered from the left)
   * @param newPosition where to move it to
   * @return whether moving the given coin to the given position is valid
   */
  private boolean validMove(String player, int coinIndex, int newPosition) {
    boolean validCoinAndPosition = coinIndex >= 0 && coinIndex < this.coinCount()
        && newPosition >= 0 && newPosition < this.board.length &&
        this.board[newPosition] == null && player.equals(this.currentPlayer);
    if (coinIndex > 0) {
      return this.getCoinPosition(coinIndex - 1) < newPosition && validCoinAndPosition;
    } else {
      return validCoinAndPosition;
    }
  }

  @Override
  public String getCurrentPlayer() {
    return this.currentPlayer;
  }

  @Override
  public ArrayList<String> getPlayers() {
    ArrayList<String> copy = this.players;
    return copy;
  }

  @Override
  public void addPlayer(String newPlayer, int playOrderPlace) {
    if(this.uniqueName(newPlayer)) {
      ArrayList<String> newPlayerList = new ArrayList<String>();
      newPlayerList.addAll(this.players.subList(0, playOrderPlace));
      newPlayerList.add(newPlayer);
      newPlayerList.addAll(this.players.subList(playOrderPlace, this.players.size()));
    }
    else {
      throw new IllegalArgumentException("Player name must be unique");
    }
  }

  /**
   *
   * @param newPlayer
   * @return
   */
  private boolean uniqueName(String newPlayer) {
    return this.players.contains(newPlayer);
  }

  @Override
  public String getWinner() {
    if(this.players.indexOf(this.currentPlayer) == 0) {
      return this.players.get(this.players.size() - 1);
    } else {
      return this.players.get(this.players.indexOf(this.currentPlayer) - 1);
    }
  }

  // You don't need to implement any methods or constructors. However,
  // if you want to make sure your code compiles, you could have your
  // IDE generate stubs for all the missing methods. This would also
  // allow you to make sure that your tests in StrictCoinGameModelTest
  // actually type check and compile against this class (though you
  // don’t need to make them pass, because you don’t need to implement
  // StrictCoinGameModel’s methods).
}
