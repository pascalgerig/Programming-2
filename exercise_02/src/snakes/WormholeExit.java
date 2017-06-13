/** Special Square
 * 
 * A field which, upon being entered by a player, does nothing special, but in case of a player entering a WormholeEntrance square, he will be teleportet to
 * eighter this or another WormholeExit square.
 * 
 */

package snakes;

public class WormholeExit extends Square {

	public WormholeExit(Game game, int position) {
		super(game, position);
	}

	@Override
	public boolean isWormholeExit(){
		return true;
	}

	public String squareLabel() {
		return super.squareLabel() + " (Exit)";
	}
}

