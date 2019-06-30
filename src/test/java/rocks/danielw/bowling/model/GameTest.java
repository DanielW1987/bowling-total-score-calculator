package rocks.danielw.bowling.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  private Game game;
  private Player player;

  @BeforeEach
  void setup() {
    player = new Player("John Doe");
    List<Player> players = Collections.singletonList(player);
    game = new Game(players);
  }

  @Test
  void testOpenFrameCalculation() {
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(7, 2));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(3, 5));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(8, 1));

    int totalScore = game.calculateTotalScoreForFrame(player.getId(), 3);
    assertEquals(26, totalScore);
  }

  @Test
  void testSpareFrameCalculation() {
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(8, 2));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(6, 2));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(8, 1));

    int totalScore = game.calculateTotalScoreForFrame(player.getId(), 3);
    assertEquals(33, totalScore);
  }

  @Test
  void testStrikeFrameCalculation() {
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(6, 0));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(8, 0));

    int totalScore = game.calculateTotalScoreForFrame(player.getId(), 3);
    assertEquals(30, totalScore);
  }

  @Test
  void testPerfectGame() {
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addBonusForTenthFrame(player.getId(), 10, 10);

    int totalScore = game.calculateTotalScoreForFrame(player.getId(), 10);
    assertEquals(300, totalScore);
  }

  @Test
  void testWholeGameWithSparesAndStrikes() {
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(7, 2));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(3, 5));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(9, 1));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(3, 6));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(0, 4));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(6, 1));
    game.addFrameForPlayer(player.getId(), Frame.createStrike());
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(5, 4));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(8, 1));
    game.addFrameForPlayer(player.getId(), Frame.ofRolls(9, 1));
    game.addBonusForTenthFrame(player.getId(), 7);

    int totalScore = game.calculateTotalScoreForFrame(player.getId(), 10);
    assertEquals(104, totalScore);
  }

}
