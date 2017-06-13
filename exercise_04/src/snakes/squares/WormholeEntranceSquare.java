package snakes.squares;

import snakes.Game;

import java.util.Random;

/**
 * Entrance to a wormhole, which transports the player to a random exit.
 *
 * The entrance is unaware of the exits, but queries them from the game when needed.
 */
public class WormholeEntranceSquare extends StandardSquare {
	public WormholeEntranceSquare(Game game, int position) {
		super(game, position);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Landing on a wormhole entrance square moves the player to a random exit. If
	 * no such exit exists, the player stays on this square.
	 *
	 * @return
	 */
	@Override
	public Square landHereOrGoHome() {
		try {
			return this.randomExit().landHereOrGoHome();
		} catch (NoWormholeExitsException e) {
			return this;
		}
	}

	@Override
	protected String squareLabel() {
		return super.squareLabel() + " (Entrance)";
	}

	/**
	 * Obtain a random wormhole exit from the game.
	 * @return a random exit
	 * @throws NoWormholeExitsException thrown if the game does not contain any wormholes
	 */
	private Square randomExit() throws NoWormholeExitsException {
		if (game.wormholeExits().size() == 0) {
			throw new NoWormholeExitsException();
		}
		return game.wormholeExits().get(new Random().nextInt(game.wormholeExits().size()));
	}
}