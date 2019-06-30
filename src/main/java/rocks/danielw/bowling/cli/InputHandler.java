package rocks.danielw.bowling.cli;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public interface InputHandler {

  String readString(String message, Predicate<String> inputValidator);

  List<String> readListOfStrings(String message, Predicate<String> inputValidator);

  int readInt(String message, IntPredicate inputValidator);

  <T> T readObject(String message, Function<String, T> mapper);

}
