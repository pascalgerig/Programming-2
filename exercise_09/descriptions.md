# Service-Locator:

## Changes:

Added Interfaces:	
- IGame
- ICommandParser
- IUserInteraction

Added Classes:
- TestGame
- TestUserInteraction
- DefaultServiceLocator
- TestServiceLocator
- ServiceLocator

The ServiceLocator Classes are just Copies of your idea on your SerciveLocator
Classes.  The Service Locator is used in ParserV1.class, ParserV2.class to
create a new IGame, and in UserInteraction.class to set up an ICommandParser.
TestGame is the IGame that can be used for testing. it's the same as Game.class
but doesn't create any output. Therefore i had to introduce a new class
TestUserInteraction to simulate UserInteraction. It doesn't need any Input
(which is as well nice for testing)

# Dependency injection:

## Injecting IUserInteraction implementation

The to-be-used class implementing the IUserInteraction interface can be
injected into the Game object via a setter method. If not set, it defaults to
the 'regular' UserInteraction, which actually interacts with the user.

This allowed most code to remain untouched - merely tests which want to stub
out user interaction have to replace the user interaction class with e.g
TestUserInteraction.

## Injecting IGame implementation

The to-be-used class implementing the game interface can be injected into the parser - whose job it is to generate a game based on a specification file - when calling the method to parse a file.

This provided an easy solution to leave the Parser class fully-static, with no
need for instantiation. For compatability reasons, a second method was added,
with the old signature, which substitues a regular Game instance. Again, this
allowed most code to stay untouched, while tests which want to disable e.g. the
rendering part, can subsitute their own implementation of IGame.

## Injecting ICommandParser implementation

The to-be-used class implementing the ICommandParser interface can be injected
into the UserInteraction object via a setter method. If not set, it defaults to
the regular command parser.

This allowed all existing code to be untouched. If a test now required a custom
parser, it could easily inject its own implementation when setting up the game.

# Silent tests

Built based on dependency injection no particular reason - tests were
sucessfully silenced with either method.
