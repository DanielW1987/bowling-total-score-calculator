package rocks.danielw.bowling.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

  @ParameterizedTest
  @MethodSource(value = "frameTypeDataProvider")
  void testFrameType(int firstRollPoints, int secondRollPoints, Predicate<Frame> frameTypeChecker) {
    Frame frame = Frame.ofRolls(firstRollPoints, secondRollPoints);

    assertTrue(frameTypeChecker.test(frame));
  }

  @ParameterizedTest
  @MethodSource(value = "isInvalidFrameDataProvider")
  void testIsInvalidFrame(int firstRollPoints, int secondRollPoints) {
    assertThrows(IllegalArgumentException.class, () -> Frame.ofRolls(firstRollPoints, secondRollPoints));
  }

  private static Stream<Arguments> frameTypeDataProvider() {
    return Stream.of(
            Arguments.of(3, 7, (Predicate<Frame>) Frame::isSpare),
            Arguments.of(1, 9, (Predicate<Frame>) Frame::isSpare),
            Arguments.of(5, 5, (Predicate<Frame>) Frame::isSpare),
            Arguments.of(0, 10, (Predicate<Frame>) Frame::isSpare),
            Arguments.of(10, 0, (Predicate<Frame>) Frame::isStrike),
            Arguments.of(4, 4, (Predicate<Frame>) Frame::isOpenFrame),
            Arguments.of(1, 8, (Predicate<Frame>) Frame::isOpenFrame),
            Arguments.of(8, 1, (Predicate<Frame>) Frame::isOpenFrame)
    );
  }

  private static Stream<Arguments> isInvalidFrameDataProvider() {
    return Stream.of(
            Arguments.of(10, 1),
            Arguments.of(1, 10),
            Arguments.of(0, 11),
            Arguments.of(4, 7),
            Arguments.of(-5, 4),
            Arguments.of(5, -4),
            Arguments.of(-5, -4)
    );
  }

}
