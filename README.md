Connect4
========

This project is an implementation of the well-known Connect-Four game for the lecture Software Engineering of the University of Applied Science Konstanz.

It was important to have a good Model-View-Controller-structure and to implement some patterns like the Observer-Pattern (which is necessary for running the TUI and the GUI at the same time).

Further requirements of this project were:

 * using the version control with git on github.
 * performing test-driven-development using JUnit.
 * metrics with sonar.
 * components and interfaces between each layer.

The initial UI was a textual User-Interface, so we could operate with the code in an early state of the project. The graphical User-Interface had not to be built until the model- and the controller-layer are (almost) finished, and there is no need to perform big changes on these layers anymore.

How To Play

TUI:
If you need help with the TUI type "help" to get an overview about available commands:

Available commands:
 * [1-7]: the column you want to put in your token
 * n1[Name]: change the name of Player 1
 * n2[Name]: change the name of Player 2
 * c1[ColorNumber]: change the color of Player 1
 * c2[ColorNumber]: change the color of Player 2
 * --Available colors: 1=Black, 2=Gray, 3=Red, 4=Orange, 5=Yellow, 6=Green, 7=Blue, 8=Pink
 * help: request help
 * new: start a new game
 * quit: exit the game

GUI:
All you need to do is Press in the column you want to put in your token.


