package quoridor;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Parser
{
	/** 
	 * Read and parse a game specification from disk.
	 *
	 * @param path Path to file on filesystem wheree game specification resides.
	 *
	 * @throws ParserException if supplied game specification is invalid.
	 * @throws IOException if reading from given path fails for any reason.
	 *
	 * @return Game object corresponding to specification in file. 
	 */
	public static IGame parseFromFile(String path, IGame game) throws ParserException, IOException {
		String input = new String(Files.readAllBytes(Paths.get(path)));
		try {
			ParserV1 parser = new ParserV1(game);
			return parser.parse(input);
		} catch (ParserException e) {
			ParserV2 parser = new ParserV2(game);
			return parser.parse(input);
		}
	}

	/** 
	 * Read and parse a game specification from disk.
	 *
	 * @param path Path to file on filesystem wheree game specification resides.
	 *
	 * @throws ParserException if supplied game specification is invalid.
	 * @throws IOException if reading from given path fails for any reason.
	 *
	 * @return Game object corresponding to specification in file. 
	 */
	public static IGame parseFromFile(String path) throws ParserException, IOException {
		return parseFromFile(path, new Game());
	}
}
