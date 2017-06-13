package snakes;

/**
 * An interface to be implemented by classes which are to be used as squares in the 'Snakes and Ladders' game.
 * 
 * @author Pascal Gerig 
 * @author Michael Senn
 *
 */
public interface ISquare {
	/**
	 * @return Position of this square on the field, starting at 1.
	 */
	public int position();

	/**
	 * Determines the field on which a player - when starting on this
	 * square and moving by a certain amount - lands on. This allows a
	 * source square to influencec the movement behaviour of players which
	 * are standing on it.
	 *
	 * A simple implementation will return the square `moves` fields ahead
	 * of itself.
	 * 
	 * @param moves Number of fields to advance
	 * @return Square which to land on after having moved `moves` squares.
	 */
	public ISquare moveAndLand(int moves);

	/**
	 * @return Whether this square is the first square.
	 */
	public boolean isFirstSquare();

	/**
	 * @return Whether this square is the last square.
	 */
	public boolean isLastSquare();

	/**
	 * Called by player object when entering this square.
	 *
	 * Allows to define additional behaviour on entering this square - e.g.
	 * triggering a second roll of the dice.
	 * 
	 * @param player Player object which entered the square.
	 */
	public void enter(Player player);

	/**
	 * Called by player object when leaving this square.
	 *
	 * Allows to define additional behaviour on leaving a square.
	 * 
	 * @param player Player object which left the square.
	 */
	public void leave(Player player);

	/**
	 * @return Whether the square is occupied.
	 */
	public boolean isOccupied();

	/**
	 * Called by source square when player would end up on this field.
	 * This allows a destination square to overwrite the target square of
	 * an inbound player.
	 *
	 * A simple implementation will simply return itself.
	 * 
	 * @return Square which arriving player should land on.
	 */
	public ISquare landHereOrGoHome();

	/**
	 * @return Whether this square is a wormhole exit.
	 */
	public boolean isWormholeExit();
}
