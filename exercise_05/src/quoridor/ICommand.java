package quoridor;

/**
 * Interface to be implemented by classes representing a player's command.
 */
public interface ICommand
{
	/* TODO: There must be a better way to allow implementations to throw their own exceptions? */
	/**
	 * Execute command onto given player.
	 *
	 * May call various methods of the player's API to e.g build walls,
	 * move around etc.
	 *
	 * @param player Player which to execute command on.
	 *
	 * @throws TileOccupiedException If moving onto an occupied tile.
	 * @throws CommandRollbackException If the executed command was denied
	 * by a higher instance. Used to tell all objects in the stack to undo
	 * the actions of this command.
	 */
	public void execute(Player player) throws TileOccupiedException, CommandRollbackException;
}
