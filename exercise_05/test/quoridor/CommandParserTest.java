package quoridor;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class CommandParserTest
{
	private ICommandParser parser;
	private IGame game;
	private Player player;

	@Before
	public void init() {
		this.game = new Game();
		this.game.setDimension(3, 3);
		this.player = new Player("John", 'J', this.game.getTile(2, 2), Player.Target.DOWN);
		this.game.addPlayer(this.player);
		this.player.enterGame(this.game);

		this.parser = new CommandParser(game);
	}

	@Test
	public void parseHandlesMoveUpCommand() throws ParserException, CommandInvalidException {
		ICommand cmd = this.parser.parse("u");
		assertEquals(new MoveCommand(MoveCommand.Direction.UP), cmd);
	}

	@Test
	public void parseHandlesMoveDownCommand() throws ParserException, CommandInvalidException {
		ICommand cmd = this.parser.parse("d");
		assertEquals(new MoveCommand(MoveCommand.Direction.DOWN), cmd);
	}

	@Test
	public void parseHandlesMoveLeftCommand() throws ParserException, CommandInvalidException {
		ICommand cmd = this.parser.parse("l");
		assertEquals(new MoveCommand(MoveCommand.Direction.LEFT), cmd);
	}

	@Test
	public void parseHandlesMoveRightCommand() throws ParserException, CommandInvalidException {
		ICommand cmd = this.parser.parse("r");
		assertEquals(new MoveCommand(MoveCommand.Direction.RIGHT), cmd);
	}

	@Test
	public void parseIsCaseInsensitive() throws ParserException, CommandInvalidException {
		ICommand cmd = this.parser.parse("R");
		assertEquals(new MoveCommand(MoveCommand.Direction.RIGHT), cmd);
	}

	@Test
	public void parseHandlesWallCommand() throws ParserException, CommandInvalidException {
		ICommand cmd = this.parser.parse("wall 1 1 1 2");
		assertEquals(new WallCommand(new Point(1, 1), new Point(1, 2)), cmd);
	}

	@Test public void parseHandlesWallCommandsWithMultiDigitCoordinates() throws ParserException, CommandInvalidException {
		this.game.setDimension(20, 20);
		ICommand cmd = this.parser.parse("wall 10 10 11 10");
		assertEquals(new WallCommand(new Point(10, 10), new Point(11, 10)), cmd);
	}

	@Test(expected = CommandInvalidException.class)
	public void parseThrowsExceptionOnWallCommandReferencingTilesOutsideOfGame() throws ParserException, CommandInvalidException {
		this.parser.parse("wall 3 3 4 3");
	}

	@Test(expected = CommandInvalidException.class)
	public void parseThrowsExceptionOnWallCommandWithIdenticalCoordinates() throws ParserException, CommandInvalidException {
		this.parser.parse("wall 2 2 2 2");
	}

	@Test(expected = CommandInvalidException.class)
	public void parseThrowsExceptionOnWallCommandWithTooFarApartCoordinates() throws ParserException, CommandInvalidException {
		this.parser.parse("wall 1 1 1 3");
	}

	@Test(expected = CommandInvalidException.class)
	public void parseThrowsExceptionOnWallCommandWithDiagonalCoordinates() throws ParserException, CommandInvalidException {
		this.parser.parse("wall 1 1 2 2");
	}

	@Test(expected = ParserException.class)
	public void parseThrowsParserExceptionOnInvalidCommand() throws ParserException, CommandInvalidException {
		this.parser.parse("udlr");
	}

	@Test(expected = CommandInvalidException.class)
	public void parseThrowsParserExceptionOnMovementCommandLeavingField() throws ParserException, CommandInvalidException, TileOccupiedException {
		this.game.currentPlayer().jump(1, 1);
		this.parser.parse("u");
	}
}
