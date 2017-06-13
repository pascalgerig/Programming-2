package quoridor;

import java.awt.Point;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Class to parse and validate user-supplied commands.
 */
public class CommandParser implements ICommandParser
{
	/* TODO: Could not get Pattern.compile("...", Pattern.CASE_INSENSITIVE)
	 * to work, for some reason. */
	private static Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^([udlr])$");
	private static Pattern WALL_COMMAND_PATTERN = Pattern.compile("^wall ([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+)$");

	private IGame game;

	/**
	 * Create new instance of this class.
	 *
	 * @param game Game which the supplied commands are intended for. Is
	 * needed for validation purposes.
	 */
	public CommandParser(IGame game) {
		this.game = game;
	}

	/* (non-Javadoc)
	 * @see quoridor.ICommandParser#parse(java.lang.String)
	 */
	@Override
	public ICommand parse(String input) throws ParserException, CommandInvalidException {
		try {
			return parseMoveCommand(input.toLowerCase());
		} catch (ParserException e) {
			return parseWallCommand(input.toLowerCase());
		}
	}

	private ICommand parseMoveCommand(String input) throws ParserException, CommandInvalidException {
		Matcher matcher = MOVE_COMMAND_PATTERN.matcher(input);
		if (matcher.matches()) {
			MoveCommand.Direction direction;
			switch (matcher.group(1)) {
				case "u":
					direction = MoveCommand.Direction.UP;
					break;
				case "d":
					direction = MoveCommand.Direction.DOWN;
					break;
				case "l":
					direction = MoveCommand.Direction.LEFT;
					break;
				case "r":
					direction = MoveCommand.Direction.RIGHT;
					break;
				default:
					throw new ParserException("Invalid move direction.");
			}

			return validateMoveCommand(new MoveCommand(direction));
		} else {
			throw new ParserException("Invalid move command.");
		}
	}

	private ICommand parseWallCommand(String input) throws ParserException, CommandInvalidException {
		Matcher matcher = WALL_COMMAND_PATTERN.matcher(input);

		if (matcher.matches()) {
			Point from = new Point(
					Integer.parseInt(matcher.group(1)),
					Integer.parseInt(matcher.group(2))
			);

			Point to = new Point(
					Integer.parseInt(matcher.group(3)),
					Integer.parseInt(matcher.group(4))
			);

			return validateWallCommand(new WallCommand(from, to));
		}

		throw new ParserException("Invalid wall command.");
	}

	/* TODO:
	 * The two validations below don't belong here. They introduce and -
	 * worse - duplicate game logic (e.g. how does 'UP' map to coordinates)
	 * to the parser.
	 * Instead, one should try to simply apply the given commands and -
	 * when one of the inner classes throws an exception - catch it, and
	 * let the player enter a second command.
	 *
	 * If one wants to do pre-emptive validation (which has its merits),
	 * then it should be a method provided by ICommand, e.g.
	 * ICommand#validInContext(Game, Player).
	 */
	private WallCommand validateWallCommand(WallCommand cmd) throws CommandInvalidException {
		int deltaX = Math.abs(cmd.to().x - cmd.from().x);
		int deltaY = Math.abs(cmd.to().y - cmd.from().y);

		if (deltaX > 1) {
			throw new CommandInvalidException("Too big gap on x axis.");
		}
		if (deltaY > 1) {
			throw new CommandInvalidException("Too big gap on y axis.");
		}

		if (deltaX + deltaY != 1) {
			throw new CommandInvalidException("Coordinates not 1 apart.");
		}

		if (!this.game.isValidPosition(cmd.to().x, cmd.to().y) || !this.game.isValidPosition(cmd.from().x, cmd.from().y)) {
			throw new CommandInvalidException("Coordinates outside of game field.");
		}

		Tile from = this.game.getTile(cmd.from());
		Tile to = this.game.getTile(cmd.to());

		if (from.isOccupied() || to.isOccupied()) {
			throw new CommandInvalidException("Mr Trump, you can't place a wall on top of people!");
		}

		if (from.hasWall() || to.hasWall()) {
			throw new CommandInvalidException("Mr Trump, this wall is high enough already.");
		}

		return cmd;
	}

	/* TODO: Dito */
	private MoveCommand validateMoveCommand(MoveCommand cmd) throws CommandInvalidException {
		Player player = this.game.currentPlayer();
		Point pos = player.position();

		switch (cmd.direction()) {
			case UP:
				if (!this.game.isValidPosition(pos.x, pos.y - 1)) {
					throw new CommandInvalidException("Leaving field at the top.");
				}
				break;
			case DOWN:
				if (!this.game.isValidPosition(pos.x, pos.y + 1)) {
					throw new CommandInvalidException("Leaving field at the bottom.");
				}
				break;
			case LEFT:
				if (!this.game.isValidPosition(pos.x - 1, pos.y)) {
					throw new CommandInvalidException("Leaving field at the left.");
				}
				break;
			case RIGHT:
				if (!this.game.isValidPosition(pos.x + 1, pos.y)) {
					throw new CommandInvalidException("Leaving field at the right.");
				}
				break;
		}
		return cmd;
	}
}
