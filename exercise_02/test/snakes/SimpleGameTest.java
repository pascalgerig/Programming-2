package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleGameTest {

	private Player jack;
	private Player jill;

	private Game game;

	public Game newGame() {
		jack = new Player("Jack");
		jill = new Player("Jill");
		Player[] args = { jack, jill };
		game = new Game(15, args);
		game.setSquareToLadder(2, 4);
		game.setSquareToLadder(6, 2);
		game.setSquareToSnake(11, -6);
		game.setSquare(3, new SpeedUpSquare(game, 3));
		game.setSquare(4, new WormholeEntrance(game, 4));
		game.setSquare(7, new WormholeExit(game, 7));
		game.setSquare(10, new WormholeExit(game, 10));
		game.setSquare(12, new SlowDownSquare(game, 12));
		game.setSquare(14, new RollBackSquare(game, 14));
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}

	@Test
	public void initialStrings() {
		newGame();
		assertEquals("Jack", jack.toString());
		assertEquals("Jill", jill.toString());
		assertEquals("[1<Jack><Jill>]", game.firstSquare().toString());
		assertEquals("[2->6]", game.getSquare(2).toString());
		assertEquals("[5<-11]", game.getSquare(11).toString());
		assertEquals("[1<Jack><Jill>][2->6][3 (SpeedUp)][4 (Entrance)][5][6->8][7 (Exit)][8][9][10 (Exit)][5<-11][12 (SlowDown)][13][14 (RollBack)][15]", game.toString());
	}
}
