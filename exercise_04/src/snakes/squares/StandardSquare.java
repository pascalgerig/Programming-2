package snakes.squares;

import snakes.Game;
import snakes.Player;

/**
 * Default square type
 *
 * A standard square allows only one player on it. If a player lands
 * on such a square and it is already occupied, the player is sent back
 * to the game's first square.
 */
public class StandardSquare implements Square {
	/** Position in the game */
	protected int position;

	/** Host game */
	protected Game game;

	/** Player currently occupying this square; no player is on the square if this is null */
	private Player player;

	private boolean invariant() {
		return game != null
				&& game.isValidPosition(position);
	}

	/**
	 * Initialize with the host game and position in it
	 * @param game game of which this square is a part of, must not be null
	 * @param position position of this square in the game, must be >= 1
	 */
	public StandardSquare(Game game, int position) {
		assert game != null;
		assert position > 0;

		this.game = game;
		this.position = position;

		assert invariant();
	}

	public int position() {
		return this.position;
	}

	/**
	 * {@inheritDoc}
	 *
	 * This implementation moves by simply advancing in the game and
	 * landing on the target square
	 */
	public Square moveAndLand(int moves) {
		return game.findSquare(position, moves).landHereOrGoHome();
	}

	/**
	 * {@inheritDoc}
	 *
	 * Landing on this square is successful if it is not occupied,
	 * otherwise the player is sent back to the first square
	 * @return this if the square is free, the game's first square otherwise
	 */
	public Square landHereOrGoHome() {
		return this.isOccupied() ? game.firstSquare() : this;
	}

	@Override
	public boolean isWormholeExit() {
		return false;
	}

	public String toString() {
		return "[" + this.squareLabel() + this.player() + "]";
	}

	/**
	 * Text representation of the square.
	 *
	 * Subclasses should override this method to include a
	 * way to distinguish different kinds of squares.
	 */
	protected String squareLabel() {
		return Integer.toString(position);
	}

	public boolean isOccupied() {
		return player != null;
	}

	public void enter(Player player) {
		assert this.player == null;
		this.player = player;
	}

	public void leave(Player player) {
		assert this.player == player;
		this.player = null;
	}

	public boolean isFirstSquare() {
		return false;
	}

	public boolean isLastSquare() {
		return false;
	}

	/**
	 * String representation of the player on this square, if there is one
	 * @return a string with the player name if a player is here, the empty string otherwise
	 */
	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}
}
