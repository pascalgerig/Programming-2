package turtle;

/**
 * @author Michael Senn
 * @author Pascal Gerig
 * 
 * A command which makes the player character jump onto an arbitrary field,
 * without leaving a trace.
 */
public class JumpCommand implements ICommand
{
	private int x;
	private int y;

	/** 
	 * Initialize new instance of this class.
	 *
	 * @param x X coordinate, must be >= 0
	 * @param y Y coordinate, must be >= 0
	 */
	public JumpCommand(int x, int y) {
		assert(x >= 0);
		assert(y >= 0);

		this.x = x;
		this.y = y;
	}

	/**
	 * Move player charcter to location defined by supplied coordinates.
	 */
	public void execute(BoardMaker board) {
		board.jump(this.x, this.y);
	}

	/**
	 * Compare commands for equality. 
	 *
	 * Two commands are considered equal if their x and y coordinates are
	 * equal.
	 */
	public boolean equals(Object other) {
		JumpCommand cmd = (JumpCommand)other;
		return (this.x == cmd.x && this.y == cmd.y);
	}
}
