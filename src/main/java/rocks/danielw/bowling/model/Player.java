package rocks.danielw.bowling.model;

import rocks.danielw.bowling.util.ObjectId;

public final class Player {

  private final long id;
  private final String name;

  public Player(String name) {
    this.id   = ObjectId.nextId();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Player{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
