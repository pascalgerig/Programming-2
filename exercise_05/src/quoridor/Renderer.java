package quoridor;

import java.util.List;

/**
 * Renders a {@link Game} object.
 *
 * Supports text-based output only.
 */
public class Renderer
{
	private static final char WALL_SIGN = '#';

	private static final char BORDER_SIGN_HORIZONTAL = '-';
	private static final char BORDER_SIGN_VERTICAL   = '|';
	private static final char BORDER_SIGN_EDGE       = '*';

	private IGame game;
	private char[][] output;

	/**
	 * Create a new instance of this class.
	 *
	 * @param game To-be-rendered game. Must not be null.
	 */
	public Renderer(IGame game) {
		assert(game != null);

		this.game = game;
	}

	/**
	 * Render textual represenation of game.
	 *
	 * The game grid will be surrounded by walls, with empty grid tiles
	 * represented by a space, and each player represented by his defined
	 * sign.
	 *
	 * @return String containing a textual representation of the game.
	 */
	public String render() {
		resetOutput();
		renderBorder();
		renderPlayerWalls();
		renderTargetTiles();
		renderPlayers();

		return this.buildOutput();
	}

	/**
	 * Convert internal array of chars to an output string.
	 *
	 * @return Textual representation of output array.
	 */
	private String buildOutput() {
		StringBuilder out = new StringBuilder();
		for (char[] col : this.output) {
			for (char c : col) {
				out.append(c);
			}
			out.append("\n");
		}

		return out.toString();
	}

	/**
	 * Initialize internal output array to accomodate for given game
	 * object, with additional space for walls on each side. 
	 */
	private void resetOutput() {
		/* Add one additional row/column on each side which will
		 * contain the wall. */
		this.output = new char[this.game.height() + 2][this.game.width() + 2];

		for (int y = 0; y < this.output.length; y++) {
			for (int x = 0; x < this.output[0].length; x++) {
				this.output[y][x] = ' ';
			}
		}
	}

	/**
	 * Render all of the game's players.
	 */
	private void renderPlayers() {
		for (Player player : this.game.players()) {
			/* So, logically convluted crap ahead.
			 * Player position is tracked via a 1-based coordinate system.
			 * Our output array is 0-based.
			 * Our output array contains an additional row/column on each side for the wall.
			 *
			 * Hence, when mapping the player's position to an
			 * index in the output array, we have to subtract one
			 * to switch from 1-basedness to 0-basedness, then add
			 * 1 to accomodate for the wall column/row. Which
			 * leaves us with a difference of 0. */
			this.output[player.position().y][player.position().x] = player.sign();
		}
	}

	private void renderTargetTiles() {
		for (Player player : this.game.players()) {
			for (Tile tile : player.targetTiles()) {
				this.output[tile.position().y][tile.position().x] = Character.toLowerCase(player.sign());
			}
		}
	}

	/**
	 * Render walls around the game board.
	 *
	 * To-be-used sign will be taken from WALL_SIGN constant.
	 */
	private void renderBorder() {
		renderHorizontalLine(0, BORDER_SIGN_HORIZONTAL);
		renderHorizontalLine(this.output.length - 1, BORDER_SIGN_HORIZONTAL);
		renderVerticalLine(0, BORDER_SIGN_VERTICAL);
		renderVerticalLine(this.output[0].length - 1, BORDER_SIGN_VERTICAL);

		this.output[0][0] = BORDER_SIGN_EDGE;
		this.output[0][this.output[0].length - 1] = BORDER_SIGN_EDGE;
		this.output[this.output.length - 1][0] = BORDER_SIGN_EDGE;
		this.output[this.output.length - 1][this.output[0].length - 1] = BORDER_SIGN_EDGE;
	}

	/**
	 * renders Walls that have been set by Players
	 */
	private void renderPlayerWalls() {
		for(List<Tile> list : game.tiles()) {
			for(Tile tile : list) {
				//Why does this not work?!
				if(tile.hasWall()) {
					this.output[tile.position().y][tile.position().x] = WALL_SIGN;
				}
			}
		}
	}

	/**
	 * Render vertical line.
	 *
	 * @width Vertical coordinate where line will be drawn. Must be in
	 * valid range for given game.
	 */
	private void renderVerticalLine(int width, char symbol) {
		assert(width >= 0);
		assert(width < this.output[0].length);

		for (int y = 0; y < this.output.length; y++) {
			this.output[y][width] = symbol;
		}
	}

	/**
	 * Render horizontal line.
	 *
	 * @width Horizontal coordinate where line will be drawn. Must be in
	 * valid range for given game.
	 */
	private void renderHorizontalLine(int height, char symbol) {
		assert(height >= 0);
		assert(height < this.output.length);

		for (int x = 0; x < this.output[height].length; x++) {
			this.output[height][x] = symbol;
		}
	}
}
