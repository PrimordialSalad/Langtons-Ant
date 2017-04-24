# Langtons-Ant
This project is a recreation of Langton's Ant, a cellular automaton, which can be read about [here](//en.wikipedia.org/wiki/Langton%27s_ant).

Most of the code is a reuse of my older Game of Life project especially the code dealing with the GUI and class structure.  The only big changes that were made were the logic of the classes to create the ant's logic and not use the Game of Life logic. Here is an example of the GUI used in the recreation of Langton's Ant:

![Gui example for Langton's Ant](http://i.imgur.com/968troj.png)

The red square is the ant.  The green is simply the last place you hovered over the grid.  The red ant travels the board depending on the two rules outlined in the article linked above.

Here is a the ant after many steps of the program:

![Example after many steps of the program]()

If the ant travels off the board it will be randomly placed back on the board.  Everytime you hit clear, the ant is randomly placed elsewhere on the board.

There is another mode to this recreation. In the LangtonsAnt.java class, if the member variable m_antMode is set to anything other than 0 then the board will be randomly populated on creation.  This was an error when I first started and I thought the board was supposed to be initially populated with true or false cells.

If you want to learn more about the GUI, refer to my previous Game of Life project [here](https://github.com/PrimordialSalad/Game-Of-Life-Simulation).
