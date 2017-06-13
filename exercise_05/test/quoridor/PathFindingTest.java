package quoridor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

public class PathFindingTest
{
	private Point from = new Point(1, 1);
	private Point to = new Point(5, 5);
	@Test
	public void existsPathReturnsTrueOnEmptyBoard() {
		int[][] board = new int[][]{
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 }
		};
		PathFinding path = new PathFinding(board, from, to);

		assertTrue(path.existsPath());
	}

	@Test
	public void existsPathReturnsTrueOnBoardWithPath() {
		int[][] board = new int[][]{
			{ 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 1, 0 },
			{ 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 0, 0 }
		};
		PathFinding path = new PathFinding(board, from, to);

		assertTrue(path.existsPath());
	}

	@Test
	public void existsPathReturnsTrueOnBoardWithLockedOffPart() {
		int[][] board = new int[][]{
			{ 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 0 },
			{ 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 1, 0 }
		};
		PathFinding path = new PathFinding(board, from, to);

		assertTrue(path.existsPath());
	}

	@Test
	public void existsPathReturnsFalseIfNoPathToTarget() {
		int[][] board = new int[][]{
			{ 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 1, 0 },
			{ 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 1, 0 }
		};
		PathFinding path = new PathFinding(board, from, to);

		assertFalse(path.existsPath());
	}

	@Test
	public void existsPathReturnsFalseIfPlayerBoxedIn() {
		int[][] board = new int[][]{
			{ 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 0 },
			{ 1, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 }
		};
		PathFinding path = new PathFinding(board, from, to);

		assertFalse(path.existsPath());
	}
}
