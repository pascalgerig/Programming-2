/** 
 * 	A field which, upon being entered by a player, causes the player to roll move double as far as the number provided by the die.
 */

package snakes;

public class SpeedUpSquare extends Square {
	private final int SPEED_UP_FACTOR = 2;
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}

	public String squareLabel() {
		return super.squareLabel() + " (SpeedUp)";
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return super.moveAndLand(moves * SPEED_UP_FACTOR);
	}
}
