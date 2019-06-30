package rocks.danielw.bowling.model;

import rocks.danielw.bowling.util.FrameValidator;

public interface Frame {

  int MIN_SCORE_PER_ROLL        = 0;
  int MAX_SCORE_PER_FRAME       = 10; // excluding the bonus
  int MAX_BONUS_SCORE_PER_FRAME = 20; // 2 further strikes

  static Frame ofRolls(int firstRollScore, int secondRollScore) {
    FrameValidator.checkFrameRolls(firstRollScore, secondRollScore);
    return new FrameImpl(firstRollScore, secondRollScore);
  }

  static Frame ofString(String text) {
    if (text.trim().equalsIgnoreCase("x")) {
      return createStrike();
    }
    else {
      try {
        String[] rolls = text.split(",");
        int firstRollScore  = Integer.parseInt(rolls[0].trim());
        int secondRollScore = Integer.parseInt(rolls[1].trim());

        return ofRolls(firstRollScore, secondRollScore);
      }
      catch (Exception e) {
        throw new IllegalArgumentException("The input string '" + text + "' cannot be parsed into a Frame!", e);
      }
    }
  }

  static Frame createStrike() {
    return ofRolls(MAX_SCORE_PER_FRAME, 0);
  }

  int getFirstRollScore();

  int getSecondRollScore();

  int getFirstBonusRollScore();

  int getFrameScore();

  void setBonusScore(int firstBonusRollScore);

  void setBonusScore(int firstBonusRollScore, int secondBonusRollScore);

  boolean isSpare();

  boolean isStrike();

  boolean isOpenFrame();

}
