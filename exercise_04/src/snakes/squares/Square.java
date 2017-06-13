package snakes.squares;

import snakes.Player;

/**
 * API for all types of squares
 *
 * A square must always be part of a game
 */
public interface Square {
	/**
	 * Obtain position in the host game.
	 *
	 * @return position in the game
	 */
	int position();

	/**
	 * Move a player away from this square and land on
	 * the square in the game that is `moves` steps away.
	 *
	 * @param moves amount of steps to take
	 * @return square on which the player rests after landing and executing all actions
	 */
	Square moveAndLand(int moves);

	/**
	 * Tests whether this is an instance of FirstSquare.
	 */
	boolean isFirstSquare();

	/**
	 * Tests whether this is an instance of LastSquare.
	 */
	boolean isLastSquare();

	/**
	 * whether this is an instance of WormholeExit.
	 */
	boolean isWormholeExit();

	/**
	 * Registers the given player, should be called when landing on a square.
	 *
	 * @param player the player that lands on this square
	 */
	void enter(Player player);

	/**
	 * Deregisters the given player, should be called when leaving a square.
	 *
	 * @param player the player that leaves this square
	 */
	void leave(Player player);

	/**
	 * Tests whether there is still room for players on this square.
	 *
	 * @return true, if this square can not register more players
	 */
	boolean isOccupied();

	/**
	 * Land on this square and execute any associated logic.
	 *
	 * In particular, this should include testing whether there is room
	 * on the square and performing other actions, such as climbing a ladder
	 *
	 * @return the square on which the player should rest after executing all associated actions
	 */
	Square landHereOrGoHome();
}
