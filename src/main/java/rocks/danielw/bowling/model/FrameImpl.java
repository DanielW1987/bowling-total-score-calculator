package rocks.danielw.bowling.model;

import rocks.danielw.bowling.util.FrameValidator;

final class FrameImpl implements Frame {

  private final int       firstRollScore;
  private final int       secondRollScore;
  private final FrameType frameType;
  private       int       firstBonusRollScore;
  private       int       secondBonusRollScore;

  FrameImpl(int firstRollScore, int secondRollScore) {
    this.firstRollScore   = firstRollScore;
    this.secondRollScore  = secondRollScore;
    this.frameType        = FrameType.of(firstRollScore, secondRollScore);
  }

  @Override
  public int getFirstRollScore() {
    return firstRollScore;
  }

  @Override
  public int getSecondRollScore() {
    return secondRollScore;
  }

  @Override
  public int getFirstBonusRollScore() {
    return firstBonusRollScore;
  }

  @Override
  public int getFrameScore() {
    return firstRollScore + secondRollScore + firstBonusRollScore + secondBonusRollScore;
  }

  @Override
  public void setBonusScore(int firstBonusRollScore) {
    setBonusScore(firstBonusRollScore, 0);
  }

  @Override
  public void setBonusScore(int firstBonusRollScore, int secondBonusRollScore) {
    FrameValidator.checkBonusScore(firstBonusRollScore, secondBonusRollScore);
    this.firstBonusRollScore  = firstBonusRollScore;
    this.secondBonusRollScore = secondBonusRollScore;
  }

  @Override
  public boolean isSpare() {
    return frameType == FrameType.SPARE;
  }

  @Override
  public boolean isStrike() {
    return frameType == FrameType.STRIKE;
  }

  @Override
  public boolean isOpenFrame() {
    return frameType == FrameType.OPEN_FRAME;
  }

}
