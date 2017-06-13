package snakes;

import snakes.squares.*;

import java.util.*;

/**
 * Representation of a single instance of a Snakes & Ladders game.
 *
 * A game is responsible for keeping track of the squares and the players and
 * offers methods to play the game, query the board, and change board squares.
 */
public class Game {
	private List<Square> squares;
	private int size;
	/**
	 *
	 */
	private Deque<Player> players;

	/**
	 * Winner of the game, if any, null otherwise.
	 */
	private Player winner;

	/**
	 * Single die instance that all players are required to use.
	 */
	private IDie die;

	private boolean invariant() {
		return squares.size() > 3
				&& size == squares.size()
				&& players.size() > 1;
	}

	/**
	 * Initialize the game with the given size and players.
	 *
	 * The players start in the order they appear in the parameter.
	 * @param size amount of squares on the board
	 * @param players game participants
	 * @param dieSides number of sides the die will contain
	 */
	public Game(int size, Queue<Player> players, int dieSides) {
		this.size = size;
		this.addSquares(size);
		this.addPlayers(players);
		this.die = new Die(dieSides);
		assert invariant();
	}

	public boolean isValidPosition(int position) {
		return position >= 1 && position <= size;
	}

	/**
	 * Initialize and play a sample game.
	 */
	public static void main(String args[]) {
		Queue<Player> players = new LinkedList<>();
		players.add(new Player("Jack"));
		players.add(new Player("Jill"));
		IDie die = new Die(6);
		Game game = new Game(15, players, 6);
		game.setSquareToLadder(2, 4);
		game.setSquareToLadder(6, 2);
		game.setSquareToSnake(11, -6);
		game.play(die);
	}

	/**
	 * Runs the main loop of the game until there is a winner.
	 *
	 * Starts the game by letting the first player move. Prints actions
	 * and log statements to stdout and announces the winner once the
	 * game is over.
	 */
	public void play(IDie die) {
		System.out.println("Initial state: " + this);
		while (this.notOver()) {
			int roll = die.roll();
			System.out.println(this.currentPlayer() + " rolls " + roll + ":  " + this);
			this.movePlayer(roll);
		}
		System.out.println("Final state:   " + this);
		try {
			System.out.println(this.winner() + " wins!");
		} catch (GameNotOverException e) {
			System.out.println("No winner.");
		}
	}


	public boolean notOver() {
		return winner == null;
	}

	public boolean isOver() {
		return !this.notOver();
	}

	/**
	 * Player that is currently executing his move.
	 */
	public Player currentPlayer() {
		return players.peek();
	}

	/**
	 * Move the current player by the given amount of squares and
	 * let the player land on the destination.
	 * @param roll amount of squares to move
	 */
	public void movePlayer(int roll) {
		assert roll>=1 /*&& roll<= die.faces*/;
		Player currentPlayer = players.peek();
		currentPlayer.moveForward(roll);
		players.remove(); // remove the player from the front
		players.add(currentPlayer); // add him to back of the queue
		if (currentPlayer.wins()) {
			winner = currentPlayer;
		}
		assert invariant();
	}

	/**
	 * Replace the square at the given position.
	 * @param position position of the square
	 * @param newSquare square that replaces the current one
	 */
	public void setSquare(int position, Square newSquare) {
		// Do not change the type of the first or last square!
		assert !this.getSquare(position).isLastSquare()
				&& !this.getSquare(position).isFirstSquare();
		this.initSquare(position, newSquare);
		assert invariant();
	}

	/**
	 * @return the winning player if the game is over
	 * @throws GameNotOverException if called before the game is over
	 */
	public Player winner() throws GameNotOverException {
		if (notOver()) {
			throw new GameNotOverException();
		}
		return winner;
	}

	/**
	 * Obtain the unique starting square.
	 */
	public Square firstSquare() {
		return squares.get(0);
	}

	/**
	 * Obtain the square at the given position.
	 * @param position position on the board, must be within the bounds of the board
	 * @return the square at the position
	 */
	public Square getSquare(int position) {
		assert this.isValidPosition(position);
		return squares.get(position - 1);
	}

	/**
	 * String representation of the game board.
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Square square : squares) {
			buffer.append(square.toString());
		}
		return buffer.toString();
	}

	/**
	 * Populate the board with a FirstSquare, followed by size - 2 StandardSquare instances,
	 * followed by a LastSquare.
	 *
	 * @param size the number of squares to be added, must be >= 2
	 */
	private void addSquares(int size) {
		assert size >= 2;

		squares = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			StandardSquare square = new StandardSquare(this, i + 1);
			squares.add(square);
		}
		this.initSquare(1, new FirstSquare(this, 1));
		this.initSquare(size, new LastSquare(this, size));
	}

	/**
	 * Add the players to the game.
	 *
	 * Replaces any players that have joined already.
	 *
	 * @param participants The players to be added, must not be null
	 */
	private void addPlayers(Queue<Player> participants) {
		assert participants != null;

		players = new LinkedList<>();
		players.addAll(participants);
		for (Player player : players) {
			player.joinGame(this);
		}
	}

	/**
	 * Sets the square at the given position.
	 * @param position position on the board
	 * @param square new square
	 */
	private void initSquare(int position, Square square) {
		assert this.isValidPosition(position) && square != null;
		squares.set(position - 1, square);
	}

	/**
	 * Inserts a ladder at the given position
	 * @param position
	 * @param transport ladder length
	 */
	public void setSquareToLadder(int position, int transport) {
		this.setSquare(position, new LadderSquare(transport, this, position));
	}

	/**
	 * Inserts a snake at the given position
	 * @param position
	 * @param transport snake length
	 */
	public void setSquareToSnake(int position, int transport) {
		this.setSquare(position, new SnakeSquare(transport, this, position));
	}

	/**
	 * Look up the square that is `moves` squares away from `position`.
	 *
	 * If the move would go out of bounds, the remaining steps are taken backwards.
	 *
	 * @param position start position
	 * @param moves steps
	 * @return the target square
	 */
	public Square findSquare(int position, int moves) {
		assert position + moves <= 2 * size - 1; // can't go more than size-1 moves backwards past end
		int target = position + moves;
		if (target > size) { // reverse direction if we go past the end
			target = size - (target - size);
		}
		return this.getSquare(target);
	}

	/**
	 * Obtain a list of all wormhole exit instances on the board.
	 * @return list of wormhole exits
	 */
	public List<Square> wormholeExits() {
		List<Square> exits = new LinkedList<>();
		for (Square square : squares) {
			if (square.isWormholeExit()) { exits.add(square); };
		}
		return exits;
	}

	public IDie getDie() {
		return die;
	}

	/**
	 * Peek at the next player without changing the queue.
	 *
	 * Note that this is the player that comes next after the {@link Game#currentPlayer()}.
	 * @return the player that comes next
	 */
	public Player nextPlayer() {
		Player current = players.remove();
		Player next = players.peek();
		players.addFirst(current);
		return next;
	}
}