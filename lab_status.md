# P2 FS17 lab status -  group02 - testat completed #

## Lab 01 ##
**Grade:** ok <br>
**Feedback**: Very nice and clean conversion of glob to regex, simple and elegant solution. Good job! Commit messages and size/contents of commits are also okay.

## Lab 02 ##
**Grade:**  ok<br>
**Feedback (initial)**: Your implementation is again very nice and clean, and you made good use of the existing architecture, good job! I just added one little comment about the naming convention for constants (i.e. final instance variables, they SHOULD_BE_ALL_CAPS). Your RollAgainSquareTest is rather minimal (this is just a remark you don't have to extend the test). E.g. you could have tested what happens when Jill enters that field after Jack. I like the fact that you commented the test though, it helps to convey its purpose.<br>

The problem are your class comments. First of all, they need to be of the form \/** ... \*/, rather than /* ... \*/, otherwise they are just comments (can only be seen right where they are written), instead of API documentation (can be seen when hovering over methods/classes, can be exported to HTML pages, etc). Further, you should be more specific. What do you mean by "player will be moved forward a certain amount of squares"? Who defines this "certain amount"? The square, the game, ...? And all child classes of Square are basically special squares, that’s sort of implied by the inheritance hierarchy. Also, don't talk about what methods a class provides and overrides, but what functionality it provides. E.g. a square defines its behaviour when entered or left by players, etc. Then, you don't always need to write "Provides following functionality:", because, except for annotations (@author, @version, …), this is what a class comment mainly talks about.<br>

About your method comments: the comments about params and return values are fine, plus, you did write real Javadoc comments there (with two starts in the beginning). However, most of them aren’t just accessor methods, so you need to comment on how they modify the receiver’s (i.e. the square’s) state/behaviour, not just @params and @return. For example: moveAndLand() defines the square’s entire behaviour when being left by a player. Also, landHereOrGoHome() isn’t just there to be overridden, it defines a specific behaviour as well. Lastly, I wouldn’t say enter and leave are callback methods… <br>

Please read the [doc comment guidelines](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html) again, improve your comments (on all of the square classes and the ISquare interface), and make sure they actually are Javadoc comments. Don’t spend too much time on this, but since it was an important part of this exercise, I need you to do some improvements. This shouldn’t take too long. Also, according to the exercise description, you need to add all the new fields to the Game.java at least once. You seem to have forgotten the RollAgainSquare, so please add that one the the game as well.<br>

**Feedback (revision)**: Very good, your class comments are better now. However, I still noticed some of the are only introduced with a single star, but you probably just missed them. What I forgot to tell you before: you shouldn't have any statements (like package, imports, etc.) between a class comment and the class definition. Eclipse will show doc comments when hovering over a class or method identifier, but it can only match the comment to a class/method if there are no statements in between.


## Lab 03 ##
**Grade:**  ok<br>
**Feedback**: Very good solution! You separated all the concerns in an elegant way, you mostly have short and concise methods, and your contracts are good as well. I also like that you clearly state what happens, if the turtle runs out of the field.  I commented on some of your contracts, because I think you forgot to cover some cases. For example, in MoveCommand, you never state what happens if an argument is null. This is important though, because it’s your decision whether you want to accept it (which means you’ll have to reasonably handle null values and also state how you will handle them) or you don’t accept it (assert(arg != null)) and then you do not define your behaviour any further, if received a null value.<br>

One remark about invariants: you should always check them before AND after public method calls. Usually, you have a method <code>private boolean invariant(){...}</code> and then you can check <code>assert(invariant);</code> as first and last line in all public methods.

Your UMLs are good too, they are both mostly accurate and exhaustive. You should maybe be more careful with composition vs. aggregation. For example, I think for program and ICommand, the composition makes sense, but between Program and Parser it's more of an aggregation (also, the diamond is most likely at the wrong end of the line there - a program doesn't have a parser, a parser has a program, right?). It's a fine line, but maybe you should have a look at this again. You don't need to revise anything though.


## Lab 04 ##
**Grade:** ok<br>
**Feedback**: Well done, you tested all the relevant cases and achieved great coverage. At some point in GameTest you mentioned that you didn’t mock the Player, since you had to implement a lot of business logic. That’s definitely true. Personally, I don’t think frameworks like Mockito help you much there. However, if you really need a mock version of the player, you could create a custom mock like you did with MockDie. Regarding your comment on custom mock objects: the God class code smell is a good observation. Still, if you have such God classes, you can’t always avoid using them in tests, so you might have to mock them anyway. I would say having to use custom mocks might be a sign to check for God classes, and consider a refactoring if necessary, but it should not affect your testing behaviour.<br>

One thing I missed in your statement were the advantages of mock frameworks. They seem to be more limited than whatever you can do by creating a custom mock, so why do people use them anyway?

## Labs 05 and 06 ##
**Grade:** ok<br>
**Feedback**: Your game fulfils all the requirements of exercises 5 and 6, well done! Very nice input error feedback (invalid wall command, leaving field at the left, etc.) this is cool! I noticed though that apparently, every invalid command says “Invalid wall command”, which can be confusing. I like that you provide the valid commands in the input prompt, but I think you may the wall command specification should be something like <code>Wall \<x1> \<y1> \<x2> \<y2></code>. Here are some things that I noticed while reading your code:

* You are missing the v2 tag. Please make sure to set the required tags in the future.
* Good separation of concerns, with the MoveCommand. Consider extending the MoveCommand into a polymorphism, instead of using a switch case.
* Defining useful <code>equals()</code> and <code>toString()</code> methods is always a good idea, well done.
* You could sometimes use more, smaller methods, I have especially noticed this about parse- and validate methods (although for algorithms, long methods generally more acceptable).
* Avoid magic numbers (e.g. in Parser, you say something like <code>game.players().size() < 2</code>. If you ever want to change the minimum number of players, you will never find this again. * Use a constant instead, and define it in whichever class you think should decide such things.
* Maybe instead of <code>List<List<Tile>> tiles</code> you could have used a separate <code>Board</code> class. Regardless of how you represent the tiles within that, it might be more comfortable for other classes to work with it. E.g. it would make the player’s move methods look much more readable, which increases maintainability and reduces the chance of bugs. Further, the board might have an invariant that checks for valid and invalid board states.
* Overall, you did a very good job on RDD and DBC.

## Lab 07 ##
**Grade:** ok<br>
**Feedback**: Well done, the «don’t box in» function works. Your tests are good too. I especially like how well readable the path finding tests are. Good documentation. Make sure you don’t forget about null cases in your contracts. You don’t necessarily need to do null checks, but you should state the behaviour, in case a certain argument is null. Similarly, your PathFinding constructor doesn’t state what happens, if <code>from</code> or <code>to</code> are 0 (since your coordinates are 1-indexed, which someone might overlook) or generally out of bounds.

The new parser works as well. Again, your methods there are rather long. Just remember that shorter methods can make your code more readable, which makes debugging much easier. There are two more things I noticed about the parser. First, it’s not the best idea to call the classes <code>ParserV1</code> and <code>ParserV2</code>. If you don’t just know how the two versions are different, you can’t really know what either of them do. Second, you should never use exceptions to implement your logic. This refers to the <code>Parser</code> class, where you use the v1 parser first, and then use the v2 parser second, if the first one caused an exception. An idea would be to use a regex to describe each file format. If your file content matches one of the regexes, you choose the appropriate parser, if not, you throw a parser exception right away.

## Lab 08 ##
**Grade:** ok<br>
**Feedback**: Good sketches, prototype is okay. Note: A prototype is not just a series of sketches, but something you can play around with. Ideally, you should create a paper board, and cut-outs for all the changeable/movable parts like players, walls, etc. The contents of text/input fields can be set using a pencil and an eraser.

## Lab 09 ##
**Grade:** ok<br>
**Feedback**: Service locator works, well done. Careful: you may not always want your service locator to return a new instance of whatever object someone asks for. It’s often useful to just have one object (create by the SL itself, or set there by some other object) and always return that one, so everyone uses the same instance of that class.<br>

Dependency injection: also well done!<br>

Silent tests: works too, good job.<br>

The game still appears to be fully functional with either dependency resolution methods. All in all, good work.