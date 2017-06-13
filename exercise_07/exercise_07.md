# Exercise 7

For this exercise, you will continue to work with your Quoridor implementation 
and finish the last iterations of the game. In the third iteration, you will 
implement an alternate level parsers. Depending on your design, this may 
require you to use refactoring techniques to rearrange things and make the 
code more modular. In the final iteration, you will add a proper algorithm for 
checking wall placement and finally you will polish the code and make the 
final submission.

Before you start with iteration 3, you should finish the tasks from the 
previous exercise. It is also a good idea to clean up and refactor your code 
at this point. Once you are done, tag your current state with

```
git tag -a v2 -m "Quoridor iteration 2"
```


## Quoridor iteration 3

The level specification files are quite efficient: they allow you to specify 
the board size, players, and the goal walls. However, they are limited to 
square playing fields. In this task, you will have to implement a new parser 
that handles games that are non square. An example of a game in this new 
specification looks like this (this is also in the file `game3.txt`):

```
7 8 2 3
#sssss#
#     #
#  O  #
###   #
#     #
#   ###
#  S###
#ooo###
O Otis Redding
S Solomon Burke
```

The first row specifies the board size (width, height, including the 
surrounding walls), the number of players, and the number of walls each player 
can place.

Next, an ASCII representation of the board follows. The character `#` denotes 
a wall tile, a space denotes an empty tile. Uppercase letters (here `O` and 
`S`) denote player starting positions, while lowercase letters (here `o` and 
`s`) denote goal tiles, that is, tiles on which the corresponding player needs 
to land in order to win the game.

Finally, a line for each player follows that maps the symbol on the board to 
their full names.

Your task is to change your existing implementation to handle these kinds of 
board specifications. As before, you should test your implementation to make 
sure everything works as intended.

Once you did this, tag your your final commit as follows:

```
git tag -a v3 -m "Quoridor iteration 3"
```


## Quoridor iteration 4

Now that your game functionality is almost complete, you may realize that your 
board can get quite cluttered with walls. The rules of Quoridor do not allow 
walls to be placed such that a player can not reach a goal tile anymore. 
However, it is allowed to cut off parts of the board (and make them 
unreachable) **as long as all players are still able to reach one of their 
goal tiles**.

In this final iteration, implement an algorithm that checks whether a wall 
placement is valid. Demonstrate that it work with tests that cover **at 
least** the following scenarios:
- Regular placement of a wall
- Placement of a wall that cuts off parts of the board
- Invalid placement of a wall that would result in a situation where some 
  player can't reach its destination anymore

Once you have finished, tag your work as usual:

```
git tag -a v4 -m "Quoridor iteration 4"
```

## Polishing

Finally, finish off your implementation by cleaning up your project and making 
sure that you follow the principles taught in the course. This includes things 
like
  - documentation (JavaDoc, inline comments where necessary),
  - design by contract,
  - responsibility driven design (state responsibilities in class comments!).

Tag your work with `v5`

```
git tag -a v5 -m "Quoridor release"
```

Congratulations, you have implemented your own game from scratch!


## Deadline

Submit your solutions by pushing your code to the git repository by
___Thursday, 27 April, 13:00___.
