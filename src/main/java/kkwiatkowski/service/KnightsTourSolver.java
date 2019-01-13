package kkwiatkowski.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import kkwiatkowski.model.Coordinate;
import kkwiatkowski.model.MoveDirection;

public class KnightsTourSolver {

  private int boardWidth;
  private int boardHeight;
  private int[][] board;
  private int[][] closedPathResult;
  private int[][] openPathResult;
  private boolean openPathFound = false;
  private Coordinate start;

  public KnightsTourSolver(int boardWidth, int boardHeight) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
  }

  public boolean solve(Coordinate startCoordinate) {
    this.start = startCoordinate;
    this.board = getBoard();
    board[startCoordinate.getX()][startCoordinate.getY()] = 0;
    return solve(startCoordinate, 1, board);
  }

  private boolean solve(Coordinate currentPosition, int pathLenght, int[][] board) {

    if (isSolutionValid(pathLenght, currentPosition)) {
      return true;
    }

    List<Coordinate> possibleDirections
        = getPossibleDirections(board, currentPosition, true);
    possibleDirections = sortByLowestPossibleMoves(possibleDirections, board);

    for (Coordinate coordinate : possibleDirections) {
      board[coordinate.getX()][coordinate.getY()] = pathLenght;
      if (solve(coordinate, pathLenght + 1, board)) {
        return true;
      } else {
        board[coordinate.getX()][coordinate.getY()] = -1;
      }
    }

    return false;
  }

  private boolean isPossible(Coordinate coordinate, int[][] board) {
    boolean isWithinWidth = coordinate.getX() >= 0 && coordinate.getX() < boardWidth;
    boolean isWithinHeight = coordinate.getY() >= 0 && coordinate.getY() < boardHeight;
    return isWithinWidth && isWithinHeight && board[coordinate.getX()][coordinate.getY()] == -1;
  }

  private int[][] getBoard() {
    this.board = new int[boardWidth][boardHeight];

    for (int i = 0; i < boardWidth; i++) {
      for (int j = 0; j < boardHeight; j++) {
        board[i][j] = -1;
      }
    }

    return board;
  }

  private List<Coordinate> getPossibleDirections(int[][] board, Coordinate currentPosition,
      boolean checkPossible) {

    List<Coordinate> possibleDirections = new ArrayList<>();
    for (MoveDirection moveDirection : MoveDirection.values()) {
      int targetX = moveDirection.getDirectionX() + currentPosition.getX();
      int targetY = moveDirection.getDirectionY() + currentPosition.getY();
      Coordinate target = new Coordinate(targetX, targetY);
      possibleDirections.add(target);
    }

    if (checkPossible) {
      possibleDirections = possibleDirections
          .stream()
          .filter(s -> isPossible(s, board))
          .collect(Collectors.toList());
    }

    return possibleDirections;
  }

  private List<Coordinate> sortByLowestPossibleMoves(List<Coordinate> possibleDirections,
      int[][] board) {

    for (Coordinate possibleDirection : possibleDirections) {
      possibleDirection
          .setPossibleMoves(
              getPossibleDirections(board, possibleDirection, true).size());
    }
    return possibleDirections
        .stream()
        .sorted(Comparator.comparing(Coordinate::getPossibleMoves))
        .collect(Collectors.toList());
  }

  private boolean isSolutionValid(int pathLenght, Coordinate currentPosition) {
    if (isValidOpenPath(pathLenght)) {
      if (!openPathFound) {
        openPathResult = copyMatrix();
        openPathFound = true;
      }
      if (canJumpToStartSquare(currentPosition)) {
        closedPathResult = copyMatrix();
        return true;
      }
    }
    return false;
  }

  private boolean isValidOpenPath(int pathLenght) {
    return pathLenght == boardWidth * boardHeight;
  }

  private boolean canJumpToStartSquare(Coordinate currentPosition) {
    List<Coordinate> possibleDirections
        = getPossibleDirections(board, currentPosition, false);
    if(possibleDirections.contains(start))
      return true;
    return false;

  }

  private int[][] copyMatrix() {
    int[][] matrix = new int[boardWidth][boardHeight];
    for (int i = 0; i < board.length; i++) {
      matrix[i] = board[i].clone();
    }
    return matrix;
  }

  public int[][] getOpenPathResult() {
    return this.openPathResult;
  }

  public int[][] getClosedPathResult() {
    return this.closedPathResult;
  }
}

