package quoridor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Point;

import org.junit.Test;

public class WallCommandTest
{
	@Test
	public void equalsChecksForEqualFrom() {
		assertEquals(
				new WallCommand(new Point(1, 1), new Point(2, 2)),
				new WallCommand(new Point(1, 1), new Point(2, 2))
		);

		assertNotEquals(
				new WallCommand(new Point(1, 1), new Point(2, 2)),
				new WallCommand(new Point(1, 2), new Point(2, 2))
		);
	}

	@Test
	public void equalsChecksForIdenticalTo() {
		assertNotEquals(
				new WallCommand(new Point(1, 1), new Point(2, 2)),
				new WallCommand(new Point(1, 1), new Point(2, 3))
		);
	}
}
