package turtle;

/**
 * @author Michael Senn
 * @author Pascal Gerig
 * 
 * A command which makes a player move a number of steps into one of the eight
 * cardinal directions.
 */
public class MovementCommand implements ICommand
{
	/** 
	 * Enum representing the eight cardinal directions.
	 */
	public enum Direction {
		NORTH, EAST, SOUTH, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
	}

	private Direction direction;
	private int count;

	/**
	 * Initialize new instance of this class.
	 *
	 * Assumes number of steps to be `1`.
	 *
	 * @param direction Direction which to walk.
	 */
	public MovementCommand(Direction direction) {
		this.direction = direction;
		this.count = 1;
	}

	/**
	 * Initialize new instance of this class.
	 *
	 * @param direction Direction which to walk. (@Silas: you could/should also state and check the precondition for this param)
	 * @param count Number of steps which to take. Must be > 0.
	 */
	public MovementCommand(Direction direction, int count) {
		assert(count > 0);

		this.direction = direction;
		this.count = count;
	}

	/**
	 * Move player character according to given count and direction.
	 * 
	 * @Silas: you have a parameter here, so you should state and check its preconditions. For example, what happens if board is null?
	 * You should state whether or not you accept this case, and, if you do, how you will handle it. If you don't accept it, you should
	 * state that explicitly, and then assert it not to be null.
	 */
	public void execute(BoardMaker board) {
		switch(this.direction) {
			case NORTH:
				board.moveUp(this.count);
				break;
			case NORTH_EAST:
				board.moveUpRight(this.count);
				break;
			case NORTH_WEST:
				board.moveUpLeft(this.count);
				break;
			case EAST:
				board.moveRight(this.count);
				break;
			case SOUTH:
				board.moveDown(this.count);
				break;
			case SOUTH_EAST:
				board.moveDownRight(this.count);
				break;
			case SOUTH_WEST:
				board.moveDownLeft(this.count);
				break;
			case WEST:
				board.moveLeft(this.count);
				break;
		}
	}

	/** Compare commands for equality.
	 *
	 * Two commands are considered equal if their direction and number of
	 * steps to take are equal.
	 * 
	 * @Silas: again, the contract... Granted, there's not much to check here, but
	 * you'll still get an NPE if other == null
	 */
	public boolean equals(Object other) {
		MovementCommand cmd = (MovementCommand)other;
		return (cmd.direction == this.direction && cmd.count == this.count);
	}
}
