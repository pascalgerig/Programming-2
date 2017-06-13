# Exercise 5

This week, we will start the implementation of a simple board game. By 
applying the concepts that have been covered so far (for example, 
object-oriented design principles, unit testing, responsibility driven 
design), we aim for a well-designed basic game implementation.


## Problem Description

*Quoridor* ([wikipedia article](https://en.wikipedia.org/wiki/Quoridor)) is a 
simple board game that requires at least 2 players. The game is typically 
played on a square board with 9 by 9 tiles. Players start at one edge of the 
board. The goal for each player is to move to the opposite edge of the board. 
However, players can not only just move. It is also possible to place walls 
that are 2 tiles wide. In the original game, the walls are placed *between* 
the tiles. However, we will implement a variant of the game where **we place 
the walls on the tiles themselves**. The game has the following rules:

- Each player starts on a given tile of the board.
- Players take turns.
- Every player starts with a given number of walls (e.g., 5).
- In each turn, a player can either move by one step (up, down, left, right), 
  or can place a wall.
- Walls can only be placed such that after placing it, all players are still 
  able to reach their destination.
- Whoever reaches the opposite wall first wins.

Your task in this and upcoming exercises is to implement your own *Quoridor* 
game. This week, you will start with the basics for setting up a game and 
printing the state to the terminal.


### Board specification

Board files are text files that specify the game playing field including board 
size and player names, symbols, and start positions. The following format is 
used:
```
width height
player1 symbol1 x1 y2 target1
player2 symbol1 x1 y2 target2
... (additional players)
```

For example, the following represents a game on a 7 by 12 board with two 
players:
```
7 12
Otis Redding O 1 1 R
Solomon Burke S 7 12 L
```
Player names can include spaces, symbols have to be a single character, 
coordinates are 1-indexed (that is, the top-left coordinate is (1, 1) and the 
bottom right coordinate is (7, 12)), and targets are single characters 
denoting the goal side (one of R, L, U, D, for right, left, up, down).

Hint: The regular expression `(.+) (.) (\d+) (\d+) (.)` matches the player 
specification (the parentheses are used for grouping and allow you to access 
subpatterns in the matches directly).


### Board rendering

The game, similar to Snakes & Ladders, is rendered using simple print 
statements. After each move, the full board should be rendered. For example, 
the specification
```
3 3
Kris Kristofferson K 1 1 R
Janis Joplin J 1 3 R
```

is rendered as follows

```
######
#K   #
#    #
#J   #
######
```

Walls are represented as `#` characters, and there should be a wall around the 
board. Players are printed with the symbol given in the game instance 
specification.

The example board specifications from above are stored in individual files in 
the `games/` folder.


### Tasks

In order to pass this exercise, you need to implement the following.

#### Parser

Implement a parser for the game specifications that creates a game instance 
and tests that make sure the parser creates the game correctly (note that you 
should not depend on the renderer to do this!). It should be possible to read 
the game specification from a file (e.g., the sample files in the `games/` 
folder).


#### Renderer

Implement a renderer that prints a game state to the standard output as 
described above and implement tests that make sure the renderer prints given 
games correctly. Here, you need to create games without using the parser.


#### Game/Player

Implement player movement; that is, there should be a possibility to move a 
given player on the board by one step. You can visually verify whether the 
movement has taken place by rendering the game state before and after 
executing the move. However, you should also write tests that do not depend on 
the renderer. You do not need to implement the main loop for playing the game, 
just the functionality that moves a player. For example, you could implement 
methods like `Player#moveRight()` to do this. Then, your tests would need to 
cover these methods.


### Notes

- You are free to design the game as you want, but you should follow the 
  design principles that you learned so far. You can take a look at the Snakes 
  & Ladders implementations we provided to get an idea of how your class 
  structures could look like. **You must at least have dedicated classes for 
  the game, board tiles, players, the parser, and the renderer**. Some of 
  those have already been added (but lack an implementation). You can use 
  them, but if you want, you can also modify or change them (or change them 
  into interfaces if necessary).

- You do not have to implement a fully functional game yet! This week, we 
  focus on parsing, rendering, and basic player movement. You do not need to 
  implement any game logic (such as checking whether a move is valid or 
  checking whether the game is over).

- Write proper JavaDoc class and method comments.

- Do not forget about design by contract.


## Deadline

Submit your solutions by pushing your code to the git repository by
___Thursday, 30 March, 13:00___.
