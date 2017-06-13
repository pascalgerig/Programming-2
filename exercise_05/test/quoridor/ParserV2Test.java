package quoridor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ParserV2Test
{
	private ParserV2 parser;

	@Before
	public void init() {
		this.parser = new ParserV2();
	}

	@Test
	public void parseHandlesSimpleBoard() throws ParserException {
		String input = "6 4 2 3\nJ    g\n      \njjj  G\n   gg \nJ John\nG George";
		IGame game = this.parser.parse(input);

		assertEquals(new Player("John", 'J', new Tile(new Point(1, 1)), null), game.players().get(0));
		assertEquals(new Player("George", 'G', new Tile(new Point(6, 3)), null), game.players().get(1));
		assertEquals(2, game.players().get(0).availableWalls());
		assertEquals(3, game.players().get(1).availableWalls());

		assertEquals(6, game.width());
		assertEquals(4, game.height());
	}

	@Test
	public void parseHandlesPlayerNameConsistingOfMultipleWords() throws ParserException {
		String input = "6 4 2 3\nJ    g\n      \njjj  G\n   gg \nJ John Jacobi\nG George Orwell";
		IGame game = this.parser.parse(input);

		assertEquals("John Jacobi", game.players().get(0).name());
		assertEquals("George Orwell", game.players().get(1).name());
	}

	@Test
	public void parseHandlesMoreThanTwoPlayers() throws ParserException {
		String input = "6 4 2 3 5\nJ    g\nS    s\njjj  G\n   gg \nJ John\nG George\nS Sascha";
		IGame game = this.parser.parse(input);

		assertEquals(new Player("John", 'J', new Tile(new Point(1, 1)), null), game.players().get(0));
		assertEquals(new Player("George", 'G', new Tile(new Point(6, 3)), null), game.players().get(1));
		assertEquals(new Player("Sascha", 'S', new Tile(new Point(1, 2)), null), game.players().get(2));
	}

	@Test
	public void parsePlacesWallsWhereIndicated() throws ParserException {
		String input = "6 4 2 3\nJ    g\n###   \njjj  G\n#  gg \nJ John\nG George";
		IGame game = this.parser.parse(input);

		assertTrue(game.getTile(1, 2).hasWall());
		assertTrue(game.getTile(2, 2).hasWall());
		assertTrue(game.getTile(1, 4).hasWall());
	}

	@Test
	public void parseSetsPlayerTargetTiles() throws ParserException {
		String input = "6 4 2 3\nJ    g\n###   \njjj  G\n#  gg \nJ John\nG George";
		IGame game = this.parser.parse(input);

		List<Tile> expected = new ArrayList<Tile>();
		expected.add(game.getTile(6, 1));
		expected.add(game.getTile(4, 4));
		expected.add(game.getTile(5, 4));

		assertEquals(expected, game.players().get(1).targetTiles());
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnNonNumericBoardSize() throws ParserException {
		String input = "6 a 2 3\nJ     \n     \njjj   \n      \nJ John";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExcceptionOnIncompleteLineEncountered() throws ParserException {
		String input = "6 a 3\nJ     \n     \njjj   \n      \nJ John";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnEmptyInput() throws ParserException {
		String input = "";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfInsufficientWallAllowancesSpecified() throws ParserException {
		String input = "6 4 2 3\nJ    g\nS    s\njjj  G\n   gg \nJ John\nG George\nS Sascha";
		IGame game = this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfInsufficientRowsSpecified() throws ParserException {
		String input = "6 4 2 3\nJ    g";
		IGame game = this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfInvalidPlayerEntryEncountered() throws ParserException {
		String input = "6 4 2 3\nJ    g\n      \njjj  G\n   gg \nJ John\nGeorge";
		IGame game = this.parser.parse(input);
	}
}
