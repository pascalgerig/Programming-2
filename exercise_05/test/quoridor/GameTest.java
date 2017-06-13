package quoridor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameTest
{
	private Player player1 = new Player("George", 'g', new Tile(new Point(1, 1)), Player.Target.RIGHT);
	private Player player2 = new Player("John", 'j',   new Tile(new Point(3, 3)), Player.Target.DOWN);
	private List<Player> players = new ArrayList<Player>(Arrays.asList(player1, player2));

	private IGame game;

	@Before
	public void init() {
		this.players = new ArrayList<Player>(
				Arrays.asList(player1, player2)
		);

		this.game = new Game();
		this.game.setDimension(5, 7);
		this.game.addPlayer(player1);
		this.game.addPlayer(player2);

		this.player1.enterGame(this.game);
		this.player2.enterGame(this.game);
	}

	@Test
	public void getTileReturnsTile() {
		assertTrue(game.getTile(1, 3) instanceof Tile);
	}

	@Test(expected = AssertionError.class)
	public void getTileLeftOfBoardViolatesContract() {
		this.game.getTile(0, 1);
	}

	@Test(expected = AssertionError.class)
	public void getTileRightOfBoardViolatesContract() {
		this.game.getTile(6, 1);
	}

	@Test(expected = AssertionError.class)
	public void getTileAboveBoardViolatesContract() {
		this.game.getTile(1, 0);
	}

	@Test(expected = AssertionError.class)
	public void getTileBelowBoardViolatesContract() {
		this.game.getTile(1, 8);
	}

	@Test
	public void getTileIsOneIndexed() {
		/* If it's 0-indexed. one of these will raise an
		 * IndexOutOfBoundsException */
		assertTrue(game.getTile(1, 1) instanceof Tile);
		assertTrue(game.getTile(5, 7) instanceof Tile);
	}

	@Test
	public void setDimensionSetsDimensions() {
		assertEquals(5, game.width());
		assertEquals(7, game.height());
	}

	@Test
	public void setDimensionInitialisesBoardWithTiles() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				assertTrue(this.game.tiles().get(i).get(j) instanceof Tile);
			}
		}
	}

	@Test
	public void equalsChecksForIdenticalPlayers() {
		List<Player> otherPlayers = new ArrayList<Player>(
				Arrays.asList(
					new Player("Michael", 'M', new Tile(new Point(1, 1)), Player.Target.RIGHT), 
					player1
				)
		);

		IGame game2 = new Game (5, 5, otherPlayers);
		assertNotEquals(game, game2);
	}

	@Test
	public void equalsChecksForIdenticalBoardDimensions() {
		IGame game2 = new Game (7, 7, players);
		assertNotEquals(game, game2);
	}

	@Test
	public void equalsChecksForIdenticalTiles() {
		// @TODO
	}

	@Test
	public void equalsReturnsTrueForIdenticalGames() {
		IGame game2 = new Game(5, 7, players);

		assertEquals(game, game2);
	}

	@Test
	public void toStringReturnsUseableRepresentation() {
		assertEquals(String.format("Size: 5x7\nPlayers:\n%s\n%s\n", this.player1.toString(), this.player2.toString()), this.game.toString());
	}

	@Test
	public void isOverReturnsTrueIfAPlayerHasWon() throws TileOccupiedException {
		assertFalse(this.game.isOver());
		this.player1.jump(5, 1);
		assertTrue(this.game.isOver());
	}

	@Test
	public void switchCurrentPlayerMovesToNextPlayerInQueue() {
		assertEquals(this.player1, this.game.currentPlayer());
		this.game.switchCurrentPlayer();

		assertEquals(this.player2, this.game.currentPlayer());
		this.game.switchCurrentPlayer();

		assertEquals(this.player1, this.game.currentPlayer());
	}

	@Test
	public void playIsQuiet() throws ParserException, IOException {
		IGame game = new TestGame();
		game = Parser.parseFromFile("games/game1.txt", game);
		game.setUserInteraction(new TestUserInteraction(game));
		Renderer renderer = new Renderer(game);

		game.play(renderer);

		/*
		 * This test serves no real use, other than to demonstrate
		 * that, by using the service locator / dependency injection
		 * pattern, game.play() produces no visible output.
		 */
	}
}
