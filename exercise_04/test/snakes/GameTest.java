package snakes;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.StandardSquare;
import snakes.squares.LadderSquare;
import snakes.squares.SnakeSquare;
import snakes.squares.WormholeExitSquare;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GameTest {
	private Queue<Player> players;
	private Player player1;
	private Player player2;
	private Game game;

	@Before
	public void init() {
		this.player1 = mock(Player.class);
		this.player2 = mock(Player.class);

		this.players = new LinkedList<Player>(
				Arrays.asList(
					this.player1, 
					this.player2
				)
		);

		this.game = new Game(5, this.players, 6);
	}

	@Test
	public void testPlayWithMockito() throws GameNotOverException
	{
		/* I find it hard to mock the Player class here, as the
		 * implementation of Game#play makes use of
		 * Player#moveForward(), which has side-effects, which then
		 * determine how long Game#play will go on.  I would have to
		 * reimplemen half the business logic in my mock object, which
		 * is rather undesireable. */
		player1 = new Player("John");
		player2 = new Player("George");

		this.players = new LinkedList<Player>(
				Arrays.asList(
					player1,
					player2
				)
		);

		Game game = new Game(5, players, 6);

		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);

		game.play(die);

		assertEquals(player1, game.winner());
	}

	@Test
	public void testPlayWithCustomMock() throws GameNotOverException
	{
		/* I find it hard to mock the Player class here, as the
		 * implementation of Game#play makes use of
		 * Player#moveForward(), which has side-effects, which then
		 * determine how long Game#play will go on.  I would have to
		 * reimplemen half the business logic in my mock object, which
		 * is rather undesireable. */
		player1 = new Player("John");
		player2 = new Player("George");

		this.players = new LinkedList<Player>(
				Arrays.asList(
					player1,
					player2
				)
		);

		Game game = new Game(5, players, 6);

		IDie die = new MockDie();

		game.play(die);

		assertEquals(player1, game.winner());
	}

	@Test
	public void movePlayerBouncesBackFromEndOfGameBoard() {
	}

	@Test (expected = AssertionError.class)
	public void movePlayerWithMovesLowerThan1ViolatesContract()
	{
		game.movePlayer(0);
	}

	@Test
	public void winnerReturnsPlayerIfHeHasWon() throws GameNotOverException {
		when(player1.wins()).thenReturn(true);
		game.movePlayer(1);
		assertEquals(player1, game.winner());
	}

	@Test(expected = GameNotOverException.class)
	public void winnerThrowsExceptionIfGameNotOver() throws GameNotOverException {
		game.movePlayer(1);
		game.winner();
	}

	@Test
	public void wormholeExitsReturnsWormholeExits() {
		WormholeExitSquare exit1 = mock(WormholeExitSquare.class);
		when(exit1.isWormholeExit()).thenReturn(true);
		WormholeExitSquare exit2 = mock(WormholeExitSquare.class);
		when(exit2.isWormholeExit()).thenReturn(true);

		game.setSquare(2, exit1);
		game.setSquare(3, exit2);

		assertEquals(
			new ArrayList<Square>(
				Arrays.asList(
					exit1,
					exit2
				)
			),
			game.wormholeExits()
		);
	}

	@Test
	public void nextPlayerReturnsAndDoesNotModifyNextPlayer() {
		assertEquals(player2, game.nextPlayer());
		assertEquals(player2, game.nextPlayer());
	}

	@Test
	public void isOverReturnsFalseIfPlayerHasNotWon() {
		when(player1.wins()).thenReturn(false);
		game.movePlayer(1);
		assertFalse(game.isOver());
	}

	@Test
	public void isOverReturnsTrueIfPlayerHasWon() {
		when(player1.wins()).thenReturn(true);
		game.movePlayer(1);
		assertTrue(game.isOver());
	}

	@Test(expected = AssertionError.class)
	public void setSquareDoesNotModifyFirstSquare() {
		game.setSquare(1, mock(StandardSquare.class));
	}

	@Test(expected = AssertionError.class)
	public void setSquareDoesNotModifyLastSquare() {
		game.setSquare(5, mock(StandardSquare.class));
	}

	@Test
	public void testMovePlayerOverEnd()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		game.movePlayer(5);
		assertTrue(player1.position() == 2);
	}

	@Test
	public void testMovePlayer1()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		game.movePlayer(1);
		assertTrue(player1.position() == 2);
	}
}
