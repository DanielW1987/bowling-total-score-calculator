package rocks.danielw.bowling.cli;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ConsoleInputHandler implements InputHandler {

  private final Scanner scanner;

  public ConsoleInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public String readString(String message, Predicate<String> inputValidator) {
    String input;
    do {
      System.out.print(message);
      input = scanner.nextLine();
    }
    while (! inputValidator.test(input));

    return input;
  }

  @Override
  public List<String> readListOfStrings(String message, Predicate<String> inputValidator) {
    String input = readString(message, inputValidator);
    String[] values = input.split(",");

    return Stream.of(values)
            .map(String::trim)
            .collect(Collectors.toList());
  }

  @Override
  public int readInt(String message, IntPredicate inputValidator) {
    int input;
    do {
      System.out.print(message);
      try {
        input = scanner.nextInt();
      }
      catch (InputMismatchException ime) {
        System.err.println(ime.getMessage());
        return readInt(message, inputValidator);
      }
    }
    while (! inputValidator.test(input));

    return input;
  }

  @Override
  public <T> T readObject(String message, Function<String, T> mapper) {
    T object = null;
    boolean success = false;
    String input = null;

    while (input == null || ! success) {
      input = readString(message, Objects::nonNull);

      try {
        object = mapper.apply(input);
        success = true;
      }
      catch (Exception e) {
        System.err.println(e.getMessage());
        if (e.getCause() != null) {
          System.err.println(e.getCause().getMessage());
        }
      }
    }

    return object;
  }

}
