package kkwiatkowski.service;

public class UserInputValidator {

  public boolean validForOpenPath(int boardWidth, int boardHeight, int limit) {
    if(boardWidth > limit || boardHeight > limit)
      return false;
    if (boardWidth == 3 && boardHeight == 4) {
      return true;
    }
    if (boardWidth == 3 && boardHeight >= 7) {
      return true;
    }
    return boardWidth >= 4 && boardHeight >= 5;
  }

  public boolean validForClosedPath(int boardWidth, int boardHeight, int limit) {
    if(boardWidth > limit || boardHeight > limit)
      return false;
    if (boardWidth == 3 && boardHeight >= 10) {
      return true;
    }
    return boardWidth >= 5 && boardHeight >= 6;
  }

  public boolean isStartingPositionWithinBoard(
      int startX,
      int startY,
      int boardWidth,
      int boardHeight) {

    boolean isWithinWidth = startX >= 0 && startX < boardWidth;
    boolean isWithinHeight = startY >= 0 && startY < boardHeight;
    return isWithinWidth && isWithinHeight;
  }
}
