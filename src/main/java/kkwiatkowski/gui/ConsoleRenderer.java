package kkwiatkowski.gui;

import kkwiatkowski.model.Coordinate;
import kkwiatkowski.service.KnightsTourSolver;
import kkwiatkowski.service.UserInputValidator;

public class ConsoleRenderer {

  private final int LIMIT = 90;
  private final String errorMsg = "Uzytkownik podal dane na podstawie ktorych nie mozna znalezc "
      + "rozwiazania";

  private int boardWidth;
  private int boardHeight;
  private int startX;
  private int startY;

  public void start() {
    UserInputValidator userInputValidator = new UserInputValidator();

    getInputFromUser();

    if (userInputValidator.isStartingPositionWithinBoard(startX, startY, boardWidth, boardHeight)) {

      if (userInputValidator.validForOpenPath(boardWidth, boardHeight, LIMIT) &&
          userInputValidator.validForClosedPath(boardWidth, boardHeight, LIMIT)) {

        KnightsTourSolver knightsTourSolver = new KnightsTourSolver(boardWidth, boardHeight);
        knightsTourSolver.solve(new Coordinate(startY, startX));

        if (knightsTourSolver.getOpenPathResult() != null) {
          System.out.println("Ponizej rozwiazanie dla otwartejSciezki");
          this.printSolution(knightsTourSolver.getOpenPathResult(), boardWidth, boardHeight);

          if (knightsTourSolver.getClosedPathResult() != null) {
            System.out.println("Ponizej rozwiazanie dla zamknietejSciezki");
            this.printSolution(knightsTourSolver.getClosedPathResult(), boardWidth, boardHeight);
          } else {
            System.out.println("Brak rozwiazania dla zamknietejSciezki");
          }
        } else {
          System.out.println("Brak rozwiazan");
        }

      } else {
        System.out.println("Brak rozwiazan");
      }
    } else {
      System.out.println(errorMsg);
    }

  }

  private void getInputFromUser() {
    boardWidth = 6;
    boardHeight = 6;
    startX = 0;
    startY = 0;
  }

  private void printSolution(int[][] sol, int width, int height) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        System.out.print(String.format("%4d", sol[x][y]) + " ");
      }
      System.out.println();
    }
  }
}
