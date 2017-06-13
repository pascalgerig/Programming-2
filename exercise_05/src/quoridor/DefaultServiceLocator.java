package quoridor;

public class DefaultServiceLocator extends ServiceLocator {
	@Override
	public IGame getGame() {
		return new Game();
	}
	
	@Override
	public ICommandParser getCommandParser(IGame game){
		return new CommandParser(game);
	}
}
