package snakes;

import org.junit.Test;
import org.junit.Before;
import snakes.Die;

import static org.junit.Assert.*;
public class DieTest
{
	@Test(expected=AssertionError.class)
	public void facesLowerThanOneThrowsException() {
		Die die = new Die(0);
	}

	@Test
	public void rollReturnsNumbersBetweenOneAndFaces() {
		Die die = new Die(6);
		for (int i = 0; i < 100; i ++) {
			int roll = die.roll();
			assertTrue(roll <= die.faces);
			assertTrue(roll >= 1);
		}
	}
}
