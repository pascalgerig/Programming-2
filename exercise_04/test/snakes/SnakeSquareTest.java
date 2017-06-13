package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;

import snakes.squares.LadderSquare;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SnakeSquareTest {
	private Game game;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
	}

	@Test
	public void testLandHereOrGoHomeDefault()
	{
		Square start = mock(Square.class);
		when(start.landHereOrGoHome()).thenReturn(start);

		when(game.getSquare(1)).thenReturn(start);

		SnakeSquare testSquare = new SnakeSquare(-1, game, 2);
		assertEquals(start, testSquare.landHereOrGoHome());
	}

	@Test (expected = AssertionError.class)
	public void positiveTransportFailsContract()
	{
		SnakeSquare testSquare = new SnakeSquare(1, game, 1);
	}

	@Test (expected = AssertionError.class)
	public void transportLeadingOutsideOfBoardFailsContract()
	{
		when(game.isValidPosition(anyInt())).thenReturn(false);
		SnakeSquare testSquare = new SnakeSquare(-5, game, 1);
	}

	@Test
	public void toStringReturnsUsefulRepresentation() {
		StandardSquare dest = mock(StandardSquare.class);
		when(dest.position()).thenReturn(1);
		when(game.getSquare(1)).thenReturn(dest);

		Player player = mock(Player.class);
		when(player.toString()).thenReturn("John");

		SnakeSquare sq = new SnakeSquare(-2, game, 3);
		sq.enter(player);

		assertEquals("[1<-3<John>]", sq.toString());
	}
}
