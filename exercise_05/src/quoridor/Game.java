package quoridor;

import java.awt.Point;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


/**
 * Represents a Quoridor game.
 */
public class Game implements IGame {
	protected int width;
	protected int height;

	protected Player winner;
	protected Player currentPlayer;

	protected List<Player> players;

	protected List<List<Tile>> tiles;

	private IUserInteraction userInteraction;

	/**
	 * Create a new instance of this class.
	 *
	 * This will yield you an incomplete game object, which you will have
	 * to complete by calling further methods on.
	 */
	public Game() {
		this.tiles = new ArrayList<List<Tile>>();
		this.players = new ArrayList<Player>();
		this.winner = null;
		this.userInteraction = new UserInteraction(this);
	}

	public void setUserInteraction(IUserInteraction ui) {
		this.userInteraction = ui;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#switchCurrentPlayer()
	 */
	@Override
	public void switchCurrentPlayer()
	{
		int i = players.indexOf(currentPlayer);
		currentPlayer = players.get((i+1) % players.size());
	}

	/**
	 * Create a new instance of this class.
	 *
	 * This will yield you a fully-complete game object, which can be used
	 * straight away. Nevertheless, you can call additional methods - to
	 * e.g. add players - if you so desire.
	 *
	 * @param width Width of game board. Must be > 0;
	 * @param height Height of game board. Must be > 0;
	 * @param players List of players participating in game.
	 */
	public Game(int width, int height, List<Player> players) {
		setDimension(width, height);
		this.players = new ArrayList<Player>();
		for (Player p : players) {
			this.players.add(p);
		}
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#addPlayer(quoridor.Player)
	 */
	@Override
	public void addPlayer(Player player) {
		this.players.add(player);
		if (this.currentPlayer == null) {
			this.currentPlayer = player;
		}
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#isValidPosition(int, int)
	 */
	@Override
	public boolean isValidPosition(int x, int y)
	{
		return 0 < x && x <= width && 0 < y && y <= height;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#setDimension(int, int)
	 */
	@Override
	public void setDimension(int width, int height) {
		assert(width > 0);
		assert(height > 0);

		this.width = width;
		this.height = height;

		this.tiles = new ArrayList<List<Tile>>();
		for (int i = 1; i <= width; i++) {
			this.tiles.add(new ArrayList<Tile>());
			for (int j = 1; j <= height; j++) {
				/* Bloody requirement to use 1-indexed
				 * coordinates */
				this.tiles.get(i - 1).add(new Tile(new Point(i, j)));
			}
		}
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#getTile(int, int)
	 */
	@Override
	public Tile getTile(int x, int y) {
		assert(x >= 1);
		assert(x <= this.width);
		assert(y >= 1);
		assert(y <= this.height);

		return this.tiles.get(x - 1).get(y - 1);
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#getTile(java.awt.Point)
	 */
	@Override
	public Tile getTile(Point position) {
		return this.getTile(position.x, position.y);
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#width()
	 */
	@Override
	public int width() {
		return this.width;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#height()
	 */
	@Override
	public int height() {
		return this.height;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#tiles()
	 */
	@Override
	public List<List<Tile>> tiles() {
		/* It's a bit silly to wrap it in accessor, as it's a mutable
		 * object. */
		return this.tiles;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#players()
	 */
	@Override
	public List<Player> players() {
		/* It's a bit silly to wrap it in accessor, as it's a mutable
		 * object. */
		return this.players;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#currentPlayer()
	 */
	@Override
	public Player currentPlayer() {
		return this.currentPlayer;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#buildWall(java.awt.Point, java.awt.Point)
	 */
	@Override
	public void buildWall(Point from, Point to) throws CommandRollbackException {
		getTile(from).setWall();
		getTile(to).setWall();

		if (!allPlayersAbleToReachTarget()) {
			getTile(from).unsetWall();
			getTile(to).unsetWall();
			/* Note that we don't have to refund the player, as we
			 * throw an exception before he substracts.
			 * However, relying on an implementation detail like
			 * this is bound to bite us in the ass sooner or later. */

			throw new CommandRollbackException("You may not box in players.");
		}
	}

	/**
	 * Check whether all players are able to reach their targets.
	 */
	private boolean allPlayersAbleToReachTarget() {
		for (Player player : this.players) {
			if (!player.canReachTarget()) {
				return false;
			}
		}

		return true;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Game)) {
			return false;
		}

		IGame game = (IGame)other;

		return (
				game.width() == this.width &&
				game.height() == this.height &&
				game.players().equals(this.players) &&
				game.tiles().equals(this.tiles)
		);
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#toString()
	 */
	@Override
	public String toString() {
		String size = String.format("%sx%s", this.width, this.height);
		String players = "";
		for (Player p : this.players) {
			players += p.toString();
			players += "\n";
		}

		return String.format("Size: %s\nPlayers:\n%s", size, players);
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#isOver()
	 */
	@Override
	public boolean isOver() {
		for (Player player : this.players) {
			if (player.hasFinished()) {
				this.winner = player;
				return true;
			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#play(quoridor.Renderer)
	 */
	@Override
	public void play(Renderer renderer)
	{
		for (Player player : this.players) {
			player.enterGame(this);
		}
		System.out.println(renderer.render());

		while(!this.isOver()) {
			ICommand command = this.userInteraction.askNextCommand();
			try {
				command.execute(this.currentPlayer);
				this.switchCurrentPlayer();
			} catch (TileOccupiedException e) {
				System.out.println("You tried to move onto an occupied Tile, please make another move!");
			} catch (CommandRollbackException e) {
				System.out.println(String.format("\n>>>> Error <<<<<\n%s\nPlease enter another command.\n", e.getMessage()));
			}

			System.out.println(renderer.render());
		}

		System.out.println(String.format("Congratulations %s, you have won!", this.winner.name()));
	}

	/**
	 * Read specification from gamess/game1.txt, and start game.
	 */
	public static void main(String[] args) throws ParserException, IOException {
		IGame game = Parser.parseFromFile("games/game1.txt");
		Renderer renderer = new Renderer(game);

		game.play(renderer);
	}

	/* (non-Javadoc)
	 * @see quoridor.IGame#toPathFindingBoard()
	 */
	@Override
	public int[][] toPathFindingBoard() {
		int[][] board = new int[this.width][this.height];

		for (int i = 1; i <= this.width; i++) {
			for (int j = 1; j <= this.height; j++) {
				if (getTile(i, j).hasWall()) {
					board[i - 1][j - 1] = 1;
				} else {
					board[i - 1][j - 1] = 0;
				}
			}
		}
		return board;
	}
}
