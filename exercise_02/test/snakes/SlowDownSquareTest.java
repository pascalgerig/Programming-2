package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SlowDownSquareTest extends SquareTest {
	@Override
	@Before
	public void newGame() {
		initializeGame(15);
		game.setSquare(3, new SlowDownSquare(game, 3));
	}

	@Test
	public void slowDownSquareHalvesNextMove() {
		game.movePlayer(2); // moves Jack
		assertEquals(3, jack.position());
		assertEquals(1, jill.position());
		game.movePlayer(4); // moves Jill
		assertEquals(3, jack.position());
		assertEquals(5, jill.position());
		// Now it's Jack's turn - due to being on a SlowDown square, he
		// should only move half this amount.
		game.movePlayer(2);
		assertEquals(4, jack.position());
		assertEquals(5, jill.position());
	}

	@Test
	public void slowDownSquareRoundsDown() {
		// Moves Jack to slowdown square
		game.movePlayer(2);
		game.movePlayer(1);

		// Jack should move by floor(5/2) = 2
		game.movePlayer(5);
		assertEquals(5, jack.position());
	}

	@Test
	public void slowDownSquareDoesNotCauseStillstand() {
		// Moves Jack to slowdown square
		game.movePlayer(2);
		game.movePlayer(1);

		// Jackc should move by 1, not 0.
		game.movePlayer(1);
		assertEquals(4, jack.position());
	}
}
