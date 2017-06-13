package snakes.squares;

import snakes.Game;
import snakes.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Start square that can have multiple players on it.
 */
public class FirstSquare extends StandardSquare {
	private List<Player> players;

	public FirstSquare(Game game, int position) {
		super(game, position);
		players = new ArrayList<>();
	}

	@Override
	public Square landHereOrGoHome() {
		return this;
	}

	@Override
	public boolean isOccupied() {
		return !players.isEmpty();
	}

	@Override
	public void enter(Player player) {
		assert !players.contains(player);
		players.add(player);
	}

	@Override
	public void leave(Player player) {
		assert players.contains(player);
		players.remove(player);
	}

	@Override
	public boolean isFirstSquare() {
		return true;
	}

	@Override
	protected String player() {
		StringBuffer buffer = new StringBuffer();
		for (Player player : players) {
			buffer.append("<" + player + ">");
		}
		return buffer.toString();
	}
}
