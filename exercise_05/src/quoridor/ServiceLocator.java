package quoridor;

public abstract class ServiceLocator {
	private static ServiceLocator instance;
	protected ServiceLocator() {}

	public static ServiceLocator instance() {
		if (instance == null) {
			instance = defaultServiceLocator();
		}
	return instance;
	}

	public static ServiceLocator defaultServiceLocator() {
		return new DefaultServiceLocator();
	}

	public static void setServiceLocator (ServiceLocator serviceLocator) {
		instance = serviceLocator;
	}

	public abstract IGame getGame();

	public abstract ICommandParser getCommandParser(IGame game);
}
