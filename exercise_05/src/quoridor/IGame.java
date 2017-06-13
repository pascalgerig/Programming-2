package quoridor;

import java.awt.Point;
import java.util.List;

public interface IGame {

	/**
	 * lets next player be new currentPlayer
	 */
	void switchCurrentPlayer();

	/**
	 * Add additional player to the game.
	 *
	 * @param player Player which to add.
	 */
	void addPlayer(Player player);

	/**
	 * Checks Position (@param x, @param y) for beeing a valid Positon
	 * @return true if valid Position, false otherwise
	 */
	boolean isValidPosition(int x, int y);

	/**
	 * Set dimensions of game board.
	 *
	 * This will create new regular tiles to fill the board - overwriting
	 * whatever was there before.
	 *
	 * @param width Width of game board. Must be > 0;
	 * @param height Height of game board. Must be > 0;
	 */
	void setDimension(int width, int height);

	void setUserInteraction(IUserInteraction ui);

	/**
	 * Return tile at given position.
	 *
	 * Tile coordinates are 1-indexed, i.e. they go from 1 up to the
	 * board's width/height.
	 *
	 * @param x X / horizontal coordinate of tile. Must be >= 1 and <= board height.
	 * @param y Y / vertical coordinate of tile. Must be >= 1 and <= board width.
	 *
	 * @return Tile at given coordinates.
	 */
	Tile getTile(int x, int y);

	/**
	 * Get tile at given position.
	 *
	 * See {@link Game#getTile} for further details.
	 */
	Tile getTile(Point position);

	/**
	 * @return Game board's width.
	 */
	int width();

	/**
	 * @return Game board's height.
	 */
	int height();

	/**
	 * @return Game board's tiles.
	 */
	List<List<Tile>> tiles();

	/**
	 * @return Game's players.
	 */
	List<Player> players();

	/**
	 * @return Player whose turn it is.
	 */
	Player currentPlayer();

	/**
	 * Build wall between two points.
	 *
	 * @param from First endpoint of wall.
	 * @param to Second endpoint of wall.
	 *
	 * @throws TileOccupiedException if building the wall fails e.g. due to
	 * a tile being occupied by a player or wall, or if it would cut off
	 * one of the players.
	 */
	void buildWall(Point from, Point to) throws CommandRollbackException;

	/**
	 * Compare two game objects for equality.
	 *
	 * They are considered equal if:
	 * - Their width and height is equal
	 * - All their players are equal
	 * - All their tiles are equal
	 *
	 * @return Whether the two objects are equal.
	 */
	boolean equals(Object other);

	/**
	 * @return String representation of game object, suitable for
	 * consumption by humans.
	 */
	String toString();

	/**
	 * @return True if one of the game's players has reached his target.
	 */
	boolean isOver();

	/**
	 * Main game loop. Continuously ask players for commands and execute
	 * them, until one of the players has won the game.
	 *
	 * @param renderer Renderer which to use to render game state.
	 */
	void play(Renderer renderer);

	/**
	 * Convert this game's board to a two-dimensional array of integers
	 * suitable for the pathfinding algorithm.
	 *
	 * Free (passable) tiles will be represented as a 0, whereas walls will
	 * be represented as a 1.
	 *
	 * @return Two-dimensonal integer array representing game board.
	 */
	int[][] toPathFindingBoard();

}
