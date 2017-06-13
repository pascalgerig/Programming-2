package turtle;

/**
 * @author Pascal Gerig 
 * @author Michael Senn
 *
 *  An interface to be implemented by classes which are to be used as commands
 * in the turtle games.
 *
 * Commands can be any type of instruction, like 'north 5', 'jump 12 5',
 * 'circle 3' etc.
 */
public interface ICommand
{
	/**
	 * Apply this command instruction to a game board. Allows this command
	 * to modify the game board via one of its exposed methods, e.g. moving
	 * the character in one of the supported ways.
	 *
	 * @param board Game board which command is to be applied on.
	 */
	public void execute(BoardMaker board);


	/** 
	 * Check whether this command is identical to another command. Must be
	 * implemented in a meaningful way - e.g. two movement commands should
	 * be identical if they move the same number of steps into the same
	 * direction.
	 *
	 * @param other Object which to compare equality with.
	 * @return Whether the compared objects are equal.
	 */
	public boolean equals(Object other);
}
