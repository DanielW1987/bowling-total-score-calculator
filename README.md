# Bowling Score Calcuator

## Introduction
In this repository there is a console program that can calculate the points of a bowling game. 
The points are entered completely via the command line. The program can be used with several players.

## Bowling Rules
The game consists of 10 frames. In each frame the player has two rolls and can play until to 10 pins. 
The number of points per frame consists of the number of overturned pins and other bonuses. 
These bonuses result from spares and strikes.

A spare is when the player has knocked over all 10 pins in a frame. The bonus for this
frame is the number of pins that are overturned in the next roll. 

A strike occurs when the player knocks over all 10 pins in the first attempt. The bonus for this is the
number of pins knocked over in the next two rolls.

In the tenth frame, a player who achieves a spare or a strike may have an additional roll
to finish the frame. But you can not play more than 3 rolls in the last frame.

## Download source code

Clone the source code via:

```
git clone https://github.com/Waginator/bowling-total-score-calculator.git
```

## Run the program

via .jar file
- Download .jar artifact [bowling-calculator-1.0.0.jar](https://github.com/Waginator/bowling-total-score-calculator/blob/master/src/artifacts/bowling-calculator-1.0.0.jar)
- Run command `java -jar bowling-calculator-1.0.0.jar`

via gradle
- Clone the repository
- Execute command `gradle run` in root directory

via your IDE
- Clone the repository
- Execute main class `rocks.danielw.bowling.BowlingTotalScoreCalculatorApplication`
