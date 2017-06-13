package snakes;

import org.junit.Before;

public abstract class SquareTest {
	protected Game game;
	protected Player jack, jill;

	void initializePlayers() {
		jack = new Player("Jack");
		jill = new Player("Jill");
	}

	void initializeGame(int size) {
		initializePlayers();
		game = new Game(size, new Player[] { jack, jill });
	}

	public abstract void newGame();
}
