package quoridor;

public interface ICommandParser {

	/**
	 * Parse a command.
	 *
	 * @param input User-supplied command.
	 * @return ICommand Command class representing user-supplied command.
	 * @throws ParserException if user-supplied command is an unknown, or
	 * syntactically incorrect, command, e.g. if a wall command is missing a parameter.
	 * @throws CommandInvalidException if user-supplied command is
	 * semantically invalid, e.g. if a movement command tries to move to an
	 * invalid position.
	 */
	ICommand parse(String input) throws ParserException, CommandInvalidException;

}