package snakes;

/**
 * A die for rolling random numbers
 */
public class Die implements IDie {
	protected final int faces;

	/**
	 * Initialize a new die with given faces
	 * @param faces number of sides, must be > 0
	 */
	public Die(int faces) {
		assert faces > 0;
		this.faces = faces;
	}

	/* (non-Javadoc)
	 * @see snakes.IDie#roll()
	 */
	@Override
	public int roll() {
		int result = 1 + (int) (faces * Math.random());
		assert result >= 1 && result <= faces;
		return result;
	}
}
