package quoridor;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.awt.Point;

/**
 * Parses a Quoridor file specification v2 and creates a {@link Game} instance.
 *
 * Internally, the parser will construct a game object piece-by-piece, but its
 * public API will take care of either returning a full game object, or
 * throwing an exception.
 */
public class ParserV2 extends Parser
{
	private static Pattern BOARD_DIMENSION_AND_WALL_COUNT_PATTERN = Pattern.compile("^(\\d+) (\\d+) (\\d+)( \\d+)+$");
	private static Pattern BOARD_ROW_PATTERN = Pattern.compile("^[#a-zA-Z ]+$");
	private static Pattern PLAYER_ENTRY_PATTERN = Pattern.compile("^(.) (.+)$");

	private IGame game;
	private List<Integer> playerWallAllowances;
	private HashMap<Character, Point> playerStartingPositions = new HashMap<Character, Point>();
	private HashMap<Character, List<Point>> playerTargetPositions = new HashMap<Character, List<Point>>();
	private int width;
	private int height;

	public ParserV2() {
		this.game = new Game();
	}

	public ParserV2(IGame game) {
		this.game = game;
	}

	public IGame parse(String input) throws ParserException {
		Scanner scanner = new Scanner(input);

		if (!scanner.hasNextLine()) {
			throw new ParserException(String.format("Input empty", input));
		}

		this.parseBoardDimensionAndWallCount(scanner);
		this.parseBoard(scanner);


		// for (Map.Entry<Character, Point> entry : this.playerStartingPositions.entrySet()) {
		// 	System.out.println(String.format("%s starts at %s / %s", entry.getKey(), entry.getValue().x, entry.getValue().y));
		// }

		// for (Map.Entry<Character, List<Point>> entry : this.playerTargetPositions.entrySet()) {
		// 	System.out.println(String.format("%s target positions:", entry.getKey()));
		// 	for (Point p : entry.getValue()) {
		// 		System.out.println(String.format("- %s / %s", p.x, p.y));
		// 	}

		// }

		this.parsePlayers(scanner);
		scanner.close();

		return this.game;
	}

	private void parseBoardDimensionAndWallCount(Scanner scanner) throws ParserException {
		String input = scanner.nextLine();
		Matcher matcher = BOARD_DIMENSION_AND_WALL_COUNT_PATTERN.matcher(input);
		if (matcher.matches()) {
			this.width = Integer.parseInt(matcher.group(1));
			this.height = Integer.parseInt(matcher.group(2));
			this.game.setDimension(this.width, this.height);

			/* First two entries are width and height, which we'll discard. */
			String[] wallAllowances = input.split(" ");
			this.playerWallAllowances = new ArrayList<Integer>(wallAllowances.length - 2);
			for (int i = 2; i < wallAllowances.length; i++) {
				this.playerWallAllowances.add(Integer.parseInt(wallAllowances[i]));
			}
		} else {
			throw new ParserException(String.format("Invalid board dimensions or player wall counts: %s", input));
		}
	}

	private void parseBoard(Scanner scanner) throws ParserException {
		for (int y = 1; y <= this.height; y++) {
			if (scanner.hasNextLine()) {
				String input = scanner.nextLine();
				Matcher matcher = BOARD_ROW_PATTERN.matcher(input);
				if (matcher.matches() && input.length() == this.width) {
					int x = 1;
					for (char c : input.toCharArray()) {
						if (c == '#') {
							try {
								this.game.buildWall(new Point(x, y), new Point(x, y));
							} catch (CommandRollbackException e) {
								throw new ParserException(String.format("Invalid wall placement at %s/%s", x, y));
							}
						} else if (c == ' ') {
							// No-op, empty tiles are initialized already.
						} else if (Character.isLowerCase(c)) {
							/* Lower case alphabetic char denotes a destination tile, store for later usage. */
							if (this.playerTargetPositions.get(Character.toUpperCase(c)) == null) {
								this.playerTargetPositions.put(Character.toUpperCase(c), new ArrayList<Point>());
							}
							this.playerTargetPositions.get(Character.toUpperCase(c)).add(new Point(x, y));
						} else if (Character.isUpperCase(c)) {
							/* Upper case alphabetic denotes a starting position, store for later usage. */
							this.playerStartingPositions.put(c, new Point(x, y));
						}
						x++;
					}
				} else {
					throw new ParserException(String.format("Invalid board row: %s", input));
				}
			} else {
				throw new ParserException(String.format("Not enough rows in board defintion. Expected: %s, Got: %s", this.height, y - 1));
			}
		}
	}

	private void parsePlayers(Scanner scanner) throws ParserException {
		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();
			this.game.addPlayer(parsePlayer(input));
		}
	}

	private Player parsePlayer(String input) throws ParserException {
		Matcher matcher = PLAYER_ENTRY_PATTERN.matcher(input);
		if (matcher.matches()) {
			char playerSign = matcher.group(1).charAt(0);
			String playerName = matcher.group(2);
			Point startingPosition = this.playerStartingPositions.get(playerSign);
			Tile startingTile = this.game.getTile(startingPosition.x, startingPosition.y);
			Player p = new Player(
					playerName,
					playerSign,
					startingTile,
					null
			);

			for (Point point : this.playerTargetPositions.get(playerSign)) {
				p.addTargetTile(this.game.getTile(point.x, point.y));
			}

			if (this.playerWallAllowances.size() < 1) {
				throw new ParserException("Insufficient wall allowances defined.");
			}
			/* We have no way of matching wall allowances with players except by implicit order... */
			p.setAvailableWalls(this.playerWallAllowances.remove(0));
			return p;
		} else {
			throw new ParserException(String.format("Invalid player entry: %s", input));
		}
	}
}
