package cs3500.hw03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StrictCoinGameModelTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidBoard() {
    new StrictCoinGameModel("--0--", "Player 1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNonUniqueNames() {
    new StrictCoinGameModel("--O", "Player 1", "Player 2", "Player 1");
  }

  // this test is to demonstrate that these StrictCoinGameModels can be
  // successfully created without throwing exceptions
  @Test
  public void testConstructorValid() {
    // no players
    new StrictCoinGameModel("-O-");
    // one player
    new StrictCoinGameModel("-O-", "Player 1");
    // two players
    new StrictCoinGameModel("-O-", "Player 1", "Player 2");
    // multiple players
    new StrictCoinGameModel("-O-", "Player 1", "Player 2", "Player 3", "Player 4");
    // empty board
    new StrictCoinGameModel("", "Player 1");
    // no coins on board
    new StrictCoinGameModel("----", "Player 1");
    // no empty spaces/game already over
    new StrictCoinGameModel("OOOOOO", "Player 1");
    // average case
    new StrictCoinGameModel("-O-O-O-O-O----OO", "Player 1", "Player 2");
  }

  @Test
  public void testConstructorAccuracyNoPlayers() {
    StrictCoinGameModel game1 = new StrictCoinGameModel("-O-");
    assertEquals(game1.boardSize(), 3);
    assertEquals(game1.coinCount(), 1);
    assertEquals(game1.getCoinPosition(0), 1);
    assertEquals(game1.getPlayers().size(), 0);
    assertFalse(game1.isGameOver());
  }

  @Test
  public void testConstructorAccuracyEmptyBoard() {
    StrictCoinGameModel game2 = new StrictCoinGameModel("", "Player 1");
    assertEquals(game2.boardSize(), 0);
    assertEquals(game2.coinCount(), 0);
    assertEquals(game2.getPlayers().size(), 1);
    assertEquals(game2.getPlayers().get(0), "Player 1");
    assertEquals(game2.getCurrentPlayer(), 0);
    assertTrue(game2.isGameOver());
    // this is an edge case in which the game is over before it starts
    // design choice to have the first player be declared winner in this case
    assertEquals(game2.getWinner(), 0);
  }

  @Test
  public void testConstructorAccuracyAverageCase() {
    StrictCoinGameModel game3 = new StrictCoinGameModel("-O--O-", "Player 1", "Player 2",
        "Player 3");
    assertEquals(game3.boardSize(), 6);
    assertEquals(game3.coinCount(), 2);
    assertEquals(game3.getCoinPosition(0), 1);
    assertEquals(game3.getCoinPosition(1), 4);
    assertEquals(game3.getPlayers().size(), 3);
    assertEquals(game3.getPlayers().get(0), "Player 1");
    assertEquals(game3.getPlayers().get(1), "Player 2");
    assertEquals(game3.getPlayers().get(2), "Player 3");
    assertEquals(game3.getCurrentPlayer(), 0);
    assertFalse(game3.isGameOver());
  }

  @Test
  public void testGetCurrentPlayer() {
    StrictCoinGameModel game4 = new StrictCoinGameModel("-O--O", "Player 1", "Player 2");
    // starts with Player 1's turn
    assertEquals(game4.getCurrentPlayer(), 0);
    //Player 1  makes a move
    game4.move(0, 0, 0);
    // now Player 2's turn
    assertEquals(game4.getCurrentPlayer(), 1);
    //Player 2 makes a move
    game4.move(1, 1, 3);
    // now Player 1's turn again
    assertEquals(game4.getCurrentPlayer(), 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentPlayerNoPlayers() {
    StrictCoinGameModel game5 = new StrictCoinGameModel("OO-");
    game5.getCurrentPlayer();
  }

  @Test
  public void testGetPlayers() {
    StrictCoinGameModel game6 = new StrictCoinGameModel("OO---");
    assertNotNull(game6.getPlayers());
    assertEquals(game6.getPlayers().size(), 0);
    game6.addPlayer("Player 1");
    assertEquals(game6.getPlayers().size(), 1);
    assertEquals(game6.getPlayers().get(0), "Player 1");
    game6.addPlayer("Player 2");
    assertEquals(game6.getPlayers().size(), 2);
    assertEquals(game6.getPlayers().get(1), "Player 2");

    StrictCoinGameModel game7 = new StrictCoinGameModel("OO---", "Player A",
        "Player B", "Player C");
    assertEquals(game7.getPlayers().size(), 3);
    game6.addPlayer("Player D");
    assertEquals(game7.getPlayers().size(), 4);
    assertEquals(game7.getPlayers().get(3), "Player D");
  }

  @Test
  public void testAddPlayer() {
    StrictCoinGameModel game8 = new StrictCoinGameModel("--O-O");
    assertEquals(game8.getPlayers().size(), 0);
    game8.addPlayer("Caitlin");
    assertEquals(game8.getPlayers().size(), 1);
    assertEquals(game8.getPlayers().get(0), "Caitlin");

    game8.move(0, 0, 0);

    game8.addPlayer("Rachel");
    assertEquals(game8.getPlayers().size(), 2);
    assertEquals(game8.getPlayers().get(1), "Rachel");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerDuplicateName() {
    StrictCoinGameModel game = new StrictCoinGameModel("-OO-", "Caitlin", "Rachel");
    game.addPlayer("Caitlin");
  }

  @Test
  public void testGetWinner() {
    StrictCoinGameModel game9 = new StrictCoinGameModel("", "Player 0", "Player 1");
    assertEquals(game9.getWinner(), 0);

    StrictCoinGameModel game11 = new StrictCoinGameModel("--O", "Player A", "Player B");
    // Player A's turn
    game11.move(0, 0, 1);
    // Player B's turn
    game11.move(1, 0, 0);
    // game over
    assertEquals(game11.getWinner(), 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerNoPlayers() {
    StrictCoinGameModel game = new StrictCoinGameModel("OO-");
    game.getWinner();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerGameNotOver() {
    StrictCoinGameModel game12 = new StrictCoinGameModel("-O--O", "Player 1");
    game12.getWinner();
  }

  //Retested move because changed signature

  @Test
  public void testMoveAlwaysValid() {
    CoinGameModel game1 = new StrictCoinGameModel("-O", "Player 1", "Player 2");
    assertEquals(game1.getCoinPosition(0), 1);
    assertEquals(game1.getCurrentPlayer(), 0);
    game1.move(0, 0, 0);
    assertEquals(game1.getCoinPosition(0), 0);
    assertEquals(game1.getCurrentPlayer(), 1);

    CoinGameModel game2 = new StrictCoinGameModel("O-O", "Player 1");
    assertEquals(game2.getCoinPosition(1), 2);
    assertEquals(game1.getCurrentPlayer(), 0);
    game2.move(0, 1, 1);
    assertEquals(game2.getCoinPosition(1), 1);
    assertEquals(game2.getCurrentPlayer(), 0);

    CoinGameModel game3 = new StrictCoinGameModel("-O---O--OOO-O", "Player 1", "Player 2");
    assertEquals(game3.getCoinPosition(0), 1);
    assertEquals(game3.getCurrentPlayer(), 0);
    game3.move(0, 0, 0);
    assertEquals(game3.getCoinPosition(0), 0);
    assertEquals(game3.getCurrentPlayer(), 1);
    assertEquals(game3.getCoinPosition(1), 5);
    game3.move(1, 1, 2);
    assertEquals(game3.getCoinPosition(1), 2);
    assertEquals(game3.getCurrentPlayer(), 0);
    assertEquals(game3.getCoinPosition(2), 8);
    game3.move(0, 2, 5);
    assertEquals(game3.getCoinPosition(2), 5);
    assertEquals(game3.getCurrentPlayer(), 1);
    assertEquals(game3.getCoinPosition(5), 12);
    game3.move(1, 5, 11);
    assertEquals(game3.getCoinPosition(5), 11);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveInvalidCoinIndex() {
    new StrictCoinGameModel("---", "Player 1").move(0, 0, 0);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveInvalidNewPosition() {
    new StrictCoinGameModel("-O-", "Player 1").move(0, 0, 3);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveCoinIntoSamePosition() {
    new StrictCoinGameModel("-O-", "Player 1").move(0, 0, 1);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveCoinIntoOccupiedPosition() {
    new StrictCoinGameModel("O-O", "Player 1").move(0, 1, 0);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testStrictMoveCoinJump() {
    new StrictCoinGameModel("-OO", "Player 1").move(0, 1, 0);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveRight() {
    new StrictCoinGameModel("O-O", "Player 1").move(0, 0, 1);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveInvalidPlayer() {
    new StrictCoinGameModel("-O--OO", "Player 1", "Player 2").move(1, 0, 0);
  }

}

