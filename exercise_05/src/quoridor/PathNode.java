package quoridor;

import java.awt.Point;

/**
 * Class which represents a single node within a path-finding graph.
 */
public class PathNode
{
	private Point position;
	private boolean visited = false;
	private int distance = Integer.MAX_VALUE;
	private boolean hasWall;

	/**
	 * Create new instance of this class.
	 *
	 * @param position 1-based coordinates of this node.
	 * @param hasWall True if there is a wall on this node, false if not.
	 */
	public PathNode(Point position, boolean hasWall) {
		this.position = position;
		this.hasWall = hasWall;
	}

	/**
	 * @return Whether this node was visited.
	 */
	public boolean visited() {
		return this.visited;
	}

	/**
	 * Set this node to visited.
	 */
	public void visit() {
		this.visited = true;
	}

	/**
	 * @return Whether a wall is built on this node.
	 */
	public boolean hasWall() {
		return this.hasWall;
	}

	/**
	 * @return Distance of this node from source.
	 */
	public int distance() {
		return this.distance;
	}

	/**
	 * Set distance of this node from source.
	 */
	public void setDistance(int d) {
		this.distance = d;
	}

	/**
	 * @return 1-based coordinates of this node.
	 */
	public Point position() {
		return this.position;
	}

	/**
	 * @return String-based representation of this node.
	 */
	public String toString() {
		char vis = this.visited ? 'x' : 'o';
		char wl =  this.hasWall ? '#' : ' ';
		return String.format("(%2s,%2s), d: %10s, v: %s (%s)", this.position.x, this.position.y, this.distance, vis, wl);
	}
}
