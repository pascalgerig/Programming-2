package snakes.squares;

import snakes.Game;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Similar to ladders and snakes, but with two destinations that can be reached.
 *
 * Whenever a player lands on this square, the destination changes such that
 * the next player lands on a different destination. This results in alternating
 * between the two possible destinations.
 */
public class TikTokSquare extends StandardSquare {

	private final Queue<Integer> destinations;

	/**
	 * Initialize the square with the two destinations.
	 *
	 * The first destination is the one that is visited first.
	 * @param game host game, must not be null
	 * @param position position of the ladder start in the game
	 * @param destinationA first destination, visited first, must be a valid position in the game
	 * @param destinationB second destination, must be a valid position in the game
	 */
	public TikTokSquare(Game game, int position, int destinationA, int destinationB) {
		super(game, position);

		this.destinations = new LinkedList<>();
		this.destinations.add(destinationA);
		this.destinations.add(destinationB);
	}

	@Override
	public Square landHereOrGoHome() {
		int targetPosition = destinations.remove();
		destinations.add(targetPosition);
		return game.getSquare(targetPosition).landHereOrGoHome();
	}

	@Override
	public String toString() {
		return "[" + this.squareLabel() + this.player() + "]";
	}

	@Override
	protected String squareLabel() {
		return super.squareLabel() + " (TikTok)";
	}
}
