package rocks.danielw.bowling.util;

public final class ObjectId {

  private static long ID = 1;

  public static long nextId() {
    return ID++;
  }

}
