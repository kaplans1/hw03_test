/**
 * Created by AviSion on 10/9/2015.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.hw03.CoinGameModel;
import cs3500.hw03.Player;
import cs3500.hw03.StrictCoinGameModel;
//import cs3500.hw03.LaxCoinGameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class StrictCoinGameModelTest {
  CoinGameModel test = new StrictCoinGameModel("-0--0", 1);
  CoinGameModel test0players = new StrictCoinGameModel("-0--0", 0);
  CoinGameModel test0players2 = new StrictCoinGameModel("-0--0");
  CoinGameModel test2 = new StrictCoinGameModel("-0--0", 2);
  CoinGameModel test2OVER = new StrictCoinGameModel("00---", 2);
  Player zero = new Player(0);
  Player one = new Player(1);
  Player two = new Player(2);

  @Test
  public void makePlayer0() {
    assertEquals((test.getPlayers()).get(0).getID(), 0);
  }

  @Test
  public void makePlayer1() {
    assertEquals((test.getPlayers()).get(1).getID(), 1);
  }

  @Test
  public void makePlayer2() {
    test.addPlayer();
    assertEquals((test.getPlayers()).get(2).getID(), (test2.getPlayers()).get(2).getID());
  }

  @Test
  public void makePlayer3() {
    assertEquals((test.getPlayers()).get(1).getID(), 1);
  }

  //Player array returned will be empty if game created with 0 players
  @Test(expected = IndexOutOfBoundsException.class)
  public void makePlayer4() {
    test0players.getPlayers().get(0);
  }

  //Player array returned will be null, and not created if game created without player field
  @Test
  public void makePlayer4a() {
    assertNull(test0players2.getPlayers());
  }

  @Test
  public void nextPlayerUpdate0() {
    test.move(4, 3);
    assertEquals(1, test.getNextPlayer());
  }

  //looping around test
  @Test
  public void nextPlayerUpdate1() {
    test.move(4, 3);
    test.move(3, 2);
    assertEquals(0, test.getNextPlayer());
  }

  //no players results in no next player
  @Test
  public void nextPlayerUpdate2() {
    assertNull(test0players.getNextPlayer());
  }

  //no players results in no next player
  @Test
  public void nextPlayerUpdate2a() {
    assertNull(test0players2.getNextPlayer());
  }

  @Test
  public void nextPlayerUpdate3() {
    assertEquals(-1, (test2OVER.getNextPlayer()));
  }

  //adding player BEFORE last player moves
  @Test
  public void nextPlayerUpdate4() {
    test.move(4, 3);
    //nextPlayer is 1
    test.addPlayer();
    test.move(3, 2);
    //next Player is 2
    assertEquals(2, (test.getNextPlayer()));
  }

  //adding player AFTER last player moves
  @Test
  public void nextPlayerUpdate5() {
    test.move(4, 3);
    //nextPlayer is 1
    test.move(3, 2);
    //next player is 0
    test.addPlayer();
    assertEquals(0, (test.getNextPlayer()));
  }

  //testing if  winner is set correctly
  @Test
  public void updateWinner0() {
    test.move(1, 0);
    test.move(4, 1);
    //game is over at this point
    assertEquals(1, test.getWinner());
  }

  //testing if  winner is set correctly
  @Test
  public void updateWinner1() {
    test.move(1, 0);
    //game is not over
    assertNull(test.getWinner());
  }

  //not testing Player.java yet - intentional

  //HW 2 tests
  //------------------------------------------------------------------------------------
//    CoinGameModel lax = new LaxCoinGameModel("-0--0");
//    CoinGameModel laxMove1 = new LaxCoinGameModel("00---");
//    CoinGameModel str = new StrictCoinGameModel("-0--0");
//    CoinGameModel strMove2 = new StrictCoinGameModel("-00--");

//    CoinGameModel over = new LaxCoinGameModel("00---");
//    CoinGameModel over2 = new LaxCoinGameModel("00");
  //new ArrayList<String>(Arrays.asList("-", "0", "-", "-", "0"))

//    //invalid board testing
//    @Test (expected = IllegalArgumentException.class)
//    public void testValidBoard() {
//        CoinGameModel strict = new StrictCoinGameModel("");
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testValidBoard1() {
//        CoinGameModel strict = new StrictCoinGameModel("holymoleyit's a test");
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void testValidBoard2() {
//        CoinGameModel strict = new StrictCoinGameModel("0");
//    }
//
//    //Testing board size
//    @Test
//    public void boardSize() {
//        assertEquals(5, lax.boardSize());
//    }
//
//    //Counting coins on board
//    @Test
//    public void coinCount() {
//        assertEquals(2, lax.coinCount());
//    }
//
//    //Testing coin position
//    @Test
//    public void getCoinPosition() {
//        assertEquals(1, lax.getCoinPosition(0));
//    }
//
//    @Test
//    public void getCoinPosition2() {
//        assertEquals(4, lax.getCoinPosition(1));
//    }
//
//    //Testing getting the coin position
//    @Test (expected = IllegalArgumentException.class)
//    public void getCoinPosition3() {
//        lax.getCoinPosition(2);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void getCoinPosition4() {
//        lax.getCoinPosition(-1);
//    }
//
//    //Testing if game is over
//    @Test
//    public void isOver() {
//        assertEquals(true, over.isGameOver());
//    }
//
//    @Test
//    public void isOver2() {
//        assertEquals(true, over2.isGameOver());
//    }
//
//    @Test
//    public void isOver3() {
//        assertEquals(false, lax.isGameOver());
//    }
//    //move test
//    //error thrown if coin jumps in strict game
//    @Test (expected = IllegalArgumentException.class)
//    public void move() {
//        str.move(1, 0);
//    }
//    //same board as move() -> no error thrown, coin is moved
//    @Test
//    public void move1() {
//        lax.move(1, 0);
//        assertEquals(lax.toString(), laxMove1.toString());
//    }
//    //strict game coin moved fine
//    @Test
//    public void move2() {
//        str.move(1, 2);
//        assertEquals(strMove2.toString(), str.toString());
//    }
//    //coin on top of other coin
//    @Test (expected = IllegalArgumentException.class)
//    public void move3() {
//        str.move(1, 1);
//    }
//
//    @Test (expected = IllegalArgumentException.class)
//    public void move4() {
//        str.move(1, -5);
//    }
//    //------------------------------------------------------------------------------------

}
