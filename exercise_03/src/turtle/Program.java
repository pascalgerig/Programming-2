package turtle;

import java.util.List;

/**
 * @author Michael Senn
 * @author Pascal Gerig
 *
 *	Representation of a collection of commands entered by the user. The object then parses the input and - if possible - executes the command
 */
public class Program
{
	private String program;
	private List<ICommand> commandList;

	public Program(String program) throws ParserException {
		this.program = program;
		this.commandList = Parser.parse(this.program);
	}

	public void execute(BoardMaker board) {
		for (ICommand c : this.commandList) {
			c.execute(board);
		}
	}
}
