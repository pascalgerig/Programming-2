# Exercise 11

For the final exercise, we depart from the usual Java environment and get a 
taste of Pharo Smalltalk. We will revisit the turtle game from the beginning 
of the semester and finish an incomplete implementation, which requires a 
small amount of Pharo code.

## Turtle Game Revisited

In this implementation of the turtle game, we work with simple programs. The 
following commands are supported:

* `right n`
	* Moves the turtle to the right by `n` steps.
- `left n`
	- Moves the turtle to the left by `n` steps.
* `down n`
	* Moves the turtle down by `n` steps.
- `up n`
	- Moves the turtle up by `n` steps.

A *turtle program* is simply a list of commands separated by new-line 
characters.

The (incomplete) implementation of the game consists of four classes: 
`BoardRenderer`, `BoardModel`, `TurtleModel`, and `BoardChanged`. The game is 
started by instantiating the `BoardRenderer` class and passing it a 
`BoardModel`. This can be done with the following code (which is already 
entered in the Playground window in the provided Pharo image):
```
(BoardRenderer new
	boardModel: BoardModel new)
		openInWindow
		setLabel: 'Turtle Game'.
```
When executing this code (by selecting the code, right-clicking on the 
selection, and choosing `Do it`), a new window opens with the familiar turtle 
game, where the board is drawn on the left and the program input field is on 
the right.

Whenever the text of the input field changes, the method 
`BoardRenderer>>textChangedTo:` is executed, which uses the `BoardModel` 
instance and sends the message `updateBoardFrom:` with the current program to 
it. The method `BoardModel>>updateBoardFrom:` looks as follows:
```
updateBoardFrom: aTurtleProgram

	"First, we reset the board and move the turtle to its initial position."
	cellMatrix := self initialBoard.
	turtle reserPosition.

	"Now you have to:
		- split the turtle program into lines
		- extract from each line the command and the number of moves
		- move the turtle based on the command. The turtle class 
		  already has the methods for moving the turtle. You just need to call the right one."
	
	"......."

	"Notify the renderer that the turtle has moved on the board."
	self announcer announce: BoardChanged
```
In the first two lines of the method body, a new board gets initialized (where 
all fields are set to `false`). The turtle is placed on its initial position 
before any part of the turtle program is executed.

Your task is described in the comment in the middle: Splitting the turtle 
program `aTurtleProgram` into individual lines, extracting the commands, and 
then executing them using the methods available in the turtle class (e.g., 
after parsing the command `right 5`, you can execute `turtle right: 5`).

Finally, the final line *announces* that the board has changed, so the 
renderer knows that it needs to redraw the board (the renderer listens to the 
`BoardChanged` announcements, which is set up in the 
`BoardRenderer>>boardModel:` method).

In this exercise, you can do all the work in the 
`BoardModel>>updateBoardFrom:` method. There is no need to change or add any 
other parts.

## Running Pharo

In the `exercise_11` folder, you can find an installation of Pharo 5.0 
(virtual machine and image) for Windows, Linux, and Mac OSX; you do not have 
to download or set up anything. Use the following to start Pharo, depending on 
your platform:
- OSX: In the folder `mac/`, double click on `Pharo5.0.app`.
- Windows: In the `windows/` folder, double click on `Pharo.exe`.
- Linux: In the `linux/` folder, execute `./pharo` from the command-line.

Do not forget to save the image when you are done with the exercise (by 
clicking on the background and either selecting `Save` or `Save and quit`). 
Commit all changes in the `exercise_11` directory whenever you worked in 
Pharo.


## Resources

There are many good resources available at the [Pharo 
website](http://pharo.org/).

The books [Pharo by Example](http://files.pharo.org/books/pharo-by-example/), 
[Deep into Pharo](http://files.pharo.org/books/deep-into-pharo/), and 
[Enterprise Pharo](http://files.pharo.org/books/enterprise-pharo/) are freely 
available and a very nice starting point. However, keep in mind that some of 
the content in the books may be outdated.


## Deadline

Submit your solutions by pushing your code to the git repository by
___Thursday, 1 June, 13:00___.
