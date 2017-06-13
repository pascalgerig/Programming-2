# Exercise 2

In the second exercise, we are extending an existing project by applying 
object-oriented design principles. We learn how to reuse classes and 
interfaces and how to write proper tests for the added functionality. The 
reading materials cover some guidelines on how to write good code, comments, 
and documentation.

Due to your outstanding performance in programming, you've been hired by the 
prestigious ACME Inc, Programming division. At the moment ACME Inc. is into 
game development. Their latest game, *Snakes and Ladders*, has become a real 
success. Therefore, they are currently looking into adding new features.

In order to pass this exercise, you need to modify the current implementation 
of the Snakes and Ladders game (presented during the lecture) in the following 
ways:

1. Add the following new types of squares

  - `SpeedUpSquare` and `SpeedDownSquare`: When landing on the first square, 
    double the amount of moves in the next turn (for example, when rolling 3, 
    move by 6). Similarly, move only half the amount of the roll when landing 
    on a `SpeedDownSquare` square (but move by at least by 1).

  - `RollAgainSquare`: Immediately roll and move a second time.

- `WormholeEntranceSquare` and `WormholeExitSquare`: When entering a wormhole 
  (upon landing on a `WormholeEntranceSquare`), the player is transferred to a 
  random `WormholeExitSquare`.

2. Add/extend tests to the classes `SpeedUpSquareTest` and 
   `RollAgainSquareTest`. Your tests should make use of the corresponding 
   squares. Some (partial and minimal) tests are already implemented; check 
   the corresponding classes.

3. Make sure that your implementation of the `WormholeEntranceSquare` and 
   `WormholeExitSquare` classes pass the tests in the `WormholeTest` class.

4. Include the new squares in the `Game.main()` method, such that each kind of 
   square is included at least once in the board. Note that for the wormholes, 
   you should have at least two exits.

5. Add API documentation to the `snakes.ISquare` interface according to the 
   rules provided by Oracle (see 
   [http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)).

6. Add class comments to all square classes, describing the responsibilities 
   and functionality of each class.


## Hints

You are allowed to change all the existing classes and add new ones. Before 
starting to code however, we advise you to think about which class has which 
responsibility, and which class needs to know (and be able to manipulate) 
which data.


## Mandatory Reading Material

- Sections *Format of a Doc Comment* and *Doc Comment Checking Tool* in 
  [http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)


## Optional Reading Material

- Remaining sections in 
  [http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)


## Deadline

Submit your solutions by pushing your code to the git repository by 
___Thursday, 9 March, 13:00___.
