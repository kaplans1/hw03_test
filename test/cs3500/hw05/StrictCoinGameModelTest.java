package cs3500.hw05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
    assertNull(game2.getCurrentPlayer());
    assertTrue(game2.isGameOver());
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
    assertEquals(game3.getCurrentPlayer(), "Player 1");
    assertFalse(game3.isGameOver());
  }

  @Test
  public void testGetCurrentPlayer() {
    StrictCoinGameModel game4 = new StrictCoinGameModel("-O--O", "Player 1", "Player 2");
    // starts with Player 1's turn
    assertEquals(game4.getCurrentPlayer(), "Player 1");
    //Player 1  makes a move
    game4.move("Player 1", 0, 0);
    // now Player 2's turn
    assertEquals(game4.getCurrentPlayer(), "Player 2");
    //Player 2 makes a move
    game4.move("Player 2", 1, 3);
    // now Player 1's turn again
    assertEquals(game4.getCurrentPlayer(), "Player 1");
  }

  @Test
  public void testGetCurrentPlayerNoPlayers() {
    StrictCoinGameModel game5 = new StrictCoinGameModel("OO-");
    assertNull(game5.getCurrentPlayer());
  }

  @Test
  public void testGetPlayers() {
    StrictCoinGameModel game6 = new StrictCoinGameModel("OO---");
    assertNotNull(game6.getPlayers());
    assertEquals(game6.getPlayers().size(), 0);
    game6.addPlayer("Player 1", 0);
    assertEquals(game6.getPlayers().size(), 1);
    assertEquals(game6.getPlayers().get(0), "Player 1");
    game6.addPlayer("Player 2", 1);
    assertEquals(game6.getPlayers().size(), 2);
    assertEquals(game6.getPlayers().get(1), "Player 2");

    StrictCoinGameModel game7 = new StrictCoinGameModel("OO---", "Player A",
        "Player B", "Player C");
    assertEquals(game7.getPlayers().size(), 3);
    game7.addPlayer("Player D", 3);
    assertEquals(game7.getPlayers().size(), 4);
    assertEquals(game7.getPlayers().get(3), "Player D");
  }

  @Test
  public void testAddPlayer() {
    StrictCoinGameModel game8 = new StrictCoinGameModel("--O-O");
    assertEquals(game8.getPlayers().size(), 0);
    game8.addPlayer("Caitlin", 0);
    assertEquals(game8.getPlayers().size(), 1);
    assertEquals(game8.getPlayers().get(0), "Caitlin");

    game8.move("Caitlin", 0, 0);

    game8.addPlayer("Rachel", 1);
    assertEquals(game8.getPlayers().size(), 2);
    assertEquals(game8.getPlayers().get(1), "Rachel");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerDuplicateName() {
    StrictCoinGameModel game = new StrictCoinGameModel("-OO-", "Caitlin", "Rachel");
    game.addPlayer("Caitlin", 2);
  }

  @Test
  public void testGetWinner() {
    StrictCoinGameModel game11 = new StrictCoinGameModel("--O", "Player A", "Player B");
    // Player A's turn
    game11.move("Player A", 0, 1);
    // Player B's turn
    game11.move("Player B", 0, 0);
    // game over
    assertEquals(game11.getWinner(), "Player B");
  }

  @Test(expected = IllegalStateException.class)
  public void testGameOverBeforeStarts() {
    StrictCoinGameModel game9 = new StrictCoinGameModel("", "Player 0", "Player 1");
    game9.getWinner();
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
    assertEquals(game1.getCurrentPlayer(), "Player 1");
    game1.move("Player 1", 0, 0);
    assertEquals(game1.getCoinPosition(0), 0);
    assertEquals(game1.getCurrentPlayer(), "Player 2");

    CoinGameModel game2 = new StrictCoinGameModel("O-O", "Player 1");
    assertEquals(game2.getCoinPosition(1), 2);
    assertEquals(game2.getCurrentPlayer(), "Player 1");
    game2.move("Player 1", 1, 1);
    assertEquals(game2.getCoinPosition(1), 1);
    assertEquals(game2.getCurrentPlayer(), "Player 1");

    CoinGameModel game3 = new StrictCoinGameModel("-O---O--OOO-O", "Player 1", "Player 2");
    assertEquals(game3.getCoinPosition(0), 1);
    assertEquals(game3.getCurrentPlayer(), "Player 1");
    game3.move("Player 1", 0, 0);
    assertEquals(game3.getCoinPosition(0), 0);
    assertEquals(game3.getCurrentPlayer(), "Player 2");
    assertEquals(game3.getCoinPosition(1), 5);
    game3.move("Player 2", 1, 2);
    assertEquals(game3.getCoinPosition(1), 2);
    assertEquals(game3.getCurrentPlayer(), "Player 1");
    assertEquals(game3.getCoinPosition(2), 8);
    game3.move("Player 1", 2, 5);
    assertEquals(game3.getCoinPosition(2), 5);
    assertEquals(game3.getCurrentPlayer(), "Player 2");
    assertEquals(game3.getCoinPosition(5), 12);
    game3.move("Player 2", 5, 11);
    assertEquals(game3.getCoinPosition(5), 11);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveInvalidCoinIndex() {
    new StrictCoinGameModel("---", "Player 1").move("Player 1", 0, 0);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveInvalidNewPosition() {
    new StrictCoinGameModel("-O-", "Player 1").move("Player 1", 0, 3);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveCoinIntoSamePosition() {
    new StrictCoinGameModel("-O-", "Player 1").move("Player 1", 0, 1);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveCoinIntoOccupiedPosition() {
    new StrictCoinGameModel("O-O", "Player 1").move("Player 1", 1, 0);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testStrictMoveCoinJump() {
    new StrictCoinGameModel("-OO", "Player 1").move("Player 1", 1,
        0);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveRight() {
    new StrictCoinGameModel("O-O", "Player 1").move("Player 1", 0, 1);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testMoveInvalidPlayer() {
    new StrictCoinGameModel("-O--OO", "Player 1", "Player 2").move("Player 2", 0, 0);
  }

  @Test
  public void testAddPlayerAnyTime() {
    StrictCoinGameModel game = new StrictCoinGameModel("-OO--", "Player 1" ,"Player 2");
    game.addPlayer("New Player", 0);
    assertEquals(game.getPlayers().indexOf("New Player"), 0);

    game.addPlayer("Player 4", 2);
    assertEquals(game.getPlayers().get(0), "New Player");
    assertEquals(game.getPlayers().get(1), "Player 1");
    assertEquals(game.getPlayers().get(2), "Player 4");
    assertEquals(game.getPlayers().get(3), "Player 2");
  }

  @Test
  public void testAddPlayerTurnOrders() {
    StrictCoinGameModel game = new StrictCoinGameModel("--OO--", "Player 1", "Player 2");
    assertEquals(game.getCurrentPlayer(), "Player 1");
    game.move("Player 1", 0, 1);

    game.addPlayer("New Player", 1);
    assertEquals(game.getCurrentPlayer(), "Player 2");

    game.addPlayer("Player 3", 3);
    game.move("Player 2", 1, 2);

    assertEquals(game.getCurrentPlayer(), "Player 3");
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testWrongPlayer() {
    StrictCoinGameModel game = new StrictCoinGameModel("--OO", "Player 1", "Player 2");
    game.move("Player 2", 0, 1);
  }

  @Test(expected = CoinGameModel.IllegalMoveException.class)
  public void testInvalidPlayer() {
    StrictCoinGameModel game = new StrictCoinGameModel("--OO", "Player 1", "Player 2");
    game.move("Player 3", 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddPlayerSameName() {
    StrictCoinGameModel game = new StrictCoinGameModel("--OO", "Player 1", "Player 2");
    game.addPlayer("Player 1", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAddPlayerSameName() {
    new StrictCoinGameModel("--OO", "Player 1", "Player 2", "Player 2");
  }

  @Test
  public void testScenario1() {
    assertEquals(CoinGameTestScenarios.scenario1("----OOOO"), "OOOO----");
  }

  @Test
  public void testScenario2() {
    assertEquals(CoinGameTestScenarios.scenario2("O--O-OO"), "OO--O-O");
  }

  @Test
  public void testScenario3() {
    assertEquals(CoinGameTestScenarios.scenario3("-OO-OO-O-O"), "O-OO-OO-O-");
  }

  @Test
  public void testScenario4() {
    assertEquals("OOO-O---", CoinGameTestScenarios.scenario4("OOO---O-"));
  }

  @Test
  public void testScenario5() {
    assertTrue(CoinGameTestScenarios.scenario5("O------O"));
  }
}

