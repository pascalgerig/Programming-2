package quoridor;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Class to handle basic pathfinding in a matrix containing fields (passable),
 * and walls (non-passable).
 *
 * Pathfinding is implemented as a slimmed-down version of Dijkstra's
 * algorithm, which simply determines whether there is a path, without worrying
 * about the path itself.
 */
public class PathFinding
{
	private int[][] board;
	private Point from;
	private Point to;

	private PathNode[][] nodes;

	/**
	 * Create new instance of this class.
	 *
	 * @param board Two-dimensional array of integers. `0` denotes a field
	 * which can be walked on, whereas `1` denotes a field which has a wall
	 * built atop.
	 * @param from One-based coordinate of starting point.
	 * @param to One-based coordinate of destination.
	 */
	public PathFinding(int[][] board, Point from, Point to) {
		this.board = board;
		this.from = new Point(from.x - 1, from.y - 1);
		this.to = new Point(to.x - 1, to.y - 1);

		initializeNodes();
		// printState();
	}

	private void initializeNodes() {
		this.nodes = new PathNode[board.length][board[0].length];

		for (int x = 0; x < this.board.length; x++) {
			for (int y = 0; y < this.board[0].length; y++) {
				if (this.board[x][y] == 0) {
					// Free space.
					this.nodes[x][y] = new PathNode(new Point(x, y), false);
				} else {
					// Wall.
					this.nodes[x][y] = new PathNode(new Point(x, y), true);
				}
			}
		}

		this.nodes[from.x][from.y].setDistance(0);
	}

	private PathNode destination() {
		return this.nodes[this.to.x][this.to.y];
	}

	private PathNode source() {
		return this.nodes[this.from.x][this.from.y];
	}

	/**
	 * Determine whether path from source to destination exists.
	 *
	 * @return True if a path exists, false otherwise.
	 */
	public boolean existsPath() {
		PathNode next = closestUnvisitedNode();
		while (next != null && next.distance() < Integer.MAX_VALUE) {
			List<PathNode> neighbours = neighbours(next);
			for (PathNode neighbour : neighbours) {
				int tmpDistance = next.distance() + 1;
				if (tmpDistance < neighbour.distance()) {
					neighbour.setDistance(tmpDistance);
				}
			}
			next.visit();

			// System.out.println("--------");
			// printState();

			next = closestUnvisitedNode();
		}

		if (this.destination().distance() < Integer.MAX_VALUE) {
			// System.out.println(String.format("Found path to destination with distance: %s", this.destination().distance()));
			return true;
		} else {
			// System.out.println("No path to destination found.");
			return false;
		}
	}

	private List<PathNode> neighbours(PathNode node) {
		Point pos = node.position();
		List<PathNode> out = new ArrayList<PathNode>();

		if (pos.x > 0) {
			out.add(this.nodes[pos.x - 1][pos.y]);
		}

		if (pos.x < this.nodes.length - 1) {
			out.add(this.nodes[pos.x + 1][pos.y]);
		}

		if (pos.y > 0) {
			out.add(this.nodes[pos.x][pos.y - 1]);
		}

		if (pos.y < this.nodes[0].length - 1) {
			out.add(this.nodes[pos.x][pos.y + 1]);
		}

		/* Nodes which are walls are not valid neighbours. */
		for (Iterator<PathNode> iterator = out.iterator(); iterator.hasNext(); ) {
			PathNode neighbour = iterator.next();
			if (neighbour.hasWall()) {
				iterator.remove();
			}
		}

		return out;
	}

	private PathNode closestUnvisitedNode() {
		PathNode out = null;

		for (PathNode[] col : this.nodes) {
			for (PathNode node : col) {
				if (!node.visited()) {
					if (out == null || node.distance() < out.distance()) {
						out = node;
					}
				}
			}
		}

		// System.out.printf("Next node: %s\n", out.toString());
		return out;
	}

	private void printState() {
		for (PathNode[] col : this.nodes) {
			for (PathNode node : col) {
				System.out.print(node.toString() + "\t");
			}
			System.out.print("\n");
		}
	}
}
