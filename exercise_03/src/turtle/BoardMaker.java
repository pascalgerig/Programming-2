
package turtle;

import java.awt.Point;


/**
 * @author Michael Senn
 * @author Pascal Gerig 
 *
 *
 *	Representation of the Board, handles the movement on this board.
 *
 *	Invariant: After first call of "initialBoard()" the position variable will be set at all time
 */
public class BoardMaker {
	private boolean[][] board;
	private final static int SIZE = 100;
	private Point position;
	private boolean autoTouch = true;
	
	
	private void invariant()
	{
		assert this.getPosition() != null;
	}

	public Point getPosition() {
		return this.position;
	}

	public boolean currentField() {
		return getFieldAt(this.position);
	}

	public boolean getFieldAt(Point position) {
		return this.board[position.x][position.y];
	}

	/**
	 * Parse the given turtle program and evaluate it. Render the trail as
	 * described in the problem description and return a SIZExSIZE board
	 * corresponding to the evaluated path.
	 *
	 * @param turtleProgram input program according to specification. may also contain invalid text!
	 * @return SIZExSIZE boolean board, where true values denote "red trail".
	 */
	public boolean[][] makeBoardFrom(String turtleProgram) throws ParserException {
		// Reset board
		this.initialBoard();

		Program program = new Program(turtleProgram);
		program.execute(this);

		assert(this.board.length == SIZE);
		this.invariant();
		return this.board;
	}

	/**
	 * (Re)initialize board with given size.
	 *
	 * This will purge the board, allowing (or requiring) a fresh start.
	 *
	 * @param size Length of board. Must be > 0.
	 * @return SIZExSIZE boolean board.
	 */
	public boolean[][] initialBoard(int size) {
		assert(size > 0);

		this.board = new boolean[size][size];
		this.position = new Point(size / 2, size / 2);

		assert(this.board.length == size);
		this.invariant();
		return this.board;
	}

	/**
	 * Create a new board and return it.
	 * @return board, must be of size SIZExSIZE.
	 */
	public boolean[][] initialBoard() {
		return this.initialBoard(SIZE);
	}

	/** 
	 * Move upwards.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveUp(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveUp();
		}
		this.invariant();
	}

	/**
	 * Move one step upwards.
	 *
	 * Same wrap-around logic as with moveUpwards(int) applies.
	 */
	public void moveUp() {
		if (this.position.y == 0) {
			this.position.y = this.board.length - 1;
		} else {
			this.position.y -= 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
		this.invariant();
	}


	/** 
	 * Move downwards.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveDown(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveDown();
		}
		this.invariant();
	}

	/**
	 * Move one step downwards.
	 *
	 * Same wrap-around logic as with moveDown(int) applies.
	 */
	public void moveDown() {
		if (this.position.y == this.board.length - 1) {
			this.position.y = 0;
		} else {
			this.position.y += 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
		this.invariant();
	}


	/** 
	 * Move towards the left.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveLeft(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveLeft();
		}
		this.invariant();
	}

	/**
	 * Move one step to the left.
	 *
	 * Same wrap-around logic as with moveLeft(int) applies.
	 */
	public void moveLeft() {
		if (this.position.x == 0) {
			this.position.x = this.board.length -1;
		} else {
			this.position.x -= 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
		this.invariant();
	}


	/** 
	 * Move towards the right.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveRight(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveRight();
		}
		this.invariant();
	}

	/**
	 * Move one step to the right.
	 *
	 * Same wrap-around logic as with moveRight(int) applies.
	 */
	public void moveRight() {
		if (this.position.x == this.board.length - 1) {
			this.position.x = 0;
		} else {
			this.position.x += 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
		this.invariant();
	}


	// @TODO: Lots of repetitive code here.
	/** 
	 * Move towards the top right.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveUpRight(int count) {
		assert(count > 0);
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveRight();
			this.moveUp();
			this.touchField();
		}
		this.autoTouch = true;
		this.invariant();
	}

	/** 
	 * Move towards the top left.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveUpLeft(int count) {
		assert(count > 0);
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveLeft();
			this.moveUp();
			this.touchField();
		}
		this.autoTouch = true;
		this.invariant();
	}

	/** 
	 * Move towards the bottom right.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveDownRight(int count) {
		assert(count > 0);
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveRight();
			this.moveDown();
			this.touchField();
		}
		this.autoTouch = true;
		this.invariant();
	}

	/** 
	 * Move towards the bottom left.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveDownLeft(int count) {
		assert(count > 0);
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveLeft();
			this.moveDown();
			this.touchField();
		}
		this.autoTouch = true;
		this.invariant();
	}

	/**
	 * Jump to position on the field.
	 *
	 * @param x X coordinate. Must be >= 0 and < board.size.
	 * @param y Y coordinate. Must be >= 0 and < board.size.
	 */
	public void jump(int x, int y) {
		assert(x >= 0);
		assert(y >= 0);
		assert(x < this.board.length);
		assert(y < this.board.length);

		this.position.x = x;
		this.position.y = y;
		this.invariant();
	}


	private void touchField() {
		this.board[this.position.x][this.position.y] = true;
	}
}
