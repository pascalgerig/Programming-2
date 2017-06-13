package quoridor;

import java.awt.Point;
import java.util.List;

public class TestGame extends Game implements IGame {
	@Override
	/**
	 * Same method as in Game.class but without generating Output
	 * logic remains the same
	 */
	public void play(Renderer renderer)
	{
		for (Player player : this.players) {
			player.enterGame(this);
		}

		IUserInteraction ui = new TestUserInteraction(this);

		while(!this.isOver()) {
			ICommand command = ui.askNextCommand();
			try {
				command.execute(this.currentPlayer);
				this.switchCurrentPlayer();
			} catch (TileOccupiedException e) {

			} catch (CommandRollbackException e) {

			}
		}
	}
}
