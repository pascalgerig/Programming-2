package quoridor;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class ParserV1Test
{
	private ParserV1 parser;

	@Before
	public void init() {
		this.parser = new ParserV1();
	}

	@Test
	public void parseHandlesSimpleBoard() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\nGeorge G 3 4 D";
		IGame game = this.parser.parse(input);

		assertEquals(new Player("John", 'J', new Tile(new Point(1, 1)), Player.Target.RIGHT), game.players().get(0));
		assertEquals(new Player("George", 'G', new Tile(new Point(3, 4)), Player.Target.DOWN), game.players().get(1));
		assertEquals(7, game.width());
		assertEquals(9, game.height());
	}

	@Test
	public void parseHandlesPlayerNameConsistingOfMultipleWords() throws ParserException {
		String input = "7 9\nWill Smith J 1 1 L\nGeorge Orwell G 3 4 D";
		IGame game = this.parser.parse(input);

		assertEquals("Will Smith", game.players().get(0).name());
		assertEquals("George Orwell", game.players().get(1).name());
	}

	@Test
	public void parseHandlesMoreThanTwoPlayers() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\nGeorge G 3 4 D\nWilliam W 4 5 U\n";
		IGame game = this.parser.parse(input);

		assertEquals(new Player("John", 'J',    new Tile(new Point(1, 1)), Player.Target.RIGHT), game.players().get(0));
		assertEquals(new Player("George", 'G',  new Tile(new Point(3, 4)), Player.Target.DOWN), game.players().get(1));
		assertEquals(new Player("William", 'W', new Tile(new Point(4, 5)), Player.Target.UP), game.players().get(2));
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfLessThanTwoPlayersListed() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\n";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfInvalidTargetEncountered() throws ParserException {
		String input = "7 9\nJohn J 1 1 X\nGeorge G 3 4 W";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnNonNumericCoordinates() throws ParserException {
		String input = "7 9\nJohn J 1 a R\nGeorge G 3 4 D";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnNonNumericBoardSize() throws ParserException {
		String input = "7 a\nJohn J 1 1 R\nGeorge G 3 4 D";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExcceptionOnIncompleteLineEncountered() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\nGeorge G 3";
		this.parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnEmptyInput() throws ParserException {
		String input = "";
		this.parser.parse(input);
	}
}
