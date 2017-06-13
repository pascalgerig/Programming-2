package snakes.squares;

import snakes.Game;

/**
 * A ladder square represents the beginning of a ladder.
 *
 * Keeps track of its length and can obtain the destination square in the game.
 * Ladders always move the player forward.
 */
public class LadderSquare extends StandardSquare {
	/** Length of the ladder. */
	private int transport;

	private boolean invariant() {
		return isValidTransport(transport);
	}

	/**
	 * Tests whether the transport is within the bounds of the game and > 0.
	 */
	protected boolean isValidTransport(int transport) {
		return transport > 0 &&
				game.isValidPosition(position + transport);
	}

	/**
	 * Initializes a new ladder for the given game.
	 * @param transport length of the ladder, must be within the bounds of the game and > 0
	 * @param game host game, must not be null
	 * @param position position of the ladder start in the game
	 */
	public LadderSquare(int transport, Game game, int position) {
		super(game, position);
		assert isValidTransport(transport);
		this.transport = transport;
		assert invariant();
	}

	@Override
	protected String squareLabel() {
		return position + "->" + this.destination().position();
	}

	@Override
	public Square landHereOrGoHome() {
		return this.destination().landHereOrGoHome();
	}

	/**
	 * Obtain the square where the ladder leads to in the game.
	 */
	protected Square destination() {
		return game.getSquare(position+transport);
	}
}
