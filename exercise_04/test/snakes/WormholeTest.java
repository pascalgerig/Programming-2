package snakes;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class WormholeTest {
	private Game game;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
	}


	@Test
	public void wormholeTeleportsPlayerToRandomExit()
	{
		List<Square> squareList = new LinkedList<Square>(
				Arrays.asList(
					new WormholeExitSquare(game, 3),
					new WormholeExitSquare(game, 4)
				)
		);
		when(game.wormholeExits()).thenReturn(squareList);

		WormholeEntranceSquare entrance = new WormholeEntranceSquare(game, 2);

		/* Ensure all returned tiles are actually wormhole exits. We
		 * can *not* assert that all exits are returned, as that would
		 * be testing nondeterministic behaviour. */
		for (int i = 0; i < 100; i++) {
			assertTrue(squareList.contains(entrance.landHereOrGoHome()));
		}
	}

	@Test
	public void wormholeDoesNotTeleportPlayerIfNoExitsPresent()
	{
		WormholeEntranceSquare sq = new WormholeEntranceSquare(game, 2);
		assertEquals(sq, sq.landHereOrGoHome());
	}

	@Test
	public void toStringReturnsUsefulRepresentation() {
		Player player = mock(Player.class);
		when(player.toString()).thenReturn("John");

		WormholeEntranceSquare entrance = new WormholeEntranceSquare(game, 1);
		WormholeExitSquare exit = new WormholeExitSquare(game, 2);

		entrance.enter(player);
		assertEquals("[1 (Entrance)<John>]", entrance.toString());

		exit.enter(player);
		assertEquals("[2 (Exit)<John>]", exit.toString());
	}

	@Test
	public void wormholeExitShouldBeWormholeExit() {
		assertTrue(new WormholeExitSquare(game, 1).isWormholeExit());
	}
}
