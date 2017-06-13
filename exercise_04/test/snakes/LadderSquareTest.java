package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class LadderSquareTest {
	private Game game;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
	}

	@Test
	public void ladderSquareMovesPlayerToEndOfLadder()
	{
		Square end = mock(Square.class);
		when(end.landHereOrGoHome()).thenReturn(end);

		when(game.getSquare(2)).thenReturn(end);

		LadderSquare testSquare = new LadderSquare(1, game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(end, destination);
	}

	@Test (expected = AssertionError.class)
	public void ladderSquarerWithNegativeTransportFailsContract()
	{
		LadderSquare testSquare = new LadderSquare(-1, game, 1);
	}

	@Test (expected = AssertionError.class)
	public void ladderSquareWithTransportLeadingOutsideOfBoardFailsContract()
	{
		when(game.isValidPosition(anyInt())).thenReturn(false);
		LadderSquare testSquare = new LadderSquare(5, game, 1);
	}

	@Test
	public void toStringReturnsUsefulRepresentation() {
		StandardSquare dest = mock(StandardSquare.class);
		when(dest.position()).thenReturn(3);
		when(game.getSquare(3)).thenReturn(dest);

		Player player = mock(Player.class);
		when(player.toString()).thenReturn("John");

		LadderSquare sq = new LadderSquare(2, game, 1);
		sq.enter(player);

		assertEquals("[1->3<John>]", sq.toString());
	}
}
