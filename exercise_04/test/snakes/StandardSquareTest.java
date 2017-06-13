package snakes;

import org.junit.Test;
import org.junit.Before;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StandardSquareTest {
	private Game game;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
	}

	@Test
	public void testMoveAndLandOnly() {
		Square testSquare;
		Square start, stop;
		testSquare = new StandardSquare(game, 1);
		start = mock(Square.class);
		stop = mock(Square.class);

		when(game.findSquare(1, 2)).thenReturn(start);
		when(start.landHereOrGoHome()).thenReturn(stop);

		Square destination = testSquare.moveAndLand(2);
		assertEquals(stop, destination);
	}

	@Test
	public void testLandHereOrGoHomeDefault()
	{
		Square testSquare;
		Square start = mock(Square.class);
		when(game.firstSquare()).thenReturn(start);
		testSquare = new StandardSquare(game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(testSquare, destination);
	}

	@Test
	public void testLandHereOrGoHomeOccupiedEnterAndLeave()
	{
		Square testSquare;
		Player player = mock(Player.class);
		when(player.position()).thenReturn(1);
		Square start = mock(Square.class);
		when(game.firstSquare()).thenReturn(start);
		testSquare = new StandardSquare(game, 1);
		testSquare.enter(player);
		assertTrue(testSquare.isOccupied());
		Square destination = testSquare.landHereOrGoHome();
		assertEquals(start, destination);
		testSquare.leave(player);
		assertTrue(!testSquare.isOccupied());
	}

	@Test
	public void toStringReturnsUsefulRepresentation() {
		Player player = mock(Player.class);
		when(player.toString()).thenReturn("John");

		StandardSquare sq = new StandardSquare(game, 1);
		sq.enter(player);

		assertEquals("[1<John>]", sq.toString());
	}

	@Test
	public void isNotFirstSquare() {
		assertFalse(new StandardSquare(game, 1).isFirstSquare());
	}

	@Test
	public void isNotWormholeExit() {
		assertFalse(new StandardSquare(game, 1).isWormholeExit());
	}
}
