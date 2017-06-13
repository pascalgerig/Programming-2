package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.SwapSquare;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SwapSquareTest {
	private Game game;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
	}

	@Test
	public void landHereOrGoHomeSwapsPlayerWithNextPlayer() {
		StandardSquare sq1 = mock(StandardSquare.class);
		Player player1 = mock(Player.class);
		when(player1.position()).thenReturn(1);
		when(game.getSquare(1)).thenReturn(sq1);

		StandardSquare sq2 = mock(StandardSquare.class);
		Player player2 = mock(Player.class);
		when(player2.position()).thenReturn(3);
		when(game.getSquare(3)).thenReturn(sq2);

		when(game.nextPlayer()).thenReturn(player2);

		SwapSquare testSquare = new SwapSquare(game, 2);
		assertEquals(sq2, testSquare.landHereOrGoHome());
	}

	@Test
	public void toStringReturnsUsefulRepresentation() {
		Player player = mock(Player.class);
		when(player.toString()).thenReturn("John");

		SwapSquare sq = new SwapSquare(game, 1);
		sq.enter(player);

		assertEquals("[1 (Swap)<John>]", sq.toString());
	}
}
