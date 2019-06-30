package rocks.danielw.bowling.util;

import rocks.danielw.bowling.model.Frame;

public final class FrameValidator {

  public static void checkFrameRolls(int firstRollScore, int secondRollScore) {
    if (   isInvalidRollScoreRange(firstRollScore) || isInvalidRollScoreRange(secondRollScore)
        || firstRollScore + secondRollScore > Frame.MAX_SCORE_PER_FRAME) {
      throw new IllegalArgumentException("Illegal arguments for a bowling frame. " +
              "The score must be greater than " + Frame.MIN_SCORE_PER_ROLL +
              " and not greater than " + Frame.MAX_SCORE_PER_FRAME + " in total!");
    }
  }

  public static void checkBonusScore(int firstBonusRollScore, int secondBonusRollScore) {
    if (   isInvalidRollScoreRange(firstBonusRollScore) || isInvalidRollScoreRange(secondBonusRollScore)
        || firstBonusRollScore + secondBonusRollScore > Frame.MAX_BONUS_SCORE_PER_FRAME) {
      throw new IllegalArgumentException("Illegal argument for bonus score. Must be a value between " +
              Frame.MIN_SCORE_PER_ROLL + " and " + Frame.MAX_BONUS_SCORE_PER_FRAME);
    }
  }

  private static boolean isInvalidRollScoreRange(int rollScore) {
    return rollScore < Frame.MIN_SCORE_PER_ROLL || rollScore > Frame.MAX_SCORE_PER_FRAME;
  }

  public static boolean isValidRollScoreRange(int rollScore) {
    return ! isInvalidRollScoreRange(rollScore);
  }

}
