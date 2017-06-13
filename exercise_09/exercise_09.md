# Exercise 9

This week, you will work with an implementation of the Quoridor game. The 
implementation contains some object dependencies. Your goal is to use two 
design patterns that help you to avoid them.

## Dependencies

Creating an object introduces a direct dependency to the class of the object 
that is created. If you want to create an instance of another type (e.g., a 
`NullLogger` instead of a `TextOutputLogger`), you need to change the code 
where the object is actually created. This violates the [*open-closed* 
principle](https://en.wikipedia.org/wiki/Open/closed_principle). There are 
different approaches that can be used to resolve this issue. Two popular 
techniques are called [dependency 
injection](https://en.wikipedia.org/wiki/Dependency_injection) and [service 
locator pattern](https://en.wikipedia.org/wiki/Service_locator_pattern).

Consider the following example. In a `Game` constructor, objects are directly 
instantiated:
```java
public Game() {
	this.board = new Board();
	this.scanner = new ConsoleScanner();
	this.logger = new ConsoleWriter();
}
```
One problem here is the following. If we want to use a `Game` for testing, we 
don't want to use `ConsoleScanner` and `ConsoleWriter`. Instead, we have 
written classes like `ScriptedScanner` and `NullWriter` (for the particular 
test we are writing, we are not interested in the output of the game and 
decided to use the [null object 
pattern](https://en.wikipedia.org/wiki/Null_Object_pattern) to make sure we 
have silent tests). With the above code, this would mean that we need to 
change the constructor. But if we do that, the other code that creates `Game` 
objects doesn't work as intended anymore. One possible (ugly) solution would 
be to add another constructor (but we still keep the one from above!), like 
this:
```java
public Game(boolean isTestGame) {
	this(); // call the previous constructor
	if (isTestGame) {
		this.scanner = new ScriptedScanner();
		this.logger = new NullWriter();
	}
}
```
But of course, this gets complicated quickly. If the number of created objects 
grows, you need to consider a lot of different cases, and you might end up 
creating objects that are never used, and so on.

A better solution is to defer the responsibility of selecting of the actual 
types to another class. To do this, we can use a so-called *service locator* 
as follows:
```java
public Game() {
	this.board = ServiceLocator.instance().getBoard();
	this.scanner = ServiceLocator.instance().getScanner();
	this.logger = ServiceLocator.instance().getLogger();
}
```
The `ServiceLocator` class is a simple class that provides the required 
objects:
```java
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

	public abstract Logger getLogger();

	public abstract Scanner getScanner();

	//...
}
```
Notice that the method signatures only indicate the supertype of the returned 
objects (e.g., the method `getScanner` returns an object of type `Scanner`, 
not a concrete type like `ConsoleScanner`).

(NB: This class implements the [singleton
pattern](https://en.wikipedia.org/wiki/Singleton_pattern), by calling 
`ServiceLocator.instance()` we will always get the same instance.)

Since `ServiceLocator` is abstract, we need to provide implementations, which 
are responsible for the actual object creation. The idea is to implement 
subclasses that are responsible for object creation and can be used where 
needed. In our example, we could implement a `DefaultServiceLocator` and a 
`TestServiceLocator` class as follows:
```java
public class DefaultServiceLocator extends ServiceLocator {
	@Override
	public Scanner getScanner() {
		return new ConsoleScanner();
	}
	//...
}

public class TestServiceLocator extends ServiceLocator {
	@Override
	public Scanner getScanner() {
		return new ScriptedScanner();
	}

	//...
}
```
Now, we don't need to have special constructors in the `Game` class anymore to 
make the code testable. Instead, we simply switch the service locator and 
`Game` will use the scanner and logger that we want for testing:
```java
@Test
public void someTest() {
	TestServiceLocator locator = new TestServiceLocator();
	locator.setScriptedInput(UP, DOWN, RIGHT, RIGHT, ...);
	ServiceLocator.setLocator(locator);

	/* We don't need to change how the game is created, as it uses
	   the service locator for creating the instances. :-) */
	Game game = new Game();

	// ...

}
```

### Dependency Injection

When using service locators, your constructors do not have parameters anymore. 
This may not be desirable, as it can be hard to tell on what interfaces and 
classes the constructor actually depends. An alternative to this is passing 
the objects as parameters. This way, you can have generic types as parameters, 
making the constructor independent and unaware of the concrete types. This is 
called *dependency injection*: instead of creating the objects inside our 
constructor (or any other method for that matter), we give the responsibility 
of creating or obtaining the correct objects to the client and let it pass the 
instances from the outside. The following is an example of a `Game` 
constructor that uses dependency injection:
```java
public Game(Board board, Scanner scanner, Logger logger) {
	this.board = board;
	this.scanner = scanner;
	this.logger = logger
}
```

## Tasks

For this exercise, you **must** solve the following tasks:

- In your Quoridor game, find at least three classes with at least one 
  constructor that has dependencies on other classes as shown above (i.e. 
  constructors that create certain objects directly). If you do not already 
  have a suitable interface, create one for the type of the object you create 
  (so for example, if you create a `new Game()` in your constructor, create an 
  interface `IGame`). Use a service locator to obtain at least one of these 
  created objects in order to remove the dependency to that particular class). 
  The service locator must use the newly created interface (or existing ones; 
  abstract classes are also ok). For your abstract service locator, write at 
  least one concrete classes like shown above (that is, one `ServiceLocator` 
  (you can name it differently if you want) that implements the singleton 
  pattern, and one class that inherits from `ServiceLocator` which provides 
  the correct object). Use the service locator in your constructors. Create a 
  markdown file `descriptions.md` (in the same folder where this file is!), 
  where you describe your choices and changes to your code. Finally commit 
  your work and tag it with `9.1-service_locator`.

- Use the same constructors you selected before, but instead of using a 
  service locator, inject the dependencies. You are free to choose how to 
  inject them: you can do this through a constructor, but could also use 
  setters. Try to choose something that makes sense in your situation. Again, 
  explain your choices and describe what you changed (you should also describe 
  how it affected the remainder of your system, e.g., did you need to change a 
  lot of other classes to make it work again?). Tag your solution with 
  `9.2-dependency_injection`.

- **Make all your tests silent**. If you have tests that write to the standard 
  output, change your code to use service locators or dependency injection to 
  provide silent objects. Once you did this, tag your solution with 
  `9.3-silent_tests`. For this tag, it doesn't matter whether you build on top 
  of `9.1` or `9.2` or even use a mix of the two, as long as none of your 
  tests print to standard output. Note that outside of the game, the game 
  should still be printed (and thus be playable)! You may want to add some 
  classes that are specifically used in unit tests... (But of course you 
  should use the *real* class for the unit under test.)

- Describe in `descriptions.md` your choice for the final tag (why did you 
  decide for one or the other or even none of the above).

- Make sure that everything is pushed to the remote repository. We are going 
  to check out the `9.1-service_locator`, `9.2-depenency_injection`, and 
  `9.3-silent_tests` tags. Your `descriptions.md` should contain all parts of 
  the text at the end (that its, when checking `9.3-silent_tests`).


## Hints

- You can change your game however you like for your task, but keep in mind 
  that you should describe how you get from your old code to the new one in 
  `descriptions.md`, as it may be hard to tell how the code evolved. 
  Furthermore, you should make sure that your tests pass all the time, but 
  especially for the `ex9.1` and `ex9.2` tags.


## Deadline

Submit your solutions by pushing your code to the git repository by 
___Thursday, 11 May, 13:00___.
