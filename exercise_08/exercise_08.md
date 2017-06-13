# Exercise 8

The last lecture covered *GUI Construction* and gave an introduction to GUI 
design. A GUI for the TicTacToe game has been designed and was implemented 
using Java Swing and the Model-View-Controller paradigm.

In this week's exercise, we go back to pen and paper and learn how early GUI 
prototypes are sketched and designed. GUI mockups are dummies that are used to 
design a GUI and present some use cases without actually implementing program 
logic in the background. Initial mockups are often done with pen and paper, 
especially when designing a game interface. In addition, paper prototypes are 
used to simulate the user experience and get a first feeling on how the 
designed interface can be used.

In this exercise, you will draw mockups and create a prototype for the 
Quoridor game. There is no programming involved in this exercises, but you 
should use the time in case you have unfinished business from the past 
exercises.


## GUI Design

Before implementing a GUI, it is often a good idea to first think about how 
the GUI should look like. The easiest way to do this is by sketching out your 
ideas with pen and paper. In this part of the exercise, you **do not need to 
implement anything**. Instead, we focus on bringing the ideas to paper. To 
pass this part, **you must complete the following tasks**:

- Create at least 6 different sketches for the main view of the Quoridor game 
  (by main view we mean the state of a game where the player is playing the 
  game and moving around the board in an unsolved state). Draw your sketches
  by hand (on paper and preferably with a pencil; we do not accept drawings 
  that have crossed out and corrected parts!), take a photo or scan the 
  results, and add them to your repository. Your sketches can be similar, but 
  you should include at least three radically different designs.

- Select one of your sketches and create a paper prototype of your game. 
  Simulate the user experience by showing how the user interface can be used. 
  Take a picture of each (major) step. Your prototype should show the 
  following:
  - Start screen.
  - Selecting game parameters (number of players, board size, walls per 
    player, ...).
  - Initial board state.
  - Performing at least one wall placement and 2 moves (in total, not per 
    player).
  - Winning screen once a player has reached his destination.
  - Whatever you intend to do after a game is played (is a rematch loaded? Are 
    you going back to the start screen? ...)

- Create a file `sketches.md` where you include the pictures for the 6 
  sketches you created before.

- Create a file `prototype.md`, where you include the pictures that show the 
  individual steps. For each picture, you should give a short description of 
  what it shows and what the user needs to do in order to get to the next one. 
  For example, you need to describe how the user can make the player move 
  around in order to solve the level.


## Notes

- Make sure that all your photos and/or scans are readable. If we can't read 
  them, we may ask you to redo them.

- Your designs can be intended for different devices. For example, you could 
  sketch a GUI for a smartphone or a console.

- Make sure you use proper markdown syntax in `sketches.md` and 
  `prototype.md`. If you are unsure about how to do that, read the [markdown 
  documentation](https://daringfireball.net/projects/markdown/).

- Keep in mind that the GUI is not necessarily just about visualizing the 
  board. Maybe there is other useful information that you can present to the 
  user?


## Deadline

Submit your solutions by pushing your code to the git repository by 
___Thursday, 4 May, 13:00___.
