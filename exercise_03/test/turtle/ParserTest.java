package turtle;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;

import turtle.Parser;
import turtle.ICommand;

public class ParserTest
{
	@Test
	public void parsesNorthCommand() throws ParserException {
		List<ICommand> cmdList = Parser.parse("north 1");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.northCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesEastCommand() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("east 1");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.eastCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesSouthCommand() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("south 1");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.southCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesWestCommand() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("west 1");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.westCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesListOfCommands() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("north 1\nsouth 1\nwest 1\njump 11 30\nnorth 1");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.northCommand(1),
					Parser.southCommand(1),
					Parser.westCommand(1),
					Parser.jumpCommand(11, 30),
					Parser.northCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesBidirectionalCommands() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("north east 1\nnorth west 1\nsouth east 1\nsouth west 1\n");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.northEastCommand(1),
					Parser.northWestCommand(1),
					Parser.southEastCommand(1),
					Parser.southWestCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesCommandsWithArbitraryLengthParameter() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("north 3\nsouth 12");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.northCommand(3),
					Parser.southCommand(12)
				)
			),
			cmdList
		);
	}

	@Test
	public void parseJumpCommands() throws ParserException {
		List<ICommand> cmdList = Parser.parse("jump 20 11");

		assertEquals(
			new ArrayList<ICommand>(
				Arrays.asList(
					Parser.jumpCommand(20, 11)
				)
			),
			cmdList
		);

	}

	@Test(expected = ParserException.class)
	public void throwsParsereExceptionOnUnknownDirection() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("giblbyboo 3");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnMissingNumber() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("north\n");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnMissingDirection() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("12\n");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnEmptyLine() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("\n");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnInvalidBidirectionalDirection1() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("north south 1\n");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnInvalidBidirectionalDirection2() throws ParserException  {
		List<ICommand> cmdList = Parser.parse("east west 1\n");
	}
}
