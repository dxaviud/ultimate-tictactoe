

import java.util.Scanner;

import Marks.*;

public class TicTacToeGame {

    private TicTacToeBoard gameBoard;
    private int turn;
    private String winner;
    private String currentPlayer;

    public TicTacToeGame() {
        gameBoard = new TicTacToeBoard();
        turn = 1;
        winner = "neither";
        currentPlayer = "X";
    }

    public void runGame() {

        while(true) {

            displayBoard();

            System.out.println("Turn " + turn + ": " + currentPlayer + " plays.");

            playerMakesMark();

            switchCurrentPlayer();

            turn++;

            if (getGameOver()) {
                displayBoard();
                break;
            }
        }

        //game over message
        System.out.println("\n");
        System.out.println("Game over. Winner: " + winner);
    }

    public void switchCurrentPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    public void makeXMark() {
        makeGeneralMark(new XMark());
    }

    public void makeOMark() {
        makeGeneralMark(new OMark());
    }

    public void makeGeneralMark(Mark mark) {
        int rowInd = -1;
        int colInd = -1;
        while(true) {
            rowInd = getPlayerInput("Enter row index: ");
            colInd = getPlayerInput("Enter col index: ");
            if (gameBoard.getBox(rowInd, colInd) instanceof NoMark) {
                break;
            } else {
                System.out.println("Must pick box that is not already marked.");
            }
        }
        gameBoard.setBox(rowInd, colInd, mark);
    }

    public int getPlayerInput(String prompt) {
        Scanner in = new Scanner(System.in);
        int input = -1;
        while(true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(in.next());
                if (input < 0 || input > 2) {
                    System.out.println("Input must be 0, 1, or 2.");
                    continue;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT");
            }
        }
    }

    public void playerMakesMark() {
        if (turn % 2 == 0) {
            makeOMark();
        } else {
            makeXMark();
        }
    }

    public boolean getGameOver() {
        //check all rows
        for (int row = 0; row < gameBoard.length; row++) {
            if (!(gameBoard.getBox(row, 0) instanceof NoMark) && 
            gameBoard.getBox(row, 0).equals(gameBoard.getBox(row, 1)) &&
            gameBoard.getBox(row, 1).equals(gameBoard.getBox(row, 2))) {
                winner = gameBoard.getBox(row, 0).toString();
                return true;
            }
        }

        //check all cols
        for (int col = 0; col < gameBoard.width; col++) {
            if (!(gameBoard.getBox(0, col) instanceof NoMark) && 
            gameBoard.getBox(0, col).equals(gameBoard.getBox(1, col)) &&
            gameBoard.getBox(1, col).equals(gameBoard.getBox(2, col))) {
                winner = gameBoard.getBox(0, col).toString();
                return true;
            }
        }

        //check diagonals
        if (!(gameBoard.getBox(0, 0) instanceof NoMark) &&
        gameBoard.getBox(0, 0).equals(gameBoard.getBox(1, 1)) && 
        gameBoard.getBox(1, 1).equals(gameBoard.getBox(2, 2))) {
            winner = gameBoard.getBox(0, 0).toString();
            return true;
        }
        if (!(gameBoard.getBox(0, 2) instanceof NoMark) &&
        gameBoard.getBox(0, 2).equals(gameBoard.getBox(1, 1)) && 
        gameBoard.getBox(1, 1).equals(gameBoard.getBox(2, 0))) {
            winner = gameBoard.getBox(0, 2).toString();
            return true;
        }

        //board is full but no three-in-a-row (tie)
        if (getBoardIsFull()) {
            winner = "Neither";
            return true;
        }

        return false;
    }

    public boolean getBoardIsFull() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.width; col++) {
                if (gameBoard.getBox(row, col) instanceof NoMark) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayBoard() {
        gameBoard.display();
    }
}