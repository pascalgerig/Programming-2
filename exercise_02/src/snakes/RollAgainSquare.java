/**
 * 
 * A field which, upon being entered by a player, causes the player to roll a
 * second time.
 * 
 */
package snakes;

public class RollAgainSquare extends Square {
	public RollAgainSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public String squareLabel() {
		return String.format("%d (RollAgain)", position);
	}

	@Override
	public void enter(Player player) {
		super.enter(player);
		player.moveForward(game.getDie().roll());
	}
}
