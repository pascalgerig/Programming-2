package snakes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class WormholeTest {
	private Player jack;
	private Player jill;
	private Game game;

	@Before
	public void initializeGame() {
		jack = new Player("Jack");
		jill = new Player("Jill");
		Player[] args = { jack, jill };
		game = new Game(12, args);
		game.setSquareToLadder(2, 2);
		game.setSquareToLadder(7, -5);
		game.setSquare(4, new snakes.WormholeEntrance(game, 4));
		game.setSquare(10, new WormholeExit(game, 10));
	}

	@Test
	public void newGame() {
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
		assertEquals("Jack", jack.toString());
		assertEquals("Jill", jill.toString());
		assertEquals("[1<Jack><Jill>]", game.firstSquare().toString());
		assertEquals("[4 (Entrance)]", game.getSquare(4).toString());
		assertEquals("[10 (Exit)]", game.getSquare(10).toString());
	}

	@Test
	public void jackMove1() {
		game.movePlayer(3);
		assertEquals("Jack is transported to wormhole exit", 10, jack.position());
		assertEquals(1, jill.position());
		assertTrue("Game is not over", game.notOver());
	}

	@Test
	public void jillMove1() {
		game.movePlayer(3);
		game.movePlayer(3);
		assertEquals("Jill is transported to wormhole exit, then moved back to 1", 1, jill.position());
		assertEquals("Jack is still on square 10", 10, jack.position());
	}
}
