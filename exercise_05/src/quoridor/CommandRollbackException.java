package quoridor;

public class CommandRollbackException extends Exception
{
	public CommandRollbackException(String message) {
		super(message);
	}
}
