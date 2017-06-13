package turtle;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

/**
 * @author Michael Senn
 * @author Pascal Gerig
 * 
 * Fully-static (and stateless) class to parse a user-supplied Turtle program into a
 * sequence of commands which can be executed against a game board.
 */
public class Parser
{
	private static Pattern MOVEMENT_COMMAND_PATTERN = Pattern.compile("^(north|east|south|west|north east|north west|south east|south west) ([0-9]+)$");
	private static Pattern JUMP_COMMAND_PATTERN = Pattern.compile("^jump ([0-9]+) ([0-9]+)$");

	/**
	 * Parse a user-supplied Turtle-program, outputting a sequence of
	 * commands - classes implementing the ICommand interfacec - which can
	 * then be executed on a game board.
	 *
	 * @param program User-supplied program. Invalid input will lead to an exception. @Silas: maybe you should say what a valid input (or rather, turtle program) is, or where you 
	 * 																							you'd find a definition.
	 * @throws ParserException if the supplied input program is invalid.
	 * @return List of commands corresponding to the entered program.
	 */
	public static List<ICommand> parse(String program) throws ParserException {
		List<ICommand> cmdList = new ArrayList<ICommand>();

		Scanner scanner = new Scanner(program);

		while (scanner.hasNextLine()) {
			String cmd = scanner.nextLine();
			cmdList.add(parseCommand(cmd));
		}
		scanner.close();
		return cmdList;
	}

	private static ICommand parseCommand(String cmd) throws ParserException {
		ICommand command;

		command = parseJumpCommand(cmd);

		if (command == null) {
			command = parseMovementCommand(cmd);
		}

		if (command != null) {
			return command;
		} else {
			throw new ParserException();
		}
	}

	private static JumpCommand parseJumpCommand(String cmd) {
		Matcher matcher = JUMP_COMMAND_PATTERN.matcher(cmd);
		if (matcher.matches()) {
			int x = Integer.parseInt(matcher.group(1));
			int y = Integer.parseInt(matcher.group(2));

			return jumpCommand(x, y);
		} else {
			return null;
		}
	}

	private static MovementCommand parseMovementCommand(String cmd) {
		Matcher matcher = MOVEMENT_COMMAND_PATTERN.matcher(cmd);
		if (matcher.matches()) {
			String cmdDirection = matcher.group(1);
			int cmdCount = Integer.parseInt(matcher.group(2));

			switch(cmdDirection) {
				case "north":
					return northCommand(cmdCount);
				case "east":
					return eastCommand(cmdCount);
				case "south":
					return southCommand(cmdCount);
				case "west":
					return westCommand(cmdCount);
				case "north east":
					return northEastCommand(cmdCount);
				case "north west":
					return northWestCommand(cmdCount);
				case "south east":
					return southEastCommand(cmdCount);
				case "south west":
					return southWestCommand(cmdCount);
			}
		}
		return null;

	}

	protected static MovementCommand northCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.NORTH, count);
	}

	protected static MovementCommand eastCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.EAST, count);
	}

	protected static MovementCommand southCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.SOUTH, count);
	}

	protected static MovementCommand westCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.WEST, count);
	}

	protected static MovementCommand northEastCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.NORTH_EAST, count);
	}

	protected static MovementCommand northWestCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.NORTH_WEST, count);
	}

	protected static MovementCommand southEastCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.SOUTH_EAST, count);
	}

	protected static MovementCommand southWestCommand(int count) {
		return new MovementCommand(MovementCommand.Direction.SOUTH_WEST, count);
	}

	protected static JumpCommand jumpCommand(int x, int y) {
		return new JumpCommand(x, y);
	}
}
