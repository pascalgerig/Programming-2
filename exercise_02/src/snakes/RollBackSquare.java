/**
 * 
 * Square which, when standing on, causes the next move of a player to be
 * backwards rather than forward.
 * 
 */
package snakes;

public class RollBackSquare extends Square {
	public RollBackSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public String squareLabel() {
		return String.format("%d (RollBack)", position);
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return super.moveAndLand(moves * -1);
	}
}
