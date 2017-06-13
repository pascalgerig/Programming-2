package quoridor;

import java.util.Scanner;

/**
 * IUserInteraction only for testing
 * methods don't create Output
 * @author Pascal Gerig
 *
 */
public class TestUserInteraction implements IUserInteraction {
	private IGame game;
	private ICommandParser parser;

	/**
	 * Create new instance of this class.
	 *
	 * @param game Game which the user interaction is for.
	 */
	public TestUserInteraction(IGame game) {
		this.game = game;
		this.parser = new CommandParser(this.game);
	}

	public void setCommandParser(ICommandParser parser) {
		this.parser = parser;
	}

	@Override
	public ICommand askNextCommand() {
		return getCommand();
	}

	private ICommand getCommand() {

		while (true) {
			int i = (int)(Math.random() * 4);
			String input = "";
			switch(i) {
				case 1: input = "u";
					break;
				case 2: input = "d";
					break;
				case 3: input = "l";
					break;
				case 0: input = "r";
					break;
			}

			try {
				return parser.parse(input);
			} catch (ParserException | CommandInvalidException e) {
			}
		}
	}
}
