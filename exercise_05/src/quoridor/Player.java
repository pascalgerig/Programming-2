package quoridor;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a Player of the quoridor game.
 * Can move by one step eighter up, down, left or right.
 * 
 * @author Pascal Gerig
 * @author Michael Senn
 *
 */
public class Player {
	private static final int WALLS_PER_PLAYER = 5;

	private IGame game;
	private final String name;
	private final char sign;

	private int availableWalls = WALLS_PER_PLAYER;

	private Tile tile;
	private Target target;
	private List<Tile> targetTiles = new ArrayList<Tile>();

	/**
	 * Target side of the player.
	 */
	public enum Target {
		LEFT, RIGHT, UP, DOWN
	}

	/**
	 * Create new instance of this class.
	 *
	 * @param name Player's name, used in various interactions with the
	 * user.
	 * @param sign Char used to represent player on game field.
	 * @param tile Tile on which player starts on.
	 * @param target Target side of game board which player must reach in
	 * order to win.
	 */
	public Player(String name, char sign, Tile tile, Target target) {
		assert !tile.isOccupied();
		this.name = name;
		this.sign = sign;
		this.tile = tile;
		this.tile.enter(this);
		this.target = target;
		this.game = null;
	}

	/**
	 * @return true if player is in a game, false otherwise
	 */
	public boolean hasGame()
	{
		return this.game != null;
	}

	/**
	 * lets a player enter a game.
	 * 
	 * @param game which player should enter
	 */
	public void enterGame(IGame game)
	{
		this.game = game;
	}

	/**
	 * @return Players name
	 */
	public String name() {
		return this.name;
	}

	/**
	 * @return Players sign
	 */
	public char sign() {
		return this.sign;
	}

	/**
	 * @return Tile on which Player is currently standing
	 */
	public Tile currentPosition() {
		return this.tile;
	}

	/**
	 * @return Players target
	 */
	public Target target() {
		return this.target;
	}

	/**
	 * @return Number of walls available to player.
	 */
	public int availableWalls() {
		return this.availableWalls;
	}

	/**
	 * Set number of walls available to player.
	 *
	 * @param val Value which to set it to.
	 */
	public void setAvailableWalls(int val) {
		assert val > 0;
		this.availableWalls = val;
	}

	/**
	 * Set target of player.
	 *
	 * @param target Target which to set it to.
	 */
	public void setTarget(Target target) {
		this.target = target;
	}

	public void addTargetTile(Tile target) {
		this.targetTiles.add(target);
	}

	/**
	 * @return Point object of Players current Position
	 */
	public Point position() {
		return this.tile.position();
	}

	/**
	 * Checks two objects for equality
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Player)) {
			return false;
		}

		Player player = (Player)other;

		return (
				player.name().equals(this.name) &&
				player.sign() == this.sign &&
				player.currentPosition().equals(this.tile) &&
				player.target() == this.target
		);
	}

	/**
	 * @return String representation of player object, suitable for
	 * consumption by humans.
	 */
	public String toString() {
		return String.format("<%s> [%s] @ (%s, %s) -> %s", this.name, this.sign, this.tile.position().x, this.tile.position().y, this.target);
	}

	/**
	 * Lets Player place a Wall between (xFrom, yFrom) and (xTo, yTo)
	 */
	public void placeWall(Point from, Point to) throws CommandRollbackException {
		assert(this.availableWalls > 0);
		game.buildWall(from, to);

		this.availableWalls--;
	}

	/**
	 * Make player 'jump' to new position.
	 *
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 *
	 * @throws TileOccupiedException if target tile is occupied.
	 */
	public void jump(int x, int y) throws TileOccupiedException {
		moveTo(this.game.getTile(x, y));

		assert(invariant());
	}

	/**
	 * Tries to move the player up by one field
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveUp() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x, this.position().y - 1));

		assert(invariant());
	}

	/**
	 * Tries to move the player down by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveDown() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x, this.position().y + 1));

		assert(invariant());
	}

	/**
	 * tries to move the player to the right by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveRight() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x + 1, this.position().y));

		assert(invariant());
	}

	/**
	 * tries to move the player to the left by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveLeft() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x - 1, this.position().y));

		assert(invariant());
	}

	private void moveTo(Tile next) throws TileOccupiedException {
		/* TODO: Throwing a TileOccupiedException should happen within
		 * the tile - it has the authority to decide whether another
		 * player fits or not.  However, care must be taken not to end
		 * up with a ghost player sitting on a tile.
		 */
		if (next.isOccupied() || next.hasWall()) {
			throw new TileOccupiedException("Field you want to move to is occupied!");
		} else {
			this.tile.leave();
			next.enter(this);
			this.tile = next;
		}
	}

	private boolean invariant() {
		return this.tile != null;
	}

	/**
	 * Checks if player is at Target
	 * 
	 * @return True if player is on a target tile, false otherwise.
	 */
	public boolean hasFinished() {
		return (this.targetTiles().contains(this.currentPosition()));
	}

	/**
	 * Check whether player is able to reach target column / row.
	 *
	 * @return True if player is able to reach at least one tile of the target column / row.
	 */
	public boolean canReachTarget() {
		for (Tile targetTile : targetTiles()) {
			// System.out.println(t.toStringd());
			PathFinding path = new PathFinding(this.game.toPathFindingBoard(), this.position(), targetTile.position());
			if (path.existsPath()) {
				/* Path to (at least) one target square found. */
				return true;
			}
		}
		/* No target square reachable. */
		return false;
	}

	/**
	 * @return List of tiles in target column / row. This will exclude
	 * tiles containing a wall, but will not exclude tiles on which a
	 * player resides, as those would be valid locations.
	 */
	public List<Tile> targetTiles() {
		/* @TODO: Counting an occupied tile as reachable makes sense,
		 * but could lead to a deadlock, where players are in a
		 * walled-off corridor, and block each other's way. 
		 */
		List<Tile> out = new ArrayList<Tile>();

		if (this.target != null) {
			switch (this.target) {
				case UP:
					for (int i = 1; i <= this.game.width(); i++) {
						out.add(this.game.getTile(i, 1));
					}
					break;
				case DOWN:
					for (int i = 1; i <= this.game.width(); i++) {
						out.add(this.game.getTile(i, this.game.height()));
					}
					break;
				case LEFT:
					for (int i = 1; i <= this.game.height(); i++) {
						out.add(this.game.getTile(1, i));
					}
					break;
				case RIGHT:
					for (int i = 1; i <= this.game.height(); i++) {
						out.add(this.game.getTile(this.game.width(), i));
					}
					break;
			}
		}

		for (Tile tile : this.targetTiles) {
			out.add(tile);
		}

		for (Iterator<Tile> iterator = out.iterator(); iterator.hasNext();) {
			Tile tile = iterator.next();
			if (tile.hasWall()) {
				iterator.remove();
			}
		}

		return out;
	}
}
