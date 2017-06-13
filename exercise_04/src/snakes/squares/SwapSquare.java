package snakes.squares;

import snakes.Game;

/**
 * Upon landing on a swap square, the player is swapped with
 * the player that comes next.
 */
public class SwapSquare extends StandardSquare {

	public SwapSquare(Game game, int position) {
		super(game, position);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Swaps positions with the next player.
	 * @return the new square on which the current player is
	 */
	@Override
	public Square landHereOrGoHome() {
		int otherPosition = game.nextPlayer().position();
		game.nextPlayer().swap(game.getSquare(position));
		return game.getSquare(otherPosition);
	}

	@Override
	protected String squareLabel() {
		return super.squareLabel() + " (Swap)";
	}
}
