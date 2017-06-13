package snakes;

/**
 * Exception indicating that the game is not over.
 *
 * Used for situations where the game needs to be over, e.g., for
 * querying the player.
 *
 * An example where this is used is {@link Game#winner()}
 */
public class GameNotOverException extends Exception {
}
