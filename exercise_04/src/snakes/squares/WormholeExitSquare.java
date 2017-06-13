package snakes.squares;

import snakes.Game;

/**
 * Potential target for wormhole entrances.
 *
 * A player entering a wormhole always lands on a (random) WormholeExitSquare
 * in the game.
 */
public class WormholeExitSquare extends StandardSquare {

	public WormholeExitSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public boolean isWormholeExit() {
		return true;
	}

	@Override
	protected String squareLabel() {
		return super.squareLabel() + " (Exit)";
	}
}