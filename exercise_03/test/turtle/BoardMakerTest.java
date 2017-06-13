package turtle;

import java.awt.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import turtle.BoardMaker;

public class BoardMakerTest
{
	private BoardMaker board;

	@Before
	public void prepare() {
		this.board = new BoardMaker();
		this.board.initialBoard(5);
	}

	@Test
	public void initialBoardSetsPositionToCenter() {
		this.board.initialBoard(3);
		assertEquals(new Point(1, 1), this.board.getPosition());

		this.board.initialBoard(10);
		assertEquals(new Point(5, 5), this.board.getPosition());
	}

	@Test
	public void moveUpMovesUp() {
		this.board.moveUp(1);
		assertEquals(new Point(2, 1), this.board.getPosition());
	}

	@Test
	public void moveUpWrapsAround() {
		this.board.moveUp(7);
		assertEquals(new Point(2, 0), this.board.getPosition());
	}

	@Test
	public void moveDownMovesDown() {
		this.board.moveDown(1);
		assertEquals(new Point(2, 3), this.board.getPosition());
	}

	@Test
	public void moveDownWrapsAround() {
		this.board.moveDown(4);
		assertEquals(new Point(2, 1), this.board.getPosition());
	}

	@Test
	public void moveLeftMovesLeft() {
		this.board.moveLeft(1);
		assertEquals(new Point(1, 2), this.board.getPosition());
	}

	@Test
	public void moveLeftWrapsAround() {
		this.board.moveLeft(3);
		assertEquals(new Point(4, 2), this.board.getPosition());
	}

	@Test
	public void moveRightMovesRight() {
		this.board.moveRight(1);
		assertEquals(new Point(3, 2), this.board.getPosition());
	}

	@Test
	public void moveRightWrapsAround() {
		this.board.moveRight(9);
		assertEquals(new Point(1, 2), this.board.getPosition());
	}

	@Test
	public void jumpSetsPosition() {
		this.board.jump(1, 4);
		assertEquals(new Point(1, 4), this.board.getPosition());
	}
}
