package snakes.squares;

import snakes.Game;

/**
 * Goal square, which ends the game when a player lands on on of those.
 *
 * Trivial implementation that only changes the result of the isLastSquare() test.
 */
public class LastSquare extends StandardSquare {

	public LastSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public boolean isLastSquare() {
		return true;
	}
}
