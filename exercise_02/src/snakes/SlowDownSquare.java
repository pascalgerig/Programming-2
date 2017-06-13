/** 
 * A field which, upon being entered by a player, causes the player to only move half the amount of the number delivered by the die but at least one.
 */
package snakes;

public class SlowDownSquare extends Square {
	private final int SLOW_DOWN_FACTOR = 2;
	public SlowDownSquare(Game game, int position) {
		super(game, position);
	}
	public String squareLabel() {
		return super.squareLabel() + " (SlowDown)";
	}

	@Override
	public ISquare moveAndLand(int moves) {
		int halfedMoves = moves / SLOW_DOWN_FACTOR;
		if (halfedMoves == 0) {
			halfedMoves = 1;
		}

		return super.moveAndLand(halfedMoves);
	}
}
