package quoridor;

public class TestServiceLocator extends ServiceLocator {
	@Override
	public IGame getGame() {
		return new TestGame();
	}
	
	@Override
	public ICommandParser getCommandParser(IGame game){
		return new CommandParser(game);
	}
}