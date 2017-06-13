# Exercise 6

In the last exercise, we started with an implementation of the Quoridor game. 
We focused on initialization and rendering, but did not implement any game 
logic yet. This week's goal is straightforward. We will continue the 
implementation and get a working game as a result.


## Git tags

In order to mark milestones, we will start using git's tag feature. Tags are 
used to mark important commits with a name, for example, with a version 
number. The feature is thoroughly documented on 
[git-scm.com](https://git-scm.com/book/en/v2/Git-Basics-Tagging). Your first 
task is to read this documentation and learn about tags.


## Finishing the first stage

Before you start with the second stage of the game, you need to make sure that 
you meet all the requirements for exercise 5. If you missed some things, you 
should fix them and make sure everything works as intended. Once you have done 
that, use git to create a tag that marks the finished first stage. Here, we 
will use annotated tags. Create a tag for the first stage (fulfilling the 
requirements from exercise 5) with
```
git tag -a v1 -m "Quoridor stage 1"
```

You can then push the tags with `git push origin --tags` to our server.


## Second Stage

The main part of this exercise is implementing the second stage of the 
Quoridor game. Your focus should be to implement the required features, but 
also go back over your existing code and improve it, where necessary, with 
regards to documentation, testing, design by contract, and so on. In this 
second stage, you will have to implement the following features in order to 
pass the exercise.

### Wall placement

In each turn, players can either move by one tile or place a wall. Implement 
code that allows players to place walls that are two tiles wide. Every player 
has only a limited amount of walls available as specified in the game 
specification strings.


### Player actions

Although it is partially already covered in exercise 5, you may need to 
improve your implementation of player movement (and wall placement). Depending 
on which classes have which responsibilities, you may want to delegate the 
responsibility of checking whether a move is valid to the player or to a 
central "game" entity. Do not forget to make the responsibilities clear in the 
JavaDoc class comments for each class.


### Main game logic

So far, only individual parts of the game have been implemented and tested. 
The final step in this exercise is to implement a main `Game` class that 
provides (or delegates) the game logic. This should look similar to the `Game` 
class in exercise 2 (Snakes & Ladders). Implement a main method (like 
`Game#main` in Snakes & Ladders) that, when executed, starts a new game with 
two players. Instead of playing it automatically, your program should 
repeatedly ask the user to enter commands in the following format:

- Movement: Type a single character (L, R, U, D).
- Wall placement: Type the coordinates of two adjacent tiles that are not 
  occupied by players or walls (format `x1 y1 x2 y2`). For example, `1 1 2 1` 
  places a horizontal wall on the top left corner of the board.

Each of these commands should be followed by hitting Enter to confirm the 
action. After each action, the updated board state should be printed and you 
should state whose player turn it is next.

Since the user may input invalid text (for example, a user may try to place a 
wall on an occupied tile), you have to check the input first and then decide 
whether the action can be executed. If not, prompt the user to input a 
different action.

Finally, you should check whether there is a winner after each turn. Once 
there is, exit the main loop (again, similar to `Game#play` in exercise 2), 
print the final board state, and state who the winner is.


### Notes

- Once you are done with the above requirements, use git to tag your solution 
  with `v2`. Do not forget to push the tag using `git push origin --tags`.

- The corrections and feedback for exercise 5 will be done together with this 
  exercise. If you need feedback or have any questions, let us know on Piazza, 
  by mail, or during the pool hours.

## Deadline

Submit your solutions by pushing your code to the git repository by
___Thursday, 6 April, 13:00___.
