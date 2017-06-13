package quoridor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest
{
	private IGame game;
	private Player player;

	@Before
	public void init() {
		this.game = new Game();
		this.game.setDimension(3, 3);
		this.player = new Player("John", 'J', this.game.getTile(3, 3), Player.Target.RIGHT);
		this.game.addPlayer(this.player);
		this.player.enterGame(this.game);
	}

	@Test
	public void equalsChecksForIdenticalName() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("John", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalSign() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("George", 'H', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalPosition() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("George", 'G', new Tile(new Point(2, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalTarget() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.DOWN));
	}

	@Test
	public void equalsReturnsTrueForIdenticalPlayers() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertEquals(player, new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void jumpMovesPlayerToNewPosition() throws TileOccupiedException {
		this.player.jump(2, 2);
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 2));
	}

	@Test
	public void moveUpMovesPlayerUpwards() throws TileOccupiedException {
		this.player.jump(2, 2);
		this.player.moveUp();
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 1));
		assertTrue(this.game.getTile(2, 1).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}

	@Test
	public void moveDownMovesPlayerDownwards() throws TileOccupiedException {
		this.player.jump(1, 1);
		this.player.moveDown();
		assertEquals(this.player.currentPosition(), this.game.getTile(1, 2));
		assertTrue(this.game.getTile(1, 2).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}

	@Test
	public void moveLeftMovesPlayerLeftwards() throws TileOccupiedException {
		this.player.jump(2, 2);
		this.player.moveLeft();
		assertEquals(this.player.currentPosition(), this.game.getTile(1, 2));
		assertTrue(this.game.getTile(1, 2).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}

	@Test
	public void moveRightMovesPlayerRightwards() throws TileOccupiedException {
		this.player.jump(1, 2);
		this.player.moveRight();
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 2));
		assertTrue(this.game.getTile(2, 2).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}

	@Test(expected = TileOccupiedException.class)
	public void moveThrowsTileOccupiedExceptionIfMovingOntoOccupiedTile() throws TileOccupiedException {
		Player player2 = new Player("George", 'G', new Tile(new Point(2, 3)), Player.Target.LEFT);
		this.game.addPlayer(player2);
		player2.enterGame(this.game);

		player2.moveRight();
	}

	@Test
	public void hasFinishedReturnsTrueIfPlayerIsOnFinishLine() throws TileOccupiedException {
		this.player.setTarget(Player.Target.UP);
		assertFalse(this.player.hasFinished());
		this.player.jump(3, 1);
		assertTrue(this.player.hasFinished());

		this.player.setTarget(Player.Target.LEFT);
		assertFalse(this.player.hasFinished());
		this.player.jump(1, 1);
		assertTrue(this.player.hasFinished());

		this.player.setTarget(Player.Target.DOWN);
		assertFalse(this.player.hasFinished());
		this.player.jump(1, 3);
		assertTrue(this.player.hasFinished());

		this.player.setTarget(Player.Target.RIGHT);
		assertFalse(this.player.hasFinished());
		this.player.jump(3, 3);
		assertTrue(this.player.hasFinished());
	}

	@Test
	public void hasFinishedReturnsTrueIfPlayerIsOnTargetTile() throws TileOccupiedException {
		this.player.jump(1, 1);
		this.player.addTargetTile(this.game.getTile(1, 2));
		assertFalse(this.player.hasFinished());
		this.player.jump(1, 2);
		assertTrue(this.player.hasFinished());
	}

	@Test
	public void toStringReturnsUseableRepresentation() {
		assertEquals("<John> [J] @ (3, 3) -> RIGHT", this.player.toString());
	}

	@Test
	public void placeWallPlacesWalls() throws CommandRollbackException {
		this.player.placeWall(new Point(2, 1), new Point(2, 2));
		assertTrue(this.game.getTile(2, 1).hasWall());
		assertTrue(this.game.getTile(2, 2).hasWall());
	}

	@Test(expected = AssertionError.class)
	public void placeWallIfNoWallsAvailableViolatesContract() throws CommandRollbackException {
		this.game.setDimension(10, 10);
		this.player.setAvailableWalls(2);
		this.player.placeWall(new Point(2, 1), new Point(2, 2));
		this.player.placeWall(new Point(3, 1), new Point(3, 2));
		this.player.placeWall(new Point(4, 1), new Point(4, 2));
	}

	@Test
	public void hasGameReturnsWhetherPlayerEnteredGame() {
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);

		assertFalse(player.hasGame());
		player.enterGame(this.game);
		assertTrue(player.hasGame());
	}

	@Test
	public void targetTilesContainsTilesOfTarget() {
		List<Tile> expected = new ArrayList<Tile>();
		expected.add(this.game.getTile(3, 1));
		expected.add(this.game.getTile(3, 2));
		expected.add(this.game.getTile(3, 3));

		assertEquals(expected, this.player.targetTiles());
	}

	@Test
	public void targetTilesContainsArbitrarySpecifiedTiles() {
		List<Tile> expected = new ArrayList<Tile>();
		expected.add(this.game.getTile(1, 2));
		expected.add(this.game.getTile(3, 1));

		this.player.setTarget(null);
		this.player.addTargetTile(this.game.getTile(1, 2));
		this.player.addTargetTile(this.game.getTile(3, 1));

		assertEquals(expected, this.player.targetTiles());
	}

	@Test
	public void targetTilesDoesNotContainBlockedTiles() {
		List<Tile> expected = new ArrayList<Tile>();
		expected.add(this.game.getTile(1, 2));

		this.player.setTarget(null);
		this.player.addTargetTile(this.game.getTile(1, 2));
		this.player.addTargetTile(this.game.getTile(3, 1));

		this.game.getTile(3, 1).setWall();

		assertEquals(expected, this.player.targetTiles());
	}

	/* Basic integration tests only, detailed testing done as unit tests of
	 * PathFinding class. */
	@Test
	public void canReachTargetReturnsTrueIfPathExists() throws TileOccupiedException {
		this.game.setDimension(5, 5);
		this.player.jump(1, 1);

		this.player.setTarget(null);
		this.player.addTargetTile(game.getTile(5, 5));

		/* Board will look like:
                 *   #######
                 *   #J   ##
                 *   #### ##
                 *   #  #  #
                 *   #  ## #
                 *   #  # j#
                 *   #######
		 */

		/* Walled-off section */
		this.game.getTile(1, 2).setWall();
		this.game.getTile(2, 2).setWall();
		this.game.getTile(3, 2).setWall();
		this.game.getTile(3, 3).setWall();
		this.game.getTile(3, 4).setWall();
		this.game.getTile(3, 5).setWall();

		/* Normal hindrances */
		this.game.getTile(5, 1).setWall();
		this.game.getTile(5, 2).setWall();
		this.game.getTile(4, 4).setWall();

		assertTrue(this.player.canReachTarget());
	}

	@Test
	public void canReachTargetReturnsFalseIfNoPathExists() throws TileOccupiedException {
		this.game.setDimension(5, 5);
		this.player.jump(1, 1);

		this.player.setTarget(null);
		this.player.addTargetTile(game.getTile(5, 5));

		/* Board will look like:
                 *   #######
                 *   #J#   #
                 *   # ### #
                 *   #  #  #
                 *   # ## ##
                 *   # #  j#
                 *   #######
		 */

		this.game.getTile(2, 1).setWall();

		this.game.getTile(2, 2).setWall();
		this.game.getTile(3, 2).setWall();
		this.game.getTile(4, 2).setWall();

		this.game.getTile(3, 3).setWall();

		this.game.getTile(2, 4).setWall();
		this.game.getTile(3, 4).setWall();
		this.game.getTile(5, 4).setWall();

		this.game.getTile(2, 5).setWall();

		assertFalse(this.player.canReachTarget());
	}
}
