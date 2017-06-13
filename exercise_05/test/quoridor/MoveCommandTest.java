package quoridor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class MoveCommandTest
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
	public void executeCallsAppropriateMovementMethodOnPlayer() throws TileOccupiedException {
		new MoveCommand(MoveCommand.Direction.UP).execute(this.player);
		assertEquals(this.player.currentPosition(), this.game.getTile(3, 2));

		new MoveCommand(MoveCommand.Direction.LEFT).execute(this.player);
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 2));

		new MoveCommand(MoveCommand.Direction.DOWN).execute(this.player);
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 3));

		new MoveCommand(MoveCommand.Direction.RIGHT).execute(this.player);
		assertEquals(this.player.currentPosition(), this.game.getTile(3, 3));
	}

	@Test
	public void equalsChecksForEqualDirection() {
		assertEquals(new MoveCommand(MoveCommand.Direction.UP), new MoveCommand(MoveCommand.Direction.UP));
		assertNotEquals(new MoveCommand(MoveCommand.Direction.LEFT), new MoveCommand(MoveCommand.Direction.UP));
	}
}
