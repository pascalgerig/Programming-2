# Exercise 3

The third lecture covers *Design By Contract*, a methodology for designing 
systems by specifying pre- and postconditions (i.e., input requirements and 
output guarantees), as well as class invariants (i.e., conditions that must 
hold between public method calls). In this exercise, we practice this 
methodology by implementing a simple interactive game.


## Problem Description

ACME Inc. has decided to make a fun little toy game for kids to learn 
programming with: the turtle language!

Users write programs composed of the following commands (one command per 
line), telling a turtle how to walk over the screen:

* `east n`
	* Moves the turtle to the right by `n` steps.
- `west n`
	- Moves the turtle to the left by `n` steps.
* `south n`
	* Moves the turtle down by `n` steps.
- `north n`
	- Moves the turtle up by `n` steps.
- `south east n`
  - Moves the turtle diagonally down and right by `n` steps

You start with an empty class `BoardMaker`, which returns a 100 by 100 boolean 
matrix. An entry in the matrix denotes whether that particular field has been 
touched (thus resulting in the drawing of the red trace) or not.

Your task is to implement the game. You are free to add as many classes as you 
want and you are allowed to modify existing ones except for `TurtleRenderer`.

In order to pass this exercise, your solution *must* meet the following 
criteria:

* Split parsing and execution of programs in separate classes / objects.

* In your API documentation, explicitly state pre- and postconditions.

* Check pre- and postconditions using the `assert` keyword.

- Check class invariants.

* Your solution must reasonably deal with all input programs, in exactly the 
  place that your contracts prescribe.

* Write proper class comments. You should explain the class responsibilities 
  and state invariants.

* Include a UML class diagram that shows the communication and connections 
  between your classes.

* Include a UML sequence diagram that shows the method invocations that happen 
  in your program when the method `makeBoardFrom` (from class `BoardMaker`) is 
  called.

* Both UML diagrams must be created manually (you are not allowed to use tools 
  that generate diagrams from source code). Scan your handwritten diagrams or 
  take a photo.

* Note that you can not assume that the input is always a correct turtle 
  program. For example, if you run the GUI, `BoardMaker.makeBoardFrom` is 
  called after every single keystroke. Your program should deal with invalid 
  input, for example by ignoring commands that can not be parsed.

ACME Inc. thanks you for your cooperation to making the world more fun!


## Hints

- You can play the game interactively by running the main method of the 
  `TurtleRenderer` class. This class implements a simple GUI where you can 
  enter a turtle program which then is used with the `BoardMaker` class in 
  order to render the trail of the turtle.

- **Make sure that assertions are turned on**. Otherwise, the contracts are 
  not checked when you run the program.

- Keep the reading material from the last exercise (in particular the sections 
  on how to format doc comments) in mind.

- Remember that class invariants only need to hold before and after *public* 
  method calls.

- **Read the comment in `BoardMaker.makeBoardFrom`**, which outlines an 
  approach you could take for solving the exercise.


## Mandatory Reading Material

- *Applying 'Design by Contract'* by Bertrand Meyer (available on the course 
  website). The contents are relevant to the exam.


## Optional tasks

If you have extra time or want to practice more, you can solve these 
additional tasks. Note that usually, there will be no explicit feedback on 
optional tasks. Furthermore, solving optional tasks does not free you from 
having to solve the mandatory ones.

Some ideas for additional commands include the following:

* `jump x y`: Moves the turtle to the specified coordinates on the board 
  without leaving a trail.
- More directions, in particular, those including two basic directions (such 
  as `north west`). An elegant solution to this does not handle every case 
  independently, but treats the diagonal directions as a combination of the 
  basic directions.
- `goto x y`: Creates a straight line (as far as possible) to the given point.


## Deadline

Submit your solutions by pushing your code to the git repository by
___Thursday, 16 March, 13:00___.
