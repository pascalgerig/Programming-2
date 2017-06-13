package snakes;

import org.junit.Test;
import org.junit.Before;
import snakes.Player;
import snakes.squares.FirstSquare;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class PlayerTest
{
	private Game game;
	private StandardSquare source;
	private StandardSquare destination;

	@Before
	public void init() {
		this.game = mock(Game.class);
		when(this.game.isValidPosition(anyInt())).thenReturn(true);
		FirstSquare firstSquare = new FirstSquare(game, 1);
		when(this.game.firstSquare()).thenReturn(firstSquare);

		this.source = mock(StandardSquare.class);
		this.destination = mock(StandardSquare.class);
		when(this.game.getSquare(1)).thenReturn(this.source);
		when(this.game.getSquare(2)).thenReturn(this.destination);
		when(this.game.firstSquare()).thenReturn(this.source);
	}

	@Test(expected = AssertionError.class)
	public void invariantAssuresNameIsNotNull() {
		Player p = new Player(null);
		p.joinGame(game);
	}

	@Test
	public void swapSetsSquareToNewSquare() {
		Player p = new Player("John");
		p.joinGame(game);
		p.swap(destination);
		assertEquals(source.position(), p.position());
	}

	@Test
	public void swapReturnsOldSquare() {
		Player p = new Player("John");
		p.joinGame(game);
		assertEquals(source, p.swap(destination));
	}
}
