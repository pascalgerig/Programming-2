package quoridor;

/**
 * Command which represents the movement of a player in one of the four
 * cardinal directions.
 */
public class MoveCommand implements ICommand
{
	private Direction direction;

	/**
	 * Direction of movement.
	 */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	/**
	 * Create new instance of this class.
	 * @param direction Direction into which to move.
	 */
	public MoveCommand(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return Direction into which to move.
	 */
	public Direction direction() {
		return this.direction;
	}

	/**
	 * Execute command on player.
	 *
	 * @param player Player which to move.
	 * @throws TileOccupiedException if tile which player was tried to move on was occupied.
	 */
	public void execute(Player player) throws TileOccupiedException {
		switch (this.direction) {
			case UP:
				player.moveUp();
				break;
			case DOWN:
				player.moveDown();
				break;
			case LEFT:
				player.moveLeft();
				break;
			case RIGHT:
				player.moveRight();
				break;
		}
	}

	/**
	 * Compare two commands for equality.
	 *
	 * They are considered equal, if their movement direction is identical.
	 *
	 * @return Whether two commands are equal.
	 */
	public boolean equals(Object other) {
		if (!(other instanceof MoveCommand)) {
			return false;
		}


		MoveCommand cmd = (MoveCommand)other;

		return this.direction == cmd.direction();
	}
}
