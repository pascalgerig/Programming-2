package quoridor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Point;

import org.junit.Test;

public class TileTest
{
	private Tile tile = new Tile(new Point(1, 1));

	@Test
	public void equalsChecksForIdenticalCoordinates() {
		assertNotEquals(tile, new Tile(new Point(2, 1)));
		assertNotEquals(tile, new Tile(new Point(1, 2)));
	}

	@Test
	public void equalsReturnsTrueForIdenticalTiles() {
		assertEquals(tile, new Tile(new Point(1, 1)));
	}
}
