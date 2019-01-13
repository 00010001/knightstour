package kkwiatkowski.model;

import java.util.Objects;

public class Coordinate {

  private int x;
  private int y;
  private int possibleMoves;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getPossibleMoves() {
    return possibleMoves;
  }

  public void setPossibleMoves(int possibleMoves) {
    this.possibleMoves = possibleMoves;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coordinate that = (Coordinate) o;
    return getX() == that.getX() &&
        getY() == that.getY();
  }

  @Override
  public int hashCode() {

    return Objects.hash(getX(), getY());
  }
}
