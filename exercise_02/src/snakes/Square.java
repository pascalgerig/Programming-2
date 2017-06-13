/** 
 * 	A square which allowes a maximum of one player to stand on it. In case a second player tries to land when it's occupied, the player is beeing sent to the first square.
 */
package snakes;

public class Square implements ISquare {

	protected int position;
	protected Game game;
	private Player player;

	private boolean invariant() {
		return game != null
				&& game.isValidPosition(position);
	}

	public Square(Game game, int position) {
		this.game = game;
		this.position = position;
		assert invariant();
	}

	public int position() {
		return this.position;
	}

	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves).landHereOrGoHome();
	}

	protected ISquare nextSquare() {
		return game.getSquare(position+1);
	}

	protected ISquare previousSquare() {
		return game.getSquare(position-1);
	}

	public ISquare landHereOrGoHome() {
		return this.isOccupied() ? game.firstSquare() : this;
	}

	public boolean isWormholeExit(){
		return false;
	}

	public String toString() {
		return "[" + this.squareLabel() + this.player() + "]";
	}

	protected String squareLabel() {
		return Integer.toString(position);
	}

	public boolean isOccupied() {
		return player != null;
	}

	public void enter(Player player) {
		assert this.player == null;
		this.player = player;
	}

	public void leave(Player player) {
		assert this.player == player;
		this.player = null;
	}

	public boolean isFirstSquare() {
		return false;
	}

	public boolean isLastSquare() {
		return false;
	}

	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}
}
