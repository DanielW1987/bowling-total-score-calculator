package rocks.danielw.bowling.model;

import java.util.*;

public final class Game {

  public static final int MAX_ROUNDS = 10;

  private final List<Player> players;
  private final Map<Long, List<Frame>> framesPerPlayer;

  public Game(List<Player> players) {
    this.players = players;
    this.framesPerPlayer = new HashMap<>();

    players.forEach(player -> framesPerPlayer.put(player.getId(), new ArrayList<>()));
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void addFrameForPlayer(long playerId, Frame frame) {
    assert framesPerPlayer.containsKey(playerId): "Player should exist!";
    framesPerPlayer.get(playerId).add(frame);
  }

  public List<Frame> getPlayedFramesForPlayer(long playerId) {
    assert framesPerPlayer.containsKey(playerId): "Player should exist!";
    return framesPerPlayer.get(playerId);
  }

  public void addBonusForTenthFrame(long playerId, int firstBonusRollScore) {
    addBonusForTenthFrame(playerId, firstBonusRollScore, 0);
  }

  public void addBonusForTenthFrame(long playerId, int firstBonusRollScore, int secondBonusRollScore) {
    assert framesPerPlayer.containsKey(playerId): "Player should exist!";
    if (framesPerPlayer.get(playerId).size() == MAX_ROUNDS) {
      Frame tenthFrame = framesPerPlayer.get(playerId).get(9);
      tenthFrame.setBonusScore(firstBonusRollScore, secondBonusRollScore);
    }
    else {
      throw new IllegalStateException("Less or more than 10 frames played yet!");
    }
  }

  public int calculateTotalScoreForFrame(long playerId, int frame) {
    assert framesPerPlayer.containsKey(playerId): "Player should exist!";
    calculateBonusScore();
    return framesPerPlayer.get(playerId).stream().limit(frame).mapToInt(Frame::getFrameScore).sum();
  }

  private void calculateBonusScore() {
    for (List<Frame> frames : framesPerPlayer.values()) {
      for (int index = 0; index < frames.size(); index++) {
        Frame frame = frames.get(index);

        // bonus can only be calculated if the next roll has been completed
        if (index + 1 < frames.size()) {
          Frame nextFrame = frames.get(index + 1);

          if (frame.isSpare()) {
            frame.setBonusScore(nextFrame.getFirstRollScore());
          }
          else if (frame.isStrike()) {
            // If next frame is also a strike, a bonus can only be calculated if the second after next roll has been completed
            if (nextFrame.isStrike() && index + 2 < frames.size()) {
              Frame seconderAfterNextFrame = frames.get(index + 2);
              frame.setBonusScore(nextFrame.getFirstRollScore(), seconderAfterNextFrame.getFirstRollScore());
            }
            // If both the ninth and tenth frame is a strike, then the ninth frame receives the first bonus roll of the tenth frame as a bonus
            else if (nextFrame.isStrike() && index == 8) {
              frame.setBonusScore(nextFrame.getFirstRollScore(), nextFrame.getFirstBonusRollScore());
            }
            // In all other cases the frame gets the rolls of the next frame as a bonus
            else {
              frame.setBonusScore(nextFrame.getFirstRollScore(), nextFrame.getSecondRollScore());
            }
          }
        }
      }
    }
  }

}
