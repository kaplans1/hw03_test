package cs3500.hw03;

import java.util.ArrayList;

/**
 * An interface for playing a coin game. The rules of a particular coin game will be implemented by
 * classes that implement this interface.
 */
public interface CoinGameModel {
  /**
   * Gets the size of the board (the number of squares)
   *
   * @return the board size
   */
  int boardSize();

  /**
   * Gets the number of coins.
   *
   * @return the number of coins
   */
  int coinCount();

  /**
   * Gets the (zero-based) position of coin number {@code coinIndex}.
   *
   * @param coinIndex which coin to look up
   * @return the coin's position
   * @throws IllegalArgumentException if there is no coin with the requested index
   */
  int getCoinPosition(int coinIndex);

  /**
   * Returns whether the current game is over. The game is over if there are no valid moves.
   *
   * @return whether the game is over
   */
  boolean isGameOver();

  /**
   * {@code player} moves coin number {@code coinIndex} to position {@code newPosition}. Throws
   * {@code IllegalMoveException} if the requested move is illegal, which can happen in several
   * ways:
   *
   * <ul> <li>There is no coin with the requested index. <li>The new position is occupied by another
   * coin. <li>It isn't {@code player}'s turn <li>There is some other reason the move is illegal, as
   * specified by the concrete game class. </ul>
   *
   * Note that {@code coinIndex} refers to the coins as numbered from 0 to {@code coinCount() - 1},
   * not their absolute position on the board. However, coins have no identity, so if one coin
   * passes another, their indices are exchanged. The leftmost coin is always coin 0, the next
   * leftmost is coin 1, and so on.
   *
   * Updates the current player to be the next player in the turn order.
   *
   * @param player      the player making the move
   * @param coinIndex   which coin to move (numbered from the left)
   * @param newPosition where to move it to
   * @throws IllegalMoveException the move is illegal
   */
  void move(String player, int coinIndex, int newPosition);


  /**
   * Gets the id of the player whose turn it currently is
   *
   * @return the id of the player whose turn it currently is
   * @throws IllegalStateException there are no players in the game and therefore no currentPlayer
   */
  String getCurrentPlayer();

  /**
   * Counts the number of players currently in the game Assumption: players cannot leave the game
   * once they are added
   *
   *(Note: this should only return a copy of the player data, so that the player's identities
   * cannot be altered)
   *
   * @return the list of players in the game
   */
  ArrayList<String> getPlayers();

  /**
   * Adds a new player with the given name to the game
   *
   * @param newPlayer the name of the player being added
   * @param playOrderPlace place the new player should be added in the play order counting from zero
   *                       e.g. putting a player in at zero will make the player first in the
   *                       play order
   * @throws IllegalArgumentException if the name of the player is the same as one of the players
   *                                  currently in the game
   */
  void addPlayer(String newPlayer, int playOrderPlace);

  /**
   * If game is over, gets the winner - the player who made the last move
   *
   * @return the winning player (player that made the last move)
   * @throws IllegalStateException the game is not yet over
   * @throws IllegalStateException there are no players in the game to win
   */
  String getWinner();

  /**
   * The exception thrown by {@code move} when the requested move is illegal.
   *
   * <p>(Implementation Note: Implementing this interface doesn't require "implementing" the {@code
   * IllegalMoveException} class—it's already implemented right here. Nesting a class within an
   * interface is a way to strongly associate that class with the interface, which makes sense here
   * because the exception is intended to be used specifically by implementations and clients of
   * this interface.)
   */
  static class IllegalMoveException extends IllegalArgumentException {
    /**
     * Constructs a illegal move exception with no description.
     */
    public IllegalMoveException() {
      super();
    }

    /**
     * Constructs a illegal move exception with the given description.
     *
     * @param msg the description
     */
    public IllegalMoveException(String msg) {
      super(msg);
    }
  }
}
