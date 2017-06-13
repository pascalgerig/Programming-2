package snakes;

/**
 * A mocked die for rolling not-so-random numbers
 */
public class MockDie implements IDie {
	@Override
	public int roll() {
		return 2;
	}
}
