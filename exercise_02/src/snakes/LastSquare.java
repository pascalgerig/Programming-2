/* Special Square
 * 
 * Provides following functionality:
 * - First player to land on this square wins the game
 * 
 */

package snakes;

public class LastSquare extends Square {

	public LastSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public boolean isLastSquare() {
		return true;
	}
}
