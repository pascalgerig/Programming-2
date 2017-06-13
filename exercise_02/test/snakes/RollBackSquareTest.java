package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RollBackSquareTest extends SquareTest {
	@Override
	@Before
	public void newGame() {
		initializeGame(15);
		game.setSquare(7, new RollBackSquare(game, 7));
	}

	@Test
	public void moveToRollBackSquare() {
		game.movePlayer(6); // moves Jack
		assertEquals(7, jack.position());
		assertEquals(1, jill.position());
		game.movePlayer(5); // moves Jill
		assertEquals(7, jack.position());
		assertEquals(6, jill.position());
		game.movePlayer(2); // now it's Jill's turn
		assertEquals(5, jack.position());
		assertEquals(6, jill.position());
	}
}
