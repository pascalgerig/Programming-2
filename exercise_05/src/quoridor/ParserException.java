package quoridor;

/**
 * Exception raised if syntactic error encountered while parsing game
 * definition or command.
 */
public class ParserException extends Exception
{
	/**
	 * Create new instance of this class.
	 *
	 * @param message Exception message.
	 */
	public ParserException(String message) {
		super(message);
	}
}
