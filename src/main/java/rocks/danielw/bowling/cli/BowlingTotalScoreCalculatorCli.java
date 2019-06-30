package rocks.danielw.bowling.cli;

import rocks.danielw.bowling.model.Frame;
import rocks.danielw.bowling.model.Game;
import rocks.danielw.bowling.model.Player;
import rocks.danielw.bowling.util.FrameValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BowlingTotalScoreCalculatorCli {

  private InputHandler inputHandler;
  private Game bowlingGame;

  public BowlingTotalScoreCalculatorCli(InputHandler inputHandler) {
    this.inputHandler = inputHandler;
  }

  public void start() {
    List<Player> players = setupPlayers();
    System.out.println("OK! Let's play with " + players.size() + " player(s).");

    bowlingGame = new Game(players);
    playRounds();
  }

  private List<Player> setupPlayers() {
    String message = "Please enter the names of all players (comma separated): ";
    List<String> playerNames = inputHandler.readListOfStrings(message, value -> value != null && ! value.trim().isEmpty());

    return playerNames.stream().map(Player::new).collect(Collectors.toList());
  }

  private void playRounds() {
    for (int round = 1; round <= Game.MAX_ROUNDS; round++) {
      System.out.print("\n\n");
      System.out.println("Frame " + round);

      for (Player player : bowlingGame.getPlayers()) {
        String message = "Please enter a valid frame score for player " + player.getName() + " (format: '[0-10], [0-10]' or 'x' for strike): ";
        Frame frame = inputHandler.readObject(message, Frame::ofString);
        bowlingGame.addFrameForPlayer(player.getId(), frame);

        if (round == 10) {
          if (frame.isSpare()) {
            System.out.println("Since you had a spare in the tenth frame, you have a bonus roll (format: '[0-10]').");
            int bonusRollScore = inputHandler.readInt("Roll score: ", FrameValidator::isValidRollScoreRange);
            bowlingGame.addBonusForTenthFrame(player.getId(), bonusRollScore);
          }
          else if(frame.isStrike()) {
            System.out.println("Since you had a strike in the tenth frame, you have two bonus rolls (format: '[0-10]').");
            int firstBonusRollScore = inputHandler.readInt("First roll score: ", FrameValidator::isValidRollScoreRange);
            int secondBonusRollScore = inputHandler.readInt("Second roll score: ", FrameValidator::isValidRollScoreRange);
            bowlingGame.addBonusForTenthFrame(player.getId(), firstBonusRollScore, secondBonusRollScore);
          }
        }
      }

      printPlayerScoreCards();

    }
  }

  private void printPlayerScoreCards() {
    System.out.print("\n\n");
    System.out.println("Current Scorecard");

    for (Player player : bowlingGame.getPlayers()) {
      StringBuilder sb = new StringBuilder();
      sb.append(player.getName())
        .append(": \t");

      int frameCount = 1;
      for (Frame frame : bowlingGame.getPlayedFramesForPlayer(player.getId())) {
        int totalScoreForFrame = bowlingGame.calculateTotalScoreForFrame(player.getId(), frameCount);
        String scoreString = IntStream.of(frame.getFirstRollScore(), frame.getSecondRollScore(), totalScoreForFrame)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));

        sb.append("[")
                .append(scoreString)
                .append("]")
                .append("\t");

        frameCount++;
      }

      System.out.println(sb.toString());
    }
  }

}
