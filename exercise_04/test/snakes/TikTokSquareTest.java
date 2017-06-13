package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.TikTokSquare;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class TikTokSquareTest {
	private Game game;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
	}

	@Test
	public void landHereOrGoHomeAlternatesBetweenTwoExists() {
		StandardSquare dest1 = mock(StandardSquare.class);
		when(dest1.landHereOrGoHome()).thenReturn(dest1);
		StandardSquare dest2 = mock(StandardSquare.class);
		when(dest2.landHereOrGoHome()).thenReturn(dest2);

		when(game.getSquare(2)).thenReturn(dest1);
		when(game.getSquare(3)).thenReturn(dest2);

		TikTokSquare sq = new TikTokSquare(game, 1, 2, 3);
		assertEquals(dest1, sq.landHereOrGoHome());
		assertEquals(dest2, sq.landHereOrGoHome());
		assertEquals(dest1, sq.landHereOrGoHome());
	}

	@Test
	public void toStringReturnsUsefulRepresentation() {
		Player player = mock(Player.class);
		when(player.toString()).thenReturn("John");

		TikTokSquare sq = new TikTokSquare(game, 1, 2, 3);
		sq.enter(player);

		assertEquals("[1 (TikTok)<John>]", sq.toString());
	}
}
