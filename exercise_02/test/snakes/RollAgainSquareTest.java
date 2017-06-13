package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RollAgainSquareTest extends SquareTest {
	@Override
	@Before
	public void newGame() {
		initializeGame(15);
		game.setSquare(3, new RollAgainSquare(game, 3));
	}

	@Test
	public void rollAgainSquareCausesSecondRoll() {
		// Move Jack to RollAgain square. This should immediatly
		// trigger a second roll - so Jack should end up somewhere
		// ahead of square 3.
		game.movePlayer(2);
		assertTrue(jack.position() > 3);
	}

	// Can you think of more game scenarios you need/want to cover to make sure your implementation is correct?
}
