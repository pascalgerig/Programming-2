package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class FirstSquareTest {
	private Game game;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
	}

	@Test
	public void landHereOrGoHomePutsPlayerOnThisField() {
		FirstSquare testSquare = new FirstSquare(game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(testSquare, destination);
	}

	@Test
	public void enterAndLeaveSetIsOccupied()
	{
		Player player = mock(Player.class);
		when(player.position()).thenReturn(1);

		FirstSquare testSquare = new FirstSquare(game, 1);

		testSquare.enter(player);
		assertTrue(testSquare.isOccupied());

		testSquare.leave(player);
		assertFalse(testSquare.isOccupied());
	}

	@Test(expected=AssertionError.class)
	public void enteringSquareTwiceThrowsException() {
		Player player = mock(Player.class);
		when(player.position()).thenReturn(1);

		FirstSquare testSquare = new FirstSquare(game, 1);

		testSquare.enter(player);
		testSquare.enter(player);
	}

	@Test(expected=AssertionError.class)
	public void leavingSquareIfNotOnItThrowsException() {
		Player player = mock(Player.class);
		when(player.position()).thenReturn(1);

		FirstSquare testSquare = new FirstSquare(game, 1);

		testSquare.leave(player);
	}

	@Test
	public void firstSquareShouldBeFirstSquare() {
		assertTrue(new FirstSquare(game, 1).isFirstSquare());
	}
}
