package quoridor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RendererTest
{
	private IGame game;
	private Renderer renderer;

	@Before
	public void init() {
		game = new Game();
		game.setDimension(5, 3);
		game.addPlayer(new Player("Michael", 'M', game.getTile(2, 1), Player.Target.RIGHT));
		game.addPlayer(new Player("Pascal", 'P', game.getTile(5, 3), Player.Target.LEFT));

		game.players().get(0).enterGame(game);
		game.players().get(1).enterGame(game);

		renderer = new Renderer(game);
	}

	@Test
	public void renderReturnsTextRenderingOfGameObjectValid() {
		String should = "*-----*\n|pM  m|\n|p   m|\n|p   P|\n*-----*\n";
		assertEquals(should, renderer.render());
	}
}
