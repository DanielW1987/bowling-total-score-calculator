package rocks.danielw.bowling;

import rocks.danielw.bowling.cli.BowlingTotalScoreCalculatorCli;
import rocks.danielw.bowling.cli.ConsoleInputHandler;

public class BowlingTotalScoreCalculatorApplication {

  public static void main(String[] args) {
    BowlingTotalScoreCalculatorCli cliApplication = new BowlingTotalScoreCalculatorCli(new ConsoleInputHandler());
    cliApplication.start();
  }

}
