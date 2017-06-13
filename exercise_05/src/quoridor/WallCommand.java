package quoridor;

import java.awt.Point;

/**
 * Command which represents the building of a wall on the game board.
 */
public class WallCommand implements ICommand
{
	private Point from;
	private Point to;

	/**
	 * Create new instance of this class.
	 *
	 * The two endpoints of the wall must be exactly one tile - horizontal
	 * or vertical - apart.
	 *
	 * @param from First endpoint of wall.
	 * @param to Second endpoint of wall.
	 */
	public WallCommand(Point from, Point to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * @return First endpoint of wall.
	 */
	public Point from() {
		return this.from;
	}

	/**
	 * @return Second endpoint of wall.
	 */
	public Point to() {
		return this.to;
	}

	/**
	 * Execute command on player.
	 *
	 * This will make the player attempt to build the wall.
	 *
	 * @throws TileOccupiedException if building the wall failed, e.g.
	 * because there's a player where a wall was attempted to be built.
	 */
	public void execute(Player player) throws CommandRollbackException {
		if (player.availableWalls() < 1) {
			throw new CommandRollbackException("Not enough walls available.");
		}
		player.placeWall(this.from, this.to);
	}

	/**
	 * Compare two commands for equality.
	 *
	 * They are considered equal, if both their first and seecond endpoint
	 * are equal.
	 *
	 * @return Whether the two commands are equal.
	 */
	public boolean equals(Object other) {
		if (!(other instanceof WallCommand)) {
			return false;
		}


		WallCommand cmd = (WallCommand)other;

		return (
				this.from.x == cmd.from().x &&
				this.from.y == cmd.from().y &&
				this.to.x   == cmd.to().x &&
				this.to.y   == cmd.to().y
		);
	}

	/**
	 * @return String-based representation of this command.
	 */
	public String toString() {
		return String.format(
				"Wall: (%s, %s) -> (%s, %s)",
				from.x, from.y, to.x, to.y
		);
	}
}
