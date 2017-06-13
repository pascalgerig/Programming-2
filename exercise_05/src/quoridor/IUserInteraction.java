package quoridor;

public interface IUserInteraction {

	/**
	 * Ask user for next command.
	 *
	 * This will continue to prompt the user, until he entered a
	 * syntactically and semantically correct command.
	 *
	 * @return ICommand user-supplieid command.
	 */
	ICommand askNextCommand();

	void setCommandParser(ICommandParser parser);

}
