package snakes;

import snakes.squares.Square;

/**
 * Representation of a single player in the game.
 *
 * A player can join a game, move on the board, win, and knows
 * the position.
 */
public class Player {
	private String name;

	/** Square on which the player currently is, should never be null after joining a game. */
	private Square square;

	private boolean invariant() {
		return name != null
				&& square != null;
	}

	public Player(String name) {
		this.name = name;
		// invariant holds only after joining a game
	}

	/**
	 * Join the given game by entering the first square.
	 */
	public void joinGame(Game game) {
		square = game.firstSquare();
		square.enter(this);
		assert invariant();
	}

	/**
	 * Current position in the game.
	 *
	 * This should only be called when the player already joined a game.
	 */
	public int position() {
		assert invariant();
		return square.position();
	}

	/**
	 * Move on the game board.
	 * @param moves amount of squares to move forward, must be > 0
	 */
	public void moveForward(int moves) {
		assert moves > 0;
		square.leave(this);
		square = square.moveAndLand(moves);
		square.enter(this);
	}

	public String toString() {
		return name;
	}

	/**
	 * Tests whether this player is on the last square and this a winner.
	 */
	public boolean wins() {
		return square.isLastSquare();
	}

	/**
	 * Leave the current square and step onto the given one.
	 *
	 * @param newSquare square to enter
	 * @return old square on which the player before changing it
	 */
	public Square swap(Square newSquare) {
		Square oldSquare = square;
		oldSquare.leave(this);
		newSquare.enter(this);
		square = newSquare;
		return oldSquare;
	}
}
