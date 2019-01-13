package kkwiatkowski.model;

public enum MoveDirection {
  RIGHT_BOTTOM_BOTTOM(1,-2),
  RIGHT_RIGHT_BOTTOM(2,-1),
  RIGHT_RIGHT_TOP(2,1),
  RIGHT_TOP_TOP(1,2),
  LEFT_TOP_TOP(-1,2),
  LEFT_LEFT_TOP(-2,1),
  LEFT_LEFT_BOTTOM(-2,-1),
  LEFT_BOTTOM_BOTTOM(-1,-2);

  private int directionX;
  private int directionY;

  MoveDirection(int directionX, int directionY) {
    this.directionX = directionX;
    this.directionY = directionY;
  }

  public int getDirectionX() {
    return directionX;
  }

  public int getDirectionY() {
    return directionY;
  }
}
