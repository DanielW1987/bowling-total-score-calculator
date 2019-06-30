package rocks.danielw.bowling.model;

public enum FrameType {
  OPEN_FRAME, SPARE, STRIKE;

  static FrameType of (int firstRollScore, int secondRollScore) {
    if (firstRollScore == Frame.MAX_SCORE_PER_FRAME) {
      return FrameType.STRIKE;
    }
    else if (firstRollScore + secondRollScore == Frame.MAX_SCORE_PER_FRAME) {
      return FrameType.SPARE;
    }
    else {
      return FrameType.OPEN_FRAME;
    }
  }
}
